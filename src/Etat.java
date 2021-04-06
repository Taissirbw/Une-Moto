import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/** Classe Etat: definit l'état de l'interface, introduit une variable
 *  position qui indique la position courante de la moto.
 * */
public class Etat {

    /** Etat de la partie */
    boolean gameOver = false;
    /** Position de la moto */
    private Point pos = new Point();
    /** Déplacement en cours de la moto */
    public boolean left = false;
    public boolean right = false;
    /** Route */
    Route route;
    /** Vitesse */
    protected int vitesse;
    /** Score */
    int km;
    /** Pénalités obtenues lorsque la moto
     * entre en collision avec un obstacle.*/
    float penalite;
    /** Chronomètre */
    Chrono timer;

    /**
     * CONSTRUCTEUR DE LA CLASSE ETAT
     *
     * */
    public Etat() throws IOException {
        //Initialisation de la position de la voiture au début de la partie.
        pos.setLocation(Affichage.getWIDTH()/2 - Affichage.getLargeurMoto(),
                Affichage.getHEIGHT() - Affichage.getHauteurMoto() - 20);
        this.route = new Route(); //création de la route
        this.km = 0;
        this.penalite = 0;
        this.timer = new Chrono();
        this.vitesse = 80;

    }

    /*public String testPerdu(){
        String tmp = new String();
        if (this.timer.chrono <= 0) {
            tmp = "Temps écoulé";
            gameOver = true;
        }
        if (gameOver) tmp = "Fin de partie";
        return "GAME OVER : " + tmp;
    }*/
    public boolean testPerdu(){

        return this.timer.chrono <= 0 || this.vitesse <= 0;
    }

    /** ---Actualisation de la position de la moto lorsqu'elle se déplace...--- **/
    /** ...vers la gauche.*/
    public void moveLeft(){
        if(this.pos.x - Affichage.getMove() < 1) //On évite que la moto sorte du cadre à gauche
            this.pos.x = 1;
        else this.pos.x -= Affichage.getMove(); //La moto est deplacée à gauche.
    }

    /** ...vers la droite.*/
    public void moveRight(){
        if((this.pos.x + Affichage.getLargeurMoto()) + Affichage.getMove() > Affichage.getWIDTH())
            this.pos.x = Affichage.getWIDTH() - Affichage.getLargeurMoto();
        else this.pos.x += Affichage.getMove();
    }


    /** Test la collision entre la moto et un élément du décor */
    public void checkCollision(){
        //On stock une variable avec un rectangle représentant les dimensions de la moto
        Rectangle pos = new Rectangle(this.pos.x,this.pos.y,Affichage.getLargeurMoto(),Affichage.getHauteurMoto());
        //Pour chaque obstacle de la route
        for(int i = 0; i < this.route.getObstacles().size(); i++){
            Obstacle o = this.route.getObstacles().get(i);
            //On stock une variable avec un rectangle représentant les dimensions de l'obstacle
            Rectangle dimO = o.getBorder();

            if (dimO.intersects(pos) && o.isVisible()) { //Vérifie s les deux rectangle entre en collision
                o.visible = false;
                penalite += 250;
                vitesse -= penalite/13;
            }
        }
    }



    /**---METHODES D'ACCES AUX VARIABLES DE LA CLASSE ETAT---*/
    public Point getPos() { return this.pos; }

    public float getVitesse() {
        return vitesse;
    }
}
