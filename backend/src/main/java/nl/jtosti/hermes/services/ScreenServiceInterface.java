package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.Screen;

import java.util.List;

public interface ScreenServiceInterface {
    public Screen getScreenById(Long id);

    public List<Screen> getAllScreens();

    public List<Screen> getScreensByLocationId(Long id);

    public boolean exists(Long id);

    public Screen save(Screen screen);

    public Screen updateScreen(Screen screen);

    public void deleteScreen(Long id);

    String registerScreen(Long id);
}
