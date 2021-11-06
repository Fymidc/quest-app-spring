package quest.blog.business.abstracts;

import java.util.List;

import quest.blog.entities.User;

public interface UserService {
    
    List<User> getAllUsers();
    User saveOneUser(User user);

    User getOneUser(Long userId);

    User updateOneUser(Long userId,User newuser);

    public void deletOneuser(Long userId);
    User getOneUserByUserName(String userName);
}
