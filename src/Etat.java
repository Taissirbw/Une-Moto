import java.awt.*;

/** Classe Etat: definit l'état de l'interface, introduit une variable
 *  position qui indique la position courante de la moto.
 * */
public class Etat {

    /** Vitesse de la moto */
    private float vitesse;
    /** Position de la moto */
    private Point pos = new Point();

    /** Route */
    Route route;

    /** Animation de la route */
    Avancer avancer;

    /** Score */
    int km;


    public Etat(){
        //Initialisation de la position de la voiture au début de la partie.
        pos.setLocation(Affichage.getWIDTH()/2 - Affichage.getLargeurMoto(),
                Affichage.getHEIGHT() - Affichage.getHauteurMoto() - 20);
        this.route = new Route(); //création de la route
        this.km = 0;
    }

    /*---Déplacement de la moto...---*/
    /**Vers la gauche.*/
    public void moveLeft(){
        if(this.pos.x - Affichage.getMove() < 0) //On évite que la moto sorte du cadre à gauche
            this.pos.x = 0;
        else this.pos.x -= Affichage.getMove(); //La moto est deplacée à gauche.
    }
    /**Vers la droite.*/
    public void moveRight(){
        if((this.pos.x + Affichage.getLargeurMoto()) + Affichage.getMove() > Affichage.getWIDTH())
            this.pos.x = Affichage.getWIDTH() - Affichage.getLargeurMoto();
        else this.pos.x += Affichage.getMove();
    }
    /**Vers le bas.*/
    public void moveDown(){
        if((this.pos.y + Affichage.getHauteurMoto()) + Affichage.getMove() > Affichage.getHEIGHT())
            this.pos.y = Affichage.getHEIGHT() -  Affichage.getHauteurMoto();
        else this.pos.y += Affichage.getMove();
    }
    /**Vers le haut.*/
    public void moveUp(){
        /*Pour donner une impression d'horizon, on permet au haut la moto d'aller légèrement plus
        *haut que la ligne d'horizon, tant que le bas de l'image ne dépasse pas celle-ci.
        * */
        if((this.pos.y - Affichage.getMove()) < Affichage.getHorizon() - Affichage.getHauteurMoto()/2)
            this.pos.y = Affichage.getHorizon() - Affichage.getHauteurMoto()/2;
        else this.pos.y -= Affichage.getMove();
    }



    /**---METHODES D'ACCES AUX VARIABLES DE LA CLASSE ETAT---*/

    public float getVitesse() { return this.vitesse; }
    public Point getPos() { return this.pos; }
}
