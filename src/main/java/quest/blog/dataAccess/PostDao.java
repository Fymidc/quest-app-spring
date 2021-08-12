package quest.blog.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import quest.blog.entities.Post;

@Transactional
public interface PostDao extends JpaRepository<Post,Long> {

    List<Post> findByUserId(Long userId);
    
}
