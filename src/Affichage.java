import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/** Classe Affichage : Les éléments a affiché dans la fenetre créée */
public class Affichage extends JPanel {

    /** Constantes définissant la taille de la fenêtre de jeu*/
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    /**Tampon pour stocker une image représentant le véhicule.*/
    private BufferedImage vehicule;
    private BufferedImage decor;
    /**Hauteur de la ligne d'horizon. Dépendant de la hauteur de la fenêtre.*/
    private final int horizon = HEIGHT / 3;



    /**Etat du modèle.*/
    public Etat etat;


    /** Constructeur */
    public Affichage(Etat etat) {

        setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Taille de la fenetre
        this.etat = etat;
        addMouseListener(new Control(etat,this)); //Gestion de la souris par la création du controleur
    }

    /** affichage */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //nettoie la zone d'affichage
        //g.drawOval(x,etat.getHauteur(),this.width,this.height); //dessine l'ovale dans la fenetre
    }

}