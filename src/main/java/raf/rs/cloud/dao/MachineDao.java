package raf.rs.cloud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import raf.rs.cloud.model.Machine;

@Repository
public interface MachineDao extends JpaRepository<Machine, Long> {

     Machine getMachineById(long id);


}
