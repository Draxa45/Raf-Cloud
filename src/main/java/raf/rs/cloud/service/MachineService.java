package raf.rs.cloud.service;


import raf.rs.cloud.model.Machine;
import raf.rs.cloud.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MachineService {

     Machine create (long userId,String name);

     int start (Long id) throws ExecutionException, InterruptedException;

     int restart (Long id);

     int stop (Long id);

     int destroy (Long id);

     List<Machine> search(String name , String status, LocalDate from , LocalDate too , Long id);


}
