import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/** Classe Affichage : Les éléments a affiché dans la fenetre créée */
public class Affichage extends JPanel {

    /**Etat du modèle.*/
    public Etat etat;

    /** Constantes définissant la taille de la fenêtre de jeu*/
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;


    /**Véhicule.*/
    private BufferedImage moto;
    private static int largeurMoto = 50;
    private static int hauteurMoto = 46;
    /**Constante qui traduit de combien de pixel bouge la moto*/
    private static int move = 20;

    /**Environnement*/
    private BufferedImage decor;
    private BufferedImage route;
    /**Hauteur de la ligne d'horizon. Dépendant de la hauteur de la fenêtre.*/
    private static final int horizon = HEIGHT / 3;

    /**L'ensemble de points qui constitue la route*/
    private ArrayList<Point> ligneRoute;
    private ArrayList<Point> ligneRouteG;
    private ArrayList<Point> ligneRouteD;


    /** Constructeur */
    public Affichage(Etat etat) throws IOException {
        this.etat = etat;
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Définit la taille de la fenetre
        moto = ImageIO.read(new File("Assets/moto1.png"));
        decor = ImageIO.read(new File("Assets/horizon.png"));
        route = ImageIO.read(new File("Assets/route.png"));
        ligneRoute = this.etat.route.getLigneRoute();
        ligneRouteD = this.etat.route.getLigneRouteD();
        ligneRouteG = this.etat.route.getLigneRouteG();
    }

    /** affichage */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //nettoie la zone d'affichage
        g.drawImage(decor, 0, 0, WIDTH, horizon, null);
        g.drawImage(route, 0, horizon, WIDTH, HEIGHT - horizon, null);

        g.setColor(Color.BLACK);
        for (int i = 0; i < ligneRoute.size() - 1; i++) {
            //Construction de la ligne du centre
            g.drawLine( (int) ligneRoute.get(i).getX(), (int) ligneRoute.get(i).getY(),
                    (int) ligneRoute.get(i + 1).getX(), (int) ligneRoute.get(i + 1).getY());

            //Construction de la ligne de droite
            g.drawLine( (int) ligneRouteD.get(i).getX(), (int) ligneRouteD.get(i).getY(),
                    (int) ligneRouteD.get(i + 1).getX(), (int) ligneRouteD.get(i + 1).getY());

            //Construction de la ligne de gauche
            g.drawLine( (int) ligneRouteG.get(i).getX(), (int) ligneRouteG.get(i).getY(),
                    (int) ligneRouteG.get(i + 1).getX(), (int) ligneRouteG.get(i + 1).getY());
        }
        g.drawImage(moto, this.etat.getPos().x, this.etat.getPos().y, largeurMoto, hauteurMoto, null);
    }

    /**---METHODES D'ACCES AUX VARIABLES DE LA CLASSE AFFICHAGE---*/

    public static int getWIDTH() { return WIDTH; }
    public static int getHEIGHT() { return HEIGHT; }

    public BufferedImage getMoto() { return moto; }
    public static int getLargeurMoto() { return largeurMoto; }
    public static int getHauteurMoto() { return hauteurMoto; }
    public static int getMove() { return move; }

    public BufferedImage getDecor() { return decor; }
    public BufferedImage getRoute() { return route; }
    public static int getHorizon() { return horizon; }


}