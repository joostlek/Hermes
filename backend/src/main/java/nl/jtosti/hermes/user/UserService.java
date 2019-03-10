package nl.jtosti.hermes.user;

import nl.jtosti.hermes.company.exception.CompanyNotFoundException;
import nl.jtosti.hermes.config.acl.AclServiceInterface;
import nl.jtosti.hermes.user.exception.EmailAlreadyUsedException;
import nl.jtosti.hermes.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    private final AclServiceInterface aclService;

    @Autowired
    public UserService(UserRepository userRepository, AclServiceInterface aclService) {
        this.userRepository = userRepository;
        this.aclService = aclService;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id)
        );
    }

    @Override
    @PostFilter("hasPermission(filterObject, 'ADMINISTRATION')")
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public boolean exists(String email) {
        return userRepository.findByEmail(email).orElse(null) != null;
    }

    @Override
    public User save(User user) {
        if (this.exists(user.getEmail())) {
            throw new EmailAlreadyUsedException(user.getEmail());
        }
        user.setRoles(Collections.singletonList("USER"));
        User newUser = this.saveUser(user);
        aclService.addUser(user);
        return newUser;
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
                    return saveUser(user);
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
        List<User> users = userRepository.findUsersByCompanyId(companyId);
        if (users.isEmpty()) {
            throw new CompanyNotFoundException(companyId);
        }
        return users;
    }

    private User saveUser(User user) {
        return this.userRepository.save(user);
    }

}
