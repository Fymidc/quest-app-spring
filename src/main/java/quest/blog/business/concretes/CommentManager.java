package quest.blog.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.blog.business.abstracts.CommentService;
import quest.blog.business.abstracts.PostService;
import quest.blog.business.abstracts.UserService;
import quest.blog.dataAccess.CommentDao;
import quest.blog.entities.Comment;
import quest.blog.entities.Post;
import quest.blog.entities.User;
import quest.blog.entities.dtos.CommentCreateRequest;
import quest.blog.entities.dtos.CommentUpdateRequest;

@Service
public class CommentManager implements CommentService {
    
    private CommentDao commentDao;
    private UserService userService;
    private PostService postService;

    @Autowired
    public CommentManager(CommentDao commentDao, UserService userService,
     PostService postService) {
        this.commentDao = commentDao;
        this.userService=userService;
        this.postService= postService;
    }

    @Override
    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentDao.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
            return commentDao.findByUserId(userId.get());
        }else if (postId.isPresent()){
            return commentDao.findByPostId(postId.get());
        }else
            return commentDao.findAll();
        
    }

    @Override
    public Comment getOneCommentById(Long commentId) {
        return commentDao.findById(commentId).orElse(null);
    }

    @Override
    public Comment createOneComment(CommentCreateRequest request) {
        User user = userService.getOneUser(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        
        if(user != null && post != null ){
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            //üstte yakaladığımız postu ekledik
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            return commentDao.save(commentToSave);
        }else
            return null;
    }

    @Override
    public Comment updateOneCommentById(Long commentId,CommentUpdateRequest request) {
        Optional<Comment> comment = commentDao.findById(commentId);
        if(comment.isPresent()){
            //bulunan comment yeni Comment değişkene atandı 
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentDao.save(commentToUpdate);
        }else 
            return null;
    }

    @Override
    public void deleteOneCommentById(Long commentId) {
        commentDao.deleteById(commentId);
        
    }


}
