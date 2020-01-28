package raf.rs.cloud.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.rs.cloud.model.LogInResponse;
import raf.rs.cloud.model.User;
import raf.rs.cloud.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserApi {

    private UserService service;

    @Autowired
    public UserApi(UserService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public @ResponseBody
    User save(@RequestParam String username , @RequestParam String password, @RequestParam String firstName , @RequestParam String lastName)
    {
        return service.save(username,password,firstName,lastName);
    }
    @GetMapping("login")
    public @ResponseBody
    LogInResponse logIn(@RequestParam String username , @RequestParam String pass)
    {
        return service.logIn(username,pass);
    }

}
