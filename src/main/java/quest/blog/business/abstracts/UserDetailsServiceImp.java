package quest.blog.business.abstracts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import quest.blog.dataAccess.UserDao;
import quest.blog.entities.User;
import quest.blog.security.JwtUserDetails;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    UserDao userDao;

    public UserDetailsServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails userById(Long id){
        User user = userDao.findById(id).get();
        return JwtUserDetails.create(user);
    }
    
}
