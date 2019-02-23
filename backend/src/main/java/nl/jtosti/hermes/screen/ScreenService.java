package nl.jtosti.hermes.screen;

import nl.jtosti.hermes.screen.exception.ScreenIsNotToReceivePasswordException;
import nl.jtosti.hermes.screen.exception.ScreenNotFoundException;
import nl.jtosti.hermes.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScreenService implements ScreenServiceInterface {

    private final ScreenRepository screenRepository;

    @Autowired
    public ScreenService(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    @Override
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElseThrow(() -> new ScreenNotFoundException(id));
    }

    @Override
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Override
    public List<Screen> getScreensByLocationId(Long id) {
        return screenRepository.findAllByLocationId(id);
    }

    @Override
    public boolean exists(Long id) {
        return screenRepository.existsById(id);
    }

    @Override
    public Screen save(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public Screen updateScreen(Screen newScreen) {
        return screenRepository.findById(newScreen.getId())
                .map(screen -> {
                    screen.setHeight(newScreen.getHeight());
                    screen.setWidth(newScreen.getWidth());
                    screen.setName(newScreen.getName());
                    return screenRepository.save(screen);
                })
                .orElseThrow(
                        () -> new ScreenNotFoundException(newScreen.getId())
                );
    }

    @Override
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
        this.save(screen);
        return password;
    }
}
