package nl.jtosti.hermes.screen;

import nl.jtosti.hermes.location.Location;

import java.util.List;

public interface ScreenServiceInterface {
    Screen getScreenById(Long id);

    List<Screen> getAllScreens();

    List<Screen> getScreensByLocation(Location location);

    boolean exists(Long id);

    Screen addNewScreen(Screen screen, Location location);

    Screen updateScreen(Screen screen);

    void deleteScreen(Long id);

    String registerScreen(Long id);
}
