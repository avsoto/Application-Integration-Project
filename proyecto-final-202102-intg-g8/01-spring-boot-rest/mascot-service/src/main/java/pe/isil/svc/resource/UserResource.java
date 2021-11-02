package pe.isil.svc.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.svc.model.User;
import pe.isil.svc.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        User loggedUser = userService.userExists(user);
        if(loggedUser == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(loggedUser.getVet(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity getAllActiveUsers(HttpServletRequest request){
        //if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<User> users = userService.findByStatus(true);
        if(users == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/users/inactive")
    public ResponseEntity getAllInactiveUsers(HttpServletRequest request){
        //if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<User> users = userService.findByStatus(false);
        if(users == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity getUser(HttpServletRequest request) {
        User user = userService.getUserFromRequest(request);
        if(user == null) return new ResponseEntity(HttpStatus.NO_CONTENT);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(HttpServletRequest request,
                                     @RequestBody User user) {
        //if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        //Username tiene que ser unico
        if (userService.usernameExists(user)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        userService.create(user);
        if(userService.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(HttpServletRequest request,
                                     @RequestBody User user) {
        //if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(userService.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        userService.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity removeUser(HttpServletRequest request,
                                     @PathVariable Integer id) {
       // if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(userService.findById(id) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/user/restore")
    public ResponseEntity restoreUser(HttpServletRequest request,
                                      @RequestBody User user) {
        //if (!userService.isUserAdmin(request)) return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(userService.findById(user.getId()) == null) return new ResponseEntity(HttpStatus.NO_CONTENT);
        userService.restoreUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }


}
