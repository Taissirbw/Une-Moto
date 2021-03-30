import java.io.IOException;
import java.util.Random;

/** Classe Avancer: gestion de l'avancée de l'ovale sur le parcours par le biais d'un thread
 * */
public class Avancer extends Thread {
    Etat etat; //etat du modele
    Affichage affichage; //affichage dans la fenetre
    public boolean arret = false; //Condition de lancement du thread
    public int vitesseMax = 80;
    public float vitesse;

    Random rand = new Random();

    public Avancer(Etat etat, Affichage affichage){
        this.etat = etat;
        this.affichage = affichage;
        this.arret = false;
    }

    /** Calcul de la vitesse par rapport a la moitié de la fenetre**/
    public float calculVitesse () {


        if ((this.etat.getPos().x < affichage.getLigneRouteD().get(0).x) &&
                this.etat.getPos().x > affichage.getLigneRouteG().get(0).x) {
            // + l'abscisse de la moto est éloignée du centre + le délai du thread augmente
            return (vitesseMax * (480 / (this.etat.getPos().x + this.affichage.getLargeurMoto())));
        } else {
            int dist;
            if (this.etat.getPos().x >= affichage.getLigneRouteD().get(0).x) {
                dist = - (affichage.getLigneRouteD().get(0).x - this.etat.getPos().x);
            } else {
                dist = affichage.getLigneRouteG().get(0).x - this.etat.getPos().x;
            }
            return (vitesseMax * (480 / (this.etat.getPos().x + this.affichage.getLargeurMoto()))) + dist/5;

            /**if (this.etat.getPos().x < Affichage.getWIDTH()/2) {
             // + l'abscisse de la moto est éloignée du centre + le délai du thread augmente
             return (vitesseMax * ( 480 / (this.etat.getPos().x + this.affichage.getLargeurMoto()))) + 10;
             }else {
             //Complementaire
             int tmp = Affichage.getWIDTH() - this.etat.getPos().x;
             return (vitesseMax * ((float)(Affichage.getWIDTH()/2)/ tmp))+10;
             }**/
        }
    }

    @Override
    public void run(){
        while (!this.etat.testPerdu()){ //boucle principale du jeu
            this.vitesse = calculVitesse() +  etat.penalite;
            if (etat.penalite > 0) etat.penalite --;
            this.arret = true;
            //mise a jour de la route
            this.etat.route.updateRoute();

            if (etat.left) this.etat.moveLeft();
            if (etat.right) this.etat.moveRight();

            //mise a jour des points de controle
            if(!this.etat.route.getCheckpoints().isEmpty())
                this.etat.route.updateCheckpoint();
            //recréation des points de controle apres une certaine distance
            if(this.etat.km%(Affichage.getMove()*50) <= 0) this.etat.route.createCheckpoint();
            this.etat.km += Affichage.getMove(); //mise a jour des km parcourues (score)
            this.etat.route.updateObstacles();

            try {
                this.etat.route.createObstacles();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //le timer est crédité quand on passe un point de controle
            if(!this.etat.route.getCheckpoints().isEmpty() && this.etat.timer.isAlive() &&
                    this.etat.getPos().y < this.etat.route.getCheckpoints().get(0).y) this.etat.timer.chrono += 2;

            //Vérifie les collisions avec le décor
            this.etat.checkCollision();


            //actualisation de l'affichage
            //affichage.revalidate();
            //affichage.repaint();

            try { Thread.sleep((long) vitesse);} //Pause thread
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
            }
        }
    }
}
