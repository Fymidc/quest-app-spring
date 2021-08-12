package quest.blog.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.blog.entities.Comment;

public interface CommentDao extends JpaRepository<Comment,Long> {

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);
    
}
