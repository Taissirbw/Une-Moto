import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** Classe Obstacle: créer les obstacles de la route
 *
 * */
public class Obstacle {
    /**Position*/
    private Point pos = new Point();
    /**Dimension*/
    private int width;
    private int height;
    /**Visibilité de l'obstacle à l'écran*/
    public boolean visible;
    /**Image*/
    private BufferedImage image;

    public Obstacle(int x, int y) throws IOException {
        this.pos.setLocation(x,y);
        this.visible = true;
        image = ImageIO.read(new File("Assets/moto0.png"));
        this.width = Affichage.getLargeurObstacle();
        this.height = Affichage.getHauteurObstacle();
    }


    /**Renvoie les dimensions du rectangle associé à l'image*/
    public Rectangle getBorder(){
        return new Rectangle(this.pos.x,this.pos.y,this.width,this.height);
    }

    /**---METHODES D'ACCES AUX VARIABLES DE LA CLASSE ROUTE---*/
    public Point getPos() {
        return pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public BufferedImage getImage() {
        return image;
    }
}
