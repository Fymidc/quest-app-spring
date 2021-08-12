package quest.blog.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.blog.business.abstracts.UserService;
import quest.blog.dataAccess.UserDao;
import quest.blog.entities.User;


@Service
public class UserManager implements UserService{


    private UserDao userDao;
    

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        
        return userDao.findAll();
    }

    @Override
    public User saveOneUser(User user) {
        
        return userDao.save(user);
    }

    @Override
    public User getOneUser(Long userId) {
        
        return userDao.findById(userId).orElse(null);
    }

    @Override
    public User updateOneUser(Long userId, User newuser) {
        Optional<User> user = userDao.findById(userId);

        //user değişkeni databasede varmı diye kontrol ediyoruz
        if(user.isPresent()){
            //var ise useri founduser değişkenine atıyoruz
            User foundUser = user.get();
            //varolan useri new userin verdiği değişikliklerle yeniden set ediyoruz
            foundUser.setUserName(newuser.getUserName());
            foundUser.setPassword(newuser.getPassword());
            //useri kaydediyoruz
            userDao.save(foundUser);
            //ve düzenlenmiş useri döndürüyoruz
            return foundUser;
        }else 
            return null;
    }

    @Override
    public void deletOneuser(Long userId) {
        userDao.deleteById(userId);
        
    }
    
}
