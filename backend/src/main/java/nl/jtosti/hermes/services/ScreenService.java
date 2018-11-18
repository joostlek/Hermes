package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.exceptions.ScreenNotFoundException;
import nl.jtosti.hermes.repositories.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScreenService implements ScreenServiceInterface {

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElse(null);
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
    public Screen updateScreen(Screen newScreen, Long id) {
        return screenRepository.findById(id)
                .map(screen -> {
                    screen.setHeight(newScreen.getHeight());
                    screen.setWidth(newScreen.getWidth());
                    screen.setName(newScreen.getName());
                    return screenRepository.save(screen);
                })
                .orElseThrow(
                        () -> new ScreenNotFoundException(id)
                );
    }

    @Override
    public void deleteScreen(Long id) {
        screenRepository.deleteById(id);
    }
}
