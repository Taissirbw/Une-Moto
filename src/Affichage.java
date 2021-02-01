import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Classe Affichage : Les éléments a affiché dans la fenetre créée */
public class Affichage extends JPanel {

    /** Constantes */
    public static final int WIDTH = 600; //Largeur de la fenetre
    public static final int HEIGHT = 400; //Hauteur de la fenetre

    public static int x = 200; //Abscisse
    public static int y = 200; //Ordonné
    //public static int width = 125; //largeur de l'ovale
    //public static int height = 90; //hauteur de l'ovale
    public static final int saut = 40; //taille du saut lors du clic souris

    public Etat etat; //etat du modele


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