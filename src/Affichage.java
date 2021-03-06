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

    /**
     * Etat du modèle.
     */
    public Etat etat;

    /**
     * Constantes définissant la taille de la fenêtre de jeu
     */
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;


    /**
     * Véhicule.
     */
    protected BufferedImage moto;
    protected static int largeurMoto = 50;
    protected static int hauteurMoto = 46;
    private static int largeurObstacle = 50;
    private static int hauteurObstacle = 50;

    /**
     * Constante qui traduit de combien de pixel bouge la moto
     */
    private static int move = 20;

    /**
     * Environnement
     */
    private BufferedImage decor;
    private BufferedImage route;
    private BufferedImage gameOver;
    /**
     * Hauteur de la ligne d'horizon. Dépendant de la hauteur de la fenêtre.
     */
    private static final int horizon = HEIGHT / 3;

    /**
     * L'ensemble de points qui constitue la route
     */
    private ArrayList<Point> ligneRoute;
    private ArrayList<Point> ligneRouteG;
    private ArrayList<Point> ligneRouteD;

    private ArrayList<Point> checkpoints;

    private Avancer avance;
    private VueOiseau vue;


    /**
     * CONSTRUCTEUR DE LA CLASSE AFFICHAGE
     *
     * */
    public Affichage(Etat etat) throws IOException {
        this.etat = etat;
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Définit la taille de la fenetre
        moto = ImageIO.read(new File("Assets/moto1.png"));
        decor = ImageIO.read(new File("Assets/horizon2.png"));
        route = ImageIO.read(new File("Assets/planet2.png"));
        gameOver = ImageIO.read(new File("Assets/gameOver.png"));
        ligneRoute = this.etat.route.getLigneRoute();
        ligneRouteD = this.etat.route.getLigneRouteD();
        ligneRouteG = this.etat.route.getLigneRouteG();
        checkpoints = this.etat.route.getCheckpoints();
        this.avance = new Avancer(this.etat, this);
        this.vue = new VueOiseau(this);


    }

    /**
     * affichage
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); //nettoie la zone d'affichage
        this.revalidate();

        g.setColor(Color.BLACK);
        g.drawImage(route, 0, 0, WIDTH, HEIGHT, null);

        afficheRoute(g);
        for (int i = 0; i < this.etat.route.getObstacles().size(); i++) {
            Obstacle o = this.etat.route.getObstacles().get(i);
            if (o.isVisible())
                g.drawImage(o.getImage(), o.getPos().x, o.getPos().y, o.getWidth(), o.getHeight(), null);
        }

        g.drawImage(decor, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(moto, this.etat.getPos().x, this.etat.getPos().y, largeurMoto, hauteurMoto, null);
        g.setColor(Color.WHITE);
        //Affichage du score
        g.setFont(new Font("Verdana", Font.BOLD, 16));
        g.drawString(" Score : " + this.etat.km, WIDTH - WIDTH / 4, 20);

        g.drawString(" Vitesse : " + this.etat.getVitesse() + " km/h", 0, 20);
        if(this.etat.timer.chrono < 7) g.setColor(Color.RED);
        g.drawString(" Temps : " + this.etat.timer.chrono + " s", WIDTH - WIDTH / 4, 40);
        g.setColor(Color.WHITE);
        if(this.etat.getVitesse() < 30) g.setColor(Color.RED);
        g.drawString(" Vitesse : " + this.etat.getVitesse() + " km/h", 0, 20);
        try {
            vue.dessiner(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.etat.testPerdu()) {
            g.clearRect(0,0,WIDTH,HEIGHT);
            g.drawImage(gameOver, 0, 0, WIDTH, HEIGHT, null);
            //Affichage de l'écran de fin avec le score
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER" , WIDTH / 2, HEIGHT / 2);
            g.drawString("Score : " + this.etat.km, WIDTH / 2, HEIGHT / 2 + 50);
        }
    }


    /**
     * Test d'affichage de la route en courbe de Bezier
     */
    public void afficheRoute(Graphics g) {

        /** Création de la courbe de Bézier quadratique pour la ligne droite */
        Graphics2D g2 = (Graphics2D) g;
        //tracage de la ligne entre le premier point et la moitie du premier et deuxieme point
        Point midD = new Point((ligneRouteD.get(1).x + ligneRouteD.get(0).x) / 2, (ligneRouteD.get(1).y + ligneRouteD.get(0).y) / 2);
        g2.setColor(Color.WHITE);
        g2.drawLine(ligneRouteD.get(0).x, ligneRouteD.get(0).y, midD.x, midD.y);

        for (int i = 1; i < ligneRouteD.size() - 1; i++) {
            //on calcule le milieu des points suivants
            Point midD2 = new Point((ligneRouteD.get(i + 1).x + ligneRouteD.get(i).x) / 2, (ligneRouteD.get(i + 1).y + ligneRouteD.get(i).y) / 2);
            //le point suivant devient le point de controle pour la courbe entre les deux milieux des points
            QuadCurve2D courbe = new QuadCurve2D.Double();
            courbe.setCurve(
                    new Point2D.Double(midD.x, midD.y),
                    new Point2D.Double(ligneRouteD.get(i).x, ligneRouteD.get(i).y),
                    new Point2D.Double(midD2.x, midD2.y)
            );
            g2.draw(courbe);
            //on met a jour le milieu
            midD = midD2;
        }
        //on trace le dernier segment à droite
        g2.drawLine(midD.x, midD.y, ligneRouteD.get(ligneRouteD.size() - 1).x, ligneRouteD.get(ligneRouteD.size() - 1).y);


        /** Création de la courbe de Bézier quadratique pour la ligne gauche */
        //tracage de la ligne entre le premier point et la moitie du premier et deuxieme point
        Point midG = new Point((ligneRouteG.get(1).x + ligneRouteG.get(0).x) / 2, (ligneRouteG.get(1).y + ligneRouteG.get(0).y) / 2);
        g2.drawLine(ligneRouteG.get(0).x, ligneRouteG.get(0).y, midG.x, midG.y);

        for (int i = 1; i < ligneRouteG.size() - 1; i++) {
            //on calcule le milieu des points suivants
            Point midG2 = new Point((ligneRouteG.get(i + 1).x + ligneRouteG.get(i).x) / 2, (ligneRouteG.get(i + 1).y + ligneRouteG.get(i).y) / 2);
            //le point suivant devient le point de controle pour la courbe entre les deux milieux des points
            QuadCurve2D courbe = new QuadCurve2D.Double();
            courbe.setCurve(
                    new Point2D.Double(midG.x, midG.y),
                    new Point2D.Double(ligneRouteG.get(i).x, ligneRouteG.get(i).y),
                    new Point2D.Double(midG2.x, midG2.y)
            );
            g2.draw(courbe);
            //on met a jour le milieu
            midG = midG2;
        }
        //on trace le dernier segment à gauche
        g2.drawLine(midG.x, midG.y, ligneRouteG.get(ligneRouteG.size() - 1).x, ligneRouteG.get(ligneRouteG.size() - 1).y);

        //Ligne de point
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(4));

        //Les deux points qui composent la liste des points de controle forme une ligne
        if (!checkpoints.isEmpty())
            g2.drawLine(checkpoints.get(0).x, checkpoints.get(0).y, checkpoints.get(1).x, checkpoints.get(1).y);

    }

    /**
     * ---METHODES D'ACCES AUX ATTRIBUTS DE LA CLASSE AFFICHAGE---
     */

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public BufferedImage getMoto() {
        return moto;
    }

    public static int getLargeurMoto() {
        return largeurMoto;
    }

    public static int getHauteurMoto() {
        return hauteurMoto;
    }

    public static int getMove() {
        return move;
    }

    public BufferedImage getDecor() {
        return decor;
    }

    public BufferedImage getRoute() {
        return route;
    }

    public static int getHorizon() {
        return horizon;
    }

    public Avancer getAvance() {
        return avance;
    }

    public VueOiseau getVue() {
        return vue;
    }

    public static int getLargeurObstacle() {
        return largeurObstacle;
    }

    public static int getHauteurObstacle() {
        return hauteurObstacle;
    }

    public ArrayList<Point> getLigneRouteD() {
        return ligneRouteD;
    }

    public ArrayList<Point> getLigneRouteG() {
        return ligneRouteG;
    }
}
