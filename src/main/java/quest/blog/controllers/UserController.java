package quest.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quest.blog.business.abstracts.UserService;
import quest.blog.dataAccess.UserDao;
import quest.blog.entities.User;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;
    private UserDao userDao;


    @Autowired
    public UserController(UserService userService,UserDao userDao) {
        this.userService = userService;
        this.userDao=userDao;
    }


    @GetMapping
    public List<User> getAlUser(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveOneUser(user);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId , @RequestBody User newUser){
        //değiştirilmek istenen useri userid kullanarak bulup user değişkenine atıyoruz
        Optional<User> user = userDao.findById(userId);

        //user değişkeni databasede varmı diye kontrol ediyoruz
        if(user.isPresent()){
            //var ise useri founduser değişkenine atıyoruz
            User foundUser = user.get();
            //varolan useri new userin verdiği değişikliklerle yeniden set ediyoruz
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            //useri kaydediyoruz
            userService.saveOneUser(foundUser);
            //ve düzenlenmiş useri döndürüyoruz
            return foundUser;
        }else 
            return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deletOneuser(userId);
    }


}
