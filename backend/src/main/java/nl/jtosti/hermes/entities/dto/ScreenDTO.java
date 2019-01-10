package nl.jtosti.hermes.entities.dto;

import nl.jtosti.hermes.entities.Screen;

public class ScreenDTO {
    private Long id;
    private String name;
    private int width;
    private int height;

    public ScreenDTO(Screen screen) {
        this.id = screen.getId();
        this.name = screen.getName();
        this.width = screen.getWidth();
        this.height = screen.getHeight();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
