package raf.rs.cloud.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.rs.cloud.model.CreateRequest;
import raf.rs.cloud.model.Machine;
import raf.rs.cloud.service.MachineService;


import java.time.LocalDate;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/machine")
@CrossOrigin
public class MachineApi {

    private MachineService service;

    @Autowired
    public MachineApi(MachineService service) {
        this.service = service;
    }

    @PostMapping("/create")

    public Machine create (@RequestBody CreateRequest req)
    {

        return service.create(req.getId(),req.getName());
    }
    @GetMapping("/start")
    public @ResponseBody
    ResponseEntity<?> start (@RequestParam Long id) throws ExecutionException, InterruptedException {

        int flag = service.start(id);

        if(flag == 2)
        {
            return ResponseEntity.ok("Ok");
        }
        else if (flag == 0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation currently not possible");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine Not Found");
        }


    }
    @GetMapping("/stop")
    public @ResponseBody
    ResponseEntity<?> stop (@RequestParam Long id) throws ExecutionException, InterruptedException{

        int flag = service.stop(id);

        System.out.println(flag);
        if(flag == 2)
        {
            return ResponseEntity.ok("Ok");
        }
        else if (flag == 1)
        {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine Not Found");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation currently not possible");
        }

    }
    @GetMapping("/reset")
    public @ResponseBody
    ResponseEntity<?> reset (@RequestParam Long id) throws ExecutionException, InterruptedException{

        int flag = service.restart(id);

        System.out.println(flag);
        if(flag == 2)
        {
            return ResponseEntity.ok("Ok");
        }
        else if (flag == 1)
        {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine Not Found");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation currently not possible");
        }

    }
    @GetMapping("/destroy")
    public @ResponseBody
    ResponseEntity<?> destroy (@RequestParam Long id ) throws ExecutionException, InterruptedException{

        int flag = service.destroy(id);

        System.out.println(flag);
        if(flag == 2)
        {
            return ResponseEntity.ok("Ok");
        }
        else if (flag == 1)
        {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine Not Found");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation currently not possible");
        }

    }
    @PostMapping("/search")
    public @ResponseBody
    List<Machine> search(@RequestParam(required = false) String name , @RequestParam long  userId, @RequestParam(required = false) String  state, @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from , @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate too)
    {
        return service.search(name,state,from,too,userId);
    }


}
