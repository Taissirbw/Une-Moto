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
    private int largeurMoto = 60;
    private int hauteurMoto = 46;
    private BufferedImage decor;
    private BufferedImage route;
    /**Hauteur de la ligne d'horizon. Dépendant de la hauteur de la fenêtre.*/
    private final int horizon = HEIGHT / 3;

    /**Permet de tester l'affichage dans un premier temps. A remplacer par les attributs de gestions de la moto
     * qui se trouveront dans le modèle.*/
    private int posMotoX = WIDTH/2 - largeurMoto;
    private int posMotoY = HEIGHT - hauteurMoto - 50;



    /**Etat du modèle.*/
    public Etat etat;


    /** Constructeur */
    public Affichage(Etat etat) throws IOException {
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Taille de la fenetre
        this.etat = etat;
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
        g.drawImage(moto, posMotoX, posMotoY, largeurMoto, hauteurMoto, null);

    }

}