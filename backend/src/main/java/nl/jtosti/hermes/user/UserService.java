package nl.jtosti.hermes.user;

import nl.jtosti.hermes.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id)
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public boolean exists(String email) {
        return userRepository.findByEmail(email).orElse(null) != null;
    }

    @Override
    public User save(User user) {
//        user.setRoles(Collections.singletonList("USER"));
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(email));
    }

    @Override
    public User updateUser(User newUser) {
        return userRepository.findById(newUser.getId())
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    return save(user);
                })
                .orElseThrow(
                        () -> new UserNotFoundException(newUser.getId())
                );
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsersByCompanyId(Long companyId) {
        return userRepository.findUsersByCompanyId(companyId);
    }

}
