package pe.isil.svc.service;

import org.springframework.stereotype.Service;
import pe.isil.svc.model.User;
import pe.isil.svc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements CrudService<User,Integer> {

    private final UserRepository userRepository ;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        User oldUser = findById(user.getId());
        user.setPassword(oldUser.getPassword());
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            user.getVet().setStatus(false);
            user.setPassword("");
        }
        userRepository.save(user);
    }

    public void restoreUser(User user){
        if (userRepository.findById(user.getId()).isPresent()) user.getVet().setStatus(true);
        userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setPassword("");
        });

        return users;
    }

    public User userExists(User userLogin){
        User user = userRepository.findByUsername(userLogin.getUsername());
        if (user != null || !user.getVet().getStatus()){
            if (Objects.equals(user.getPassword(), userLogin.getPassword()))
                return user;
            else return null;
        }
        else return null;
    }

    public Boolean usernameExists(User user){
        if (userRepository.findByUsername(user.getUsername()) != null) return true;
        else return false;
    }

    public User getUserFromRequest(HttpServletRequest request){
        String authToken = request.getHeader("Authorization");
        if (authToken == null) return null;
        String userString =  new String(Base64.getDecoder()
                .decode(authToken));
        User user = new User(userString.split(":")[0],userString.split(":")[1]);
        return userExists(user);
    }

    public Boolean isUserAdmin(HttpServletRequest request){
        User user = getUserFromRequest(request);
        if (user != null && user.getVet().getRole().equals("ADMIN")) return true;
        else return false;
    }

    public List<User> findByStatus(Boolean isActive){
        return userRepository.findAllByVet_RoleAndVetStatusOrderByIdAsc("USER", isActive);
    }


}
