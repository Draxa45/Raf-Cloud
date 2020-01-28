package raf.rs.cloud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import raf.rs.cloud.model.Machine;
import raf.rs.cloud.model.State;
import raf.rs.cloud.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MachineDao extends JpaRepository<Machine, Long> {

     Machine getMachineById(long id);

     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCase(User user,String name);

     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndState(User user, String name, State status);
     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndStateAndDataCreatedIsBetween(User user, String name, State status, LocalDate from , LocalDate too);
     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndStateAndDataCreatedIsBefore(User user, String name, State status, LocalDate too);
     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndStateAndDataCreatedIsAfter(User user, String name, State status, LocalDate from );

     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndDataCreatedIsBetween(User user, String name,  LocalDate from , LocalDate too);
     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndDataCreatedIsBefore(User user, String name,  LocalDate too);
     List<Machine> getMachinesByCreatedByAndActiveIsTrueAndNameContainingIgnoreCaseAndDataCreatedIsAfter(User user, String name, LocalDate from );




}
