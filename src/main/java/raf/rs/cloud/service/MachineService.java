package raf.rs.cloud.service;


import raf.rs.cloud.model.Machine;
import raf.rs.cloud.model.User;

import java.util.concurrent.ExecutionException;

public interface MachineService {

     Machine create (long userId);

     int start (Long id) throws ExecutionException, InterruptedException;

     int restart (Long id);

     int stop (Long id);

     int destroy (Long id);


}
