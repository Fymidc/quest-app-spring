package quest.blog.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.blog.business.abstracts.PostService;
import quest.blog.business.abstracts.UserService;
import quest.blog.dataAccess.PostDao;
import quest.blog.entities.Post;
import quest.blog.entities.User;
import quest.blog.entities.dtos.PostCreateRequest;
import quest.blog.entities.dtos.PostUpdateRequest;

@Service
public class PostManager implements PostService{

    private PostDao postDao;
    private UserService userService;
    

    @Autowired
    public PostManager(PostDao postDao,UserService userService) {
        this.postDao = postDao;
        this.userService=userService;
    }


    @Override
    public List<Post> getAllPost(Optional<Long> userId) {
        if(userId.isPresent())
            return postDao.findByUserId(userId.get());
        return postDao.findAll();
    }


    @Override
    public Post getOnePostById(Long postId) {

        return postDao.findById(postId).orElse(null);
    }


    @Override
    public Post createOnePost(PostCreateRequest newPostRequest) {
        //databasedeki useri user değişkenine attık
        User user = userService.getOneUser(newPostRequest.getUserId());
        if(user==null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        //yukarıda buldugumuz useri olştrulan post objesinin user kısmına ekledik
        toSave.setUser(user);
        return postDao.save(toSave);
    }


    @Override
    public Post updateOnePostById(Long postId , PostUpdateRequest updatePost) {
       Optional<Post> post = postDao.findById(postId);
       if(post.isPresent()){
           Post toUpdate = new Post();
           toUpdate.setText(updatePost.getText());
           toUpdate.setTitle(updatePost.getTitle());
            postDao.save(toUpdate);
           return toUpdate;
       }
        return null;
    }


    @Override
    public void deleteOnePostById(Long postId) {
        postDao.deleteById(postId);
        
    }
    
}
