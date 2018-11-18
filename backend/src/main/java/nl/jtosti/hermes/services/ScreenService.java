package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Screen;

import java.util.List;

public interface ScreenService {
    public Screen getScreenById(Long id);

    public List<Screen> getAllScreens();

    public List<Screen> getScreensByLocationId(Long id);

    public boolean exists(Long id);

    public Screen save(Screen screen);
}
