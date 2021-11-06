package quest.blog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quest.blog.business.abstracts.UserService;
import quest.blog.entities.User;
import quest.blog.entities.dtos.UserRequest;
import quest.blog.security.JwtTokenProvide;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private AuthenticationManager authenticationManager;

    private JwtTokenProvide jwtTokenProvide;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvide jwtTokenProvide,
            UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvide = jwtTokenProvide;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login (@RequestBody UserRequest loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvide.generateJwtToken(auth);
        return "Bearer " + jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest registerUser){
        if(userService.getOneUserByUserName(registerUser.getUserName()) !=null ){
            return new ResponseEntity<>("Username already in use",HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(registerUser.getUserName());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        userService.saveOneUser(user);
        return new ResponseEntity<>("user succesfully registered ",HttpStatus.CREATED);
    }

//50.25 de kaldım

}
