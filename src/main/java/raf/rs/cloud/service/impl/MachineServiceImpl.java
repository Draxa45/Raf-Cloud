package raf.rs.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raf.rs.cloud.dao.MachineDao;
import raf.rs.cloud.dao.UserDao;
import raf.rs.cloud.model.Machine;
import raf.rs.cloud.model.State;
import raf.rs.cloud.model.User;
import raf.rs.cloud.service.MachineService;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MachineServiceImpl implements MachineService {


    private UserDao userDao;
    private MachineDao mecDao;
    private ExecutorService executor;
    private ArrayList<Long> busy;

    @Autowired
    public MachineServiceImpl(UserDao userDao, MachineDao mecDao) {
        this.userDao = userDao;
        this.mecDao = mecDao;
        this.executor= Executors.newCachedThreadPool();
        this.busy = new ArrayList<>();
    }

    @Override
    public Machine create(long userId, String name) {

        User user = userDao.getById(userId);
        return mecDao.save(new Machine(user,name));
    }

    @Override
    public int start(Long id)  {

        if(busy.contains(id))
            return 0;
        Machine mec = mecDao.getMachineById(id);
        if(mec == null)
            return 1;
        if(mec.getState().equals(State.RUNNING) || !mec.getActive())
            return 0;
        busy.add(id);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    mec.setState(State.RUNNING);
                    mecDao.save(mec);
                    busy.remove(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return 2;
    }

    @Override
    public int restart(Long id) {

        if(busy.contains(id))
            return 0;
        Machine mec = mecDao.getMachineById(id);
        if(mec == null)
            return 1;
        if(mec.getState().equals(State.STOPPED)||!mec.getActive())
            return 0;
        busy.add(id);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    mec.setState(State.STOPPED);
                    Thread.sleep(5000);
                    mec.setState(State.RUNNING);
                    mecDao.save(mec);
                    busy.remove(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return 2;
    }
    @Override
    public int stop(Long id) {

        if(busy.contains(id)) {

            return 0;
        }
        Machine mec = mecDao.getMachineById(id);
        if(mec == null)
            return 1;
        System.out.println(id);
        if(mec.getState().equals(State.STOPPED) || !mec.getActive()) {

            return 0;
        }
        busy.add(id);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(10000);
                    mec.setState(State.STOPPED);
                    mecDao.save(mec);
                    busy.remove(id);
                    System.out.println(busy);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return 2;
    }

    @Override
    public int destroy(Long id) {
        Machine mac = mecDao.getMachineById(id);
        if(mac == null )
            return 1;
        else if(!mac.getActive())
        {
            return 0;
        }
        else {
            mac.setActive(false);
            mecDao.save(mac);
        }
        return 2;
    }

    @Override
    public List<Machine> search(String name, String status, LocalDate from, LocalDate too , Long id) {

        if (name == null)
            name = "";
        User user = userDao.getById(id);
        if (from == null && too == null & status == null)
            return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCase(user,name);
        if(status!= null)
        {
            if(from == null && too != null)
                return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndStateAndDataCreatedIsBetween(user,name,State.valueOf(status),from,too);
            if(from != null && too == null)
                return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndStateAndDataCreatedIsAfter(user,name,State.valueOf(status),from);
            if(from != null && too != null)
                return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndStateAndDataCreatedIsBefore(user,name,State.valueOf(status),too);
            if(from == null && too == null)
                return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndState(user,name,State.valueOf(status));
        }
        else
        {
            if(from == null && too != null)
                return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndDataCreatedIsBetween(user,name,from,too);
            if(from != null && too == null)
                return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndDataCreatedIsAfter(user,name,from);
            if(from != null && too != null)
                return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndDataCreatedIsBefore(user,name,too);
        }


        return mecDao.getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCase(user,name);

    }



}
