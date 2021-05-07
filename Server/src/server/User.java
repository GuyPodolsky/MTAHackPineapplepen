package server;
import dto.UserDTO;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private BufferedImage pic; //node
    private int id;
    private static int idGenerator =1; //starts from one and grow each user
    private boolean isHost;         // only when pressed new meeting
    private final String saverFile = "Server\\src\\resource\\saver.bin";

    public String getName() { return name; }
    public BufferedImage getPic() { return pic; } //node
    public int getId() { return id; }
    public boolean getIsHost() { return isHost; }

    public void setName(String name) { this.name = name; }
    public void setPic(BufferedImage pic) { this.pic = pic; }
    public void setHost(boolean host) { isHost = host; }

    //first time ctor    // save to text first time use
   /* public User(String name, String photoLink) {
        this.id = idGenerator;
        idGenerator++;
        this.name = name;
        this.isHost = false;
        try { // grab a smurf from the path if it is there otherwise just use a green square.
            pic = new ImageView(new Image(photoLink)); //"http://bluebuddies.com/gallery/title/jpg/Smurf_Fun_100x100.jpg"
        } catch (Exception e) {
            pic = new ImageView(new Image("resource/default_pic.jpg"));
        }
        pic.setTranslateZ(150);
        pic.setOpacity(0.7);
        pic.setMouseTransparent(true);

        saver(); // save to file
    }*/

    public User(String name, File photo) {
        this.id = idGenerator;
        idGenerator++;
        this.name = name;
        this.isHost = false;

        //BufferedImage bImage = null;
        try {
            pic = ImageIO.read(photo);
        } catch (IOException e) {
        System.out.println("Exception occured :" + e.getMessage());
    }
        saver(); // save to file
    }

    public User() {} // maybe delete

    //other uses- read from the file
    public User(ObjectInputStream inn) {
        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(saverFile))) {
            User temp = (User) in.readObject(); ///////////////////////////// supposed to be the user in
            this.id = temp.getId();
            this.name = temp.getName();
            this.pic = temp.getPic();
            this.isHost = false;

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! 1Something unexpected occurred:");
            System.out.println(ex.getMessage());
            this.id = 0;
            this.name = null;
            this.pic = null;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR! 2Something unexpected occurred:");
            System.out.println(ex.getMessage());
            this.id = 0;
            this.name = null;
            this.pic = null;
        }
    }

  //first time use - save to file
  public void saver() {
      try (ObjectOutputStream out =
                   new ObjectOutputStream(
                           new FileOutputStream(saverFile))) {
          out.writeObject(this);
          out.flush();
      } catch (FileNotFoundException ex) {
          System.out.println("ERROR! 1Something unexpected occurred:");
          System.out.println(ex.getMessage());
      } catch (IOException ex) {
          System.out.println("ERROR! 2Something unexpected occurred:");
          System.out.println(ex.getMessage());
      }
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(pic, user.pic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pic, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pic=" + pic +
                ", id=" + id +
                '}';
    }

    public UserDTO toDto() {
        return new UserDTO(this.name, this.pic, this.id);
    }
}


