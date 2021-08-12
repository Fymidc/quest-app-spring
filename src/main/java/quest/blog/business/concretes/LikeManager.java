package quest.blog.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.blog.business.abstracts.LikeService;
import quest.blog.business.abstracts.PostService;
import quest.blog.business.abstracts.UserService;
import quest.blog.dataAccess.LikeDao;
import quest.blog.entities.Like;
import quest.blog.entities.Post;
import quest.blog.entities.User;
import quest.blog.entities.dtos.LikeCreateRequest;

@Service
public class LikeManager implements LikeService {

    private LikeDao likeDao;
    private UserService userService;
    private PostService postService;

    @Autowired
    public LikeManager(LikeDao likeDao, UserService userService,
     PostService postService) {
        this.likeDao = likeDao;
        this.userService=userService;
        this.postService= postService;
    }


    @Override
    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return likeDao.findByUserIdAndPostId(userId.get(),postId.get());
        }else if (userId.isPresent()){
            return likeDao.findByUserId(userId);
        }else if(postId.isPresent()){
            return likeDao.findByPostId(postId);
        }else
            return likeDao.findAll();
    }

    @Override
    public Like getOneLikeById(Long likeId) {

        return likeDao.findById(likeId).orElse(null);
    }

    @Override
    public void deleteOneLikeById(Long likeId) {
        likeDao.deleteById(likeId);
        
    }

    @Override
    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUser(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(user != null && post != null ){
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeDao.save(likeToSave);
        }else 
            return null;

    }
    
}
