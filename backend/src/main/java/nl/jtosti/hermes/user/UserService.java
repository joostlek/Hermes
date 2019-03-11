package nl.jtosti.hermes.user;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.config.acl.AclServiceInterface;
import nl.jtosti.hermes.config.acl.MyPermission;
import nl.jtosti.hermes.user.exception.EmailAlreadyUsedException;
import nl.jtosti.hermes.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.PrincipalSid;
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
    @PostFilter("hasRole('ADMIN') or filterObject.email == authentication.name")
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        if (this.exists(user.getEmail())) {
            throw new EmailAlreadyUsedException(user.getEmail());
        }
        user.setRoles(Collections.singletonList("USER"));
        User newUser = this.saveUser(user);
        aclService.addUser(user);
        aclService.addPermissionsToObject(newUser, new PrincipalSid(user.getEmail()), MyPermission.ADMINISTRATION);
        return newUser;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(email));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#newUser, 'ADMINISTRATION')")
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
    @PreAuthorize("hasRole('ADMIN') or #user.email == authentication.name")
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#company, 'EMPLOYEE')")
    public List<User> getAllUsersByCompany(Company company) {
        return userRepository.findUsersByCompanyId(company.getId());
    }

    private User saveUser(User user) {
        return this.userRepository.save(user);
    }

}
