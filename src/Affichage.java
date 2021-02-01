import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/** Classe Affichage : Les éléments a affiché dans la fenetre créée */
public class Affichage extends JPanel {

    /** Constantes définissant la taille de la fenêtre de jeu*/
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;

    /**Tampon pour stocker une image représentant le véhicule.*/
    private BufferedImage moto;
    private static int largeurMoto = 60;
    private static int hauteurMoto = 46;
    private BufferedImage decor;
    private BufferedImage route;
    /**Hauteur de la ligne d'horizon. Dépendant de la hauteur de la fenêtre.*/
    private static final int horizon = HEIGHT / 3;

    /**Permet de tester l'affichage dans un premier temps. A remplacer par les attributs de gestions de la moto
     * qui se trouveront dans le modèle.*/
    private int posMotoX = WIDTH/2 - largeurMoto;
    private int posMotoY = HEIGHT - hauteurMoto - 50;

    /**Constante qui traduit de combien de pixel bouge la moto*/
    private static int move = 50;

    /**Etat du modèle.*/
    public Etat etat;

    /**Controleur du modèle.*/
    public Control control;


    /** Constructeur */
    public Affichage(Etat etat) throws IOException {
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Taille de la fenetre
        this.etat = etat;
        this.control = new Control(etat,this); //création du controler
        //addKeyListener(control); //Gestion du clavier par la création du controleur
        addMouseListener(new Control(etat,this)); //Gestion de la souris par la création du controleur
        moto = ImageIO.read(new File("Assets/moto.png"));
        decor = ImageIO.read(new File("Assets/horizon.png"));
        route = ImageIO.read(new File("Assets/route.png"));
    }

    /** affichage */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //nettoie la zone d'affichage
        g.drawImage(decor, 0, 0, WIDTH, horizon, null);
        g.drawImage(route, 0, horizon, WIDTH, HEIGHT - horizon, null);
        g.drawImage(moto, this.etat.getPos().x, this.etat.getPos().y, largeurMoto, hauteurMoto, null);

    }

    /** Getter de la classe Affichage */

    /** renvoie la largeur de la fenetre */
    public static int getWIDTH() {
        return WIDTH;
    }

    /** renvoie la hauteur de la fenetre */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /** renvoie la moto */
    public BufferedImage getMoto() {
        return moto;
    }

    /** renvoie la largeur de la moto */
    public static int getLargeurMoto() {
        return largeurMoto;
    }

    /** renvoie la hauteur de la moto */
    public static int getHauteurMoto() {
        return hauteurMoto;
    }

    /** renvoie le decor */
    public BufferedImage getDecor() {
        return decor;
    }

    /** renvoie la route */
    public BufferedImage getRoute() {
        return route;
    }

    /** renvoie l'horizon */
    public static int getHorizon() {
        return horizon;
    }

    public int getPosMotoX() {
        return posMotoX;
    }

    public int getPosMotoY() {
        return posMotoY;
    }

    /** renvoie la constante move */
    public static int getMove() {
        return move;
    }
}