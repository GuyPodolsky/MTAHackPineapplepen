import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class User implements Serializable {
    private String name;
    private Node pic;
    private int id;
    private static int counter=1; //starts from one and grow each user
    private String saverFile = "Server\\src\\resource\\saver.bin";
    //first time ctor    // save to text first time use
    public User(String name, String photoLink) {
        this.id = counter;
        counter++;
        this.name = name;
        try { // grab a smurf from the path if it is there otherwise just use a green square.
            pic = new ImageView(new Image(photoLink)); //"http://bluebuddies.com/gallery/title/jpg/Smurf_Fun_100x100.jpg"
        } catch (Exception e) {
            pic = new ImageView(new Image("default_pic.jpg"));
        }
        pic.setTranslateZ(150);
        pic.setOpacity(0.7);
        pic.setMouseTransparent(true);

        saver(); // save to file
    }

    public User() {}

    //other uses- read from the file
    public User(ObjectInputStream inn) {
        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(saverFile))) {
            User temp = (User) in.readObject(); ///////////////////////////// supposed to be the user in
            this.id = temp.id;
            this.name = temp.name;
            this.pic = temp.pic;

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

    public void setPic(Node pic) {
        this.pic = pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getPic() {
        return pic;
    }
}


