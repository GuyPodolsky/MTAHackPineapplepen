package dto;

import javafx.scene.Node;
import javafx.scene.image.Image;


import java.awt.image.BufferedImage;

public class UserDTO {
    private final String name;
    private final Image pic;
    private final int id;

    public UserDTO(String name, Image pic, int id) {
        this.name = name;
        this.pic = pic;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Image getPic() {
        return pic;
    }

    public int getId() {
        return id;
    }
}
