package dto;

import javafx.scene.Node;

public class UserDTO {
    private final String name;
    private final Node pic;
    private final int id;

    public UserDTO(String name, Node pic, int id) {
        this.name = name;
        this.pic = pic;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Node getPic() {
        return pic;
    }

    public int getId() {
        return id;
    }
}
