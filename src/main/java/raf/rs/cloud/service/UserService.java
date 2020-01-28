package raf.rs.cloud.service;

import raf.rs.cloud.model.LogInResponse;
import raf.rs.cloud.model.User;

public interface UserService {



     User save(String username,String password,String firstName, String lastName);

     LogInResponse logIn(String username, String pass);


}
