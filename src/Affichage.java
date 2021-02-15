import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
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

    Avancer avance;


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
        this.avance = new Avancer(this.etat,this);


    }

    /** affichage */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //nettoie la zone d'affichage
        g.setColor(Color.BLACK);
        g.drawImage(route, 0, horizon, WIDTH, HEIGHT - horizon, null);

        //c'est censé colorer la route en noir
        /*Polygon polygon = new Polygon();

        for(Point p : ligneRouteD) polygon.addPoint(p.x,p.y);
        for(Point p : ligneRoute) polygon.addPoint(p.x,p.y);
        for(Point p : ligneRouteG) polygon.addPoint(p.x,p.y);

        g.fillPolygon(polygon);
        g.drawPolygon(polygon);*/

        /*for (int i = 0; i < ligneRoute.size() - 1; i++) {
            //Construction de la ligne du centre
            g.drawLine( (int) ligneRoute.get(i).getX(), (int) ligneRoute.get(i).getY(),
                    (int) ligneRoute.get(i + 1).getX(), (int) ligneRoute.get(i + 1).getY());

            //Construction de la ligne de droite
            g.drawLine( (int) ligneRouteD.get(i).getX(), (int) ligneRouteD.get(i).getY(),
                    (int) ligneRouteD.get(i + 1).getX(), (int) ligneRouteD.get(i + 1).getY());

            //Construction de la ligne de gauche
            g.drawLine( (int) ligneRouteG.get(i).getX(), (int) ligneRouteG.get(i).getY(),
                    (int) ligneRouteG.get(i + 1).getX(), (int) ligneRouteG.get(i + 1).getY());
        }*/

        afficheRoute(g);
        g.drawImage(decor, 0, 0, WIDTH, horizon, null);
        g.drawImage(moto, this.etat.getPos().x, this.etat.getPos().y, largeurMoto, hauteurMoto, null);

        //Affichage du score
        g.setFont(new Font("Verdana", Font.BOLD, 16));
        g.drawString(" Score : " + this.etat.km, WIDTH - WIDTH/4, 20);
    }


    /** Test d'affichage de la route en courbe de Bezier */
    public void afficheRoute(Graphics g){

        /*QuadCurve2D courbe = new QuadCurve2D.Double();
        Point2D.Double debut = new Point2D.Double(ligneRoute.get(0).getX(), ligneRoute.get(0).getY()); //point de début
        Point2D.Double ctrl = new Point2D.Double(ligneRoute.get(1).getX(), ligneRoute.get(1).getY()); //point de control de la courbe
        Point2D.Double fin = new Point2D.Double(ligneRoute.get(2).getX(), ligneRoute.get(2).getY()); //point de fin
        courbe.setCurve(debut,ctrl,fin);

        /*QuadCurve2D courbeG = new QuadCurve2D.Double();
        Point2D.Double debutG = new Point2D.Double(ligneRouteG.get(0).getX(), ligneRouteG.get(0).getY());
        Point2D.Double ctrlG = new Point2D.Double(ligneRouteG.get(1).getX(), ligneRouteG.get(1).getY());
        Point2D.Double finG = new Point2D.Double(ligneRouteG.get(2).getX(), ligneRouteG.get(2).getY());
        courbeG.setCurve(debutG,ctrlG,finG);

        QuadCurve2D courbeD = new QuadCurve2D.Double();
        Point2D.Double debutD = new Point2D.Double(ligneRouteD.get(0).getX(), ligneRouteD.get(0).getY());
        Point2D.Double ctrlD = new Point2D.Double(ligneRouteD.get(1).getX(), ligneRouteD.get(1).getY());
        Point2D.Double finD = new Point2D.Double(ligneRouteD.get(2).getX(), ligneRouteD.get(2).getY());
        courbeD.setCurve(debutD,ctrlD,finD);*/


        //Création des courbes de Bézier pour chaque liste ligneRoute
        Graphics2D g2 = (Graphics2D)g;
        for(int i = 0; i < ligneRoute.size()-2; i+=2){
            QuadCurve2D courbe = new QuadCurve2D.Double();
            Point2D.Double debut = new Point2D.Double(ligneRoute.get(i).getX(), ligneRoute.get(i).getY()); //point de début
            Point2D.Double ctrl = new Point2D.Double(ligneRoute.get(i+1).getX(), ligneRoute.get(i+1).getY()); //point de control de la courbe
            Point2D.Double fin = new Point2D.Double(ligneRoute.get(i+2).getX(), ligneRoute.get(i+2).getY()); //point de fin
            courbe.setCurve(debut,ctrl,fin);

            g2.draw(courbe);
        }

        for(int i = 0; i < ligneRoute.size()-2; i+=2){
            QuadCurve2D courbeG = new QuadCurve2D.Double();
            Point2D.Double debutG = new Point2D.Double(ligneRouteG.get(i).getX(), ligneRouteG.get(i).getY()); //point de début
            Point2D.Double ctrlG = new Point2D.Double(ligneRouteG.get(i+1).getX(), ligneRouteG.get(i+1).getY()); //point de control de la courbe
            Point2D.Double finG = new Point2D.Double(ligneRouteG.get(i+2).getX(), ligneRouteG.get(i+2).getY()); //point de fin
            courbeG.setCurve(debutG,ctrlG,finG);

            g2.draw(courbeG);
        }

        for(int i = 0; i < ligneRoute.size()-2; i+=2){
            QuadCurve2D courbeD = new QuadCurve2D.Double();
            Point2D.Double debutD = new Point2D.Double(ligneRouteD.get(i).getX(), ligneRouteD.get(i).getY()); //point de début
            Point2D.Double ctrlD = new Point2D.Double(ligneRouteD.get(i+1).getX(), ligneRouteD.get(i+1).getY()); //point de control de la courbe
            Point2D.Double finD = new Point2D.Double(ligneRouteD.get(i+2).getX(), ligneRouteD.get(i+2).getY()); //point de fin
            courbeD.setCurve(debutD,ctrlD,finD);

            g2.draw(courbeD);
        }


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