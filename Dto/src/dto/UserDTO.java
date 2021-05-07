package dto;

import javafx.scene.Node;

import java.awt.image.BufferedImage;

public class UserDTO {
    private final String name;
    private final BufferedImage pic;
    private final int id;

    public UserDTO(String name, BufferedImage pic, int id) {
        this.name = name;
        this.pic = pic;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getPic() {
        return pic;
    }

    public int getId() {
        return id;
    }
}
