package raf.rs.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raf.rs.cloud.dao.UserDao;
import raf.rs.cloud.model.LogInResponse;
import raf.rs.cloud.model.User;
import raf.rs.cloud.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private UserDao dao ;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }



    @Override
    public User save(String username, String password, String firstName, String lastName) {
        return  dao.save(new User(username,password,firstName,lastName));
    }

    @Override
    public LogInResponse logIn(String username, String pass) {
        User user= dao.getUserByUsernameAndPassword(username,pass);
        if(user == null)
            return new LogInResponse(user,false);
        else
            return new LogInResponse(user,true);


    }


}
