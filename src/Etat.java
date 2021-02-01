import java.awt.*;

/** Classe Etat: definit l'état de l'interface, introduit une variable
 *              position qui indique la postion de la moto
 * */
public class Etat {
    /** Vitesse de la moto */
    private float vitesse;

    /** Position de la moto */
    private Point pos = new Point();


    public Etat(){
        // Initialisation de la position de la voiture
        pos.setLocation(Affichage.getWIDTH()/2 - Affichage.getLargeurMoto(),Affichage.getHEIGHT() - Affichage.getLargeurMoto() - 50);

    }

    /** Fait bouger horizontalement la moto à gauche */
    public void moveLeft(){
        if(this.pos.x - Affichage.getMove() < 0) //On évite que la moto sorte du cadre à gauche
            this.pos.x = Affichage.getLargeurMoto()/2; //La moto est deplacée de move-pixel
        else
            this.pos.x -= Affichage.getMove(); //La moto est deplacé à gauche
    }

    /** Fait bouger horizontalement la moto à droite */
    public void moveRight(){
        if((this.pos.x + Affichage.getLargeurMoto()) + Affichage.getMove() > Affichage.getWIDTH()) //On évite que la moto sorte du cadre à gauche
            this.pos.x = Affichage.getWIDTH() - Affichage.getLargeurMoto(); //La moto est deplacée de move-pixel
        else
            this.pos.x += Affichage.getMove(); //La moto est deplacée à droite
    }

    /** Getter de la classe Etat */

    /** Renvoie la vitesse de la moto */
    public float getVitesse() {
        return this.vitesse;
    }

    /** Renvoie la position de la moto */
    public Point getPos() {
        return this.pos;
    }
}