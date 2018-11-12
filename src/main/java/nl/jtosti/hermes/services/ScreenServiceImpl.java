package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Screen;
import nl.jtosti.hermes.repositories.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScreenServiceImpl implements ScreenService {

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
}
