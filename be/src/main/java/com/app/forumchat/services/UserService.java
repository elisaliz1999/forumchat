package com.app.forumchat.services;

import com.app.forumchat.models.User;
import com.app.forumchat.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo ur;

    public User save(User user){
        if(ur.findByEmail(user.getEmail()) == null){
            if(ur.findByUsername(user.getUsername()) == null){
                return ur.save(user);
            }else {
                System.out.println("Username already in use. :(");
            }
        }else{
            System.out.println("This email is already associated with an account.");
        }
        return null;
    }

    public String delete(long id){
        Optional<User> userOpt;
        userOpt = ur.findById(id);
        if(userOpt.isPresent()){
            ur.deleteById(id);
            return "User deleted";
        }
        return null;
    }

    public User update(long id, User user){
        Optional<User> userOpt;
        userOpt = ur.findById(id);
        if(userOpt.isPresent()){
            User userNew = userOpt.get();
            userNew.setName(user.getName());
            userNew.setLast_name(user.getLast_name());
            userNew.setBirthday(user.getBirthday());
            return ur.save(userNew);
        }
        return null;
    }
    /**
     * TODO forgottenPassword()
     *      editPassword()
     */
}
