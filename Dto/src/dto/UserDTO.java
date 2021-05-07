package dto;

import javafx.scene.Node;
import javafx.scene.image.Image;


import java.awt.image.BufferedImage;
import java.io.File;

public class UserDTO {
    private final String name;
    private File pic;
    private final int id;

    public UserDTO(String name, File pic, int id) {
        this.name = name;
        this.pic = pic;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public File getPic() {
        return pic;
    }

    public int getId() {
        return id;
    }
}