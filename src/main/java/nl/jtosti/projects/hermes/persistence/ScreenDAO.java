package nl.jtosti.projects.hermes.persistence;

import nl.jtosti.projects.hermes.models.Screen;

import java.util.List;

public interface ScreenDAO {
    Screen get(int id);
    Screen save(Screen screen);
    Screen update(Screen screen);
    boolean delete(Screen screen);
    List<Screen> getAll();
}
