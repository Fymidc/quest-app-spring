package quest.blog.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.blog.entities.User;

public interface UserDao extends JpaRepository<User , Long> {

    User findByUserName(String username);
    
}
