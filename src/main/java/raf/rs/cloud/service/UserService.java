package raf.rs.cloud.service;

import raf.rs.cloud.model.User;

public interface UserService {

     User get(Long id);

     User save(String username,String password,String firstName, String lastName);

     boolean delete(Long id);

}
