package raf.rs.cloud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import raf.rs.cloud.model.User;


@Repository
public interface UserDao extends JpaRepository<User,Long> {

   User getById(long id);
   User getUserByUsernameAndPassword(String username , String pass);

}
