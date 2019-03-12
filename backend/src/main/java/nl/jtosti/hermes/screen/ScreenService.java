package nl.jtosti.hermes.screen;

import nl.jtosti.hermes.config.acl.AclServiceInterface;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.screen.exception.ScreenIsNotToReceivePasswordException;
import nl.jtosti.hermes.screen.exception.ScreenNotFoundException;
import nl.jtosti.hermes.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScreenService implements ScreenServiceInterface {

    private final ScreenRepository screenRepository;

    private final AclServiceInterface aclService;

    @Autowired
    public ScreenService(ScreenRepository screenRepository, AclServiceInterface aclService) {
        this.screenRepository = screenRepository;
        this.aclService = aclService;
    }

    @Override
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElseThrow(() -> new ScreenNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#location.company, 'EMPLOYEE')")
    public List<Screen> getScreensByLocation(Location location) {
        return screenRepository.findAllByLocation(location);
    }

    @Override
    public boolean exists(Long id) {
        return screenRepository.existsById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Screen addNewScreen(Screen screen, Location location) {
        screen.setLocation(location);
        screen.setToReceivePassword(true);
        return saveScreen(screen);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Screen updateScreen(Screen newScreen) {
        return screenRepository.findById(newScreen.getId())
                .map(screen -> {
                    screen.setHeight(newScreen.getHeight());
                    screen.setWidth(newScreen.getWidth());
                    screen.setName(newScreen.getName());
                    return saveScreen(screen);
                })
                .orElseThrow(
                        () -> new ScreenNotFoundException(newScreen.getId())
                );
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteScreen(Long id) {
        screenRepository.deleteById(id);
    }

    @Override
    public String registerScreen(Long id) {
        Screen screen = this.getScreenById(id);
        if (!screen.isToReceivePassword()) {
            throw new ScreenIsNotToReceivePasswordException(id);
        }
        String password = PasswordGenerator.generatePassword();
        screen.setPassword(password);
        this.saveScreen(screen);
        aclService.addScreen(screen);
        return password;
    }

    private Screen saveScreen(Screen screen) {
        return this.screenRepository.save(screen);
    }
}
