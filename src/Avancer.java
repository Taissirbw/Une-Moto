import java.io.IOException;
import java.util.Random;

/** Classe Avancer: gestion de l'avancée de la moto et l'actualisation de la route par le biais d'un thread
 * */
public class Avancer extends Thread {

    Etat etat; //etat du modele
    Affichage affichage; //affichage dans la fenetre
    public boolean inGame = false; //Condition de lancement du thread
    public float vitesseMin = 300;
    public float vitesseMax = 80;
    public float vitesse;
    private int credit;


    /**
     * CONSTRUCTEUR DE LA CLASSE AVANCER
     *
     * */
    public Avancer(Etat etat, Affichage affichage){
        this.etat = etat;
        this.affichage = affichage;
        this.inGame = false;
        this.credit = 6;
    }

    /** Calcul de la vitesse par rapport a la moitié de la fenetre**/
    public float calculVitesse () {

        /** Si la moto se trouve entre les deux bords de la route (sur la piste)*/
        if ((this.etat.getPos().x < affichage.getLigneRouteD().get(0).x) &&
                this.etat.getPos().x > affichage.getLigneRouteG().get(0).x) {
            if(this.etat.vitesse < vitesseMax) this.etat.vitesse+=2;
            return (vitesseMax * (480 / (this.etat.getPos().x + this.affichage.getLargeurMoto())));
        } else {
            /** Sinon, si la moto est sur le hors piste, la vitesse du thread qui la fait avancer diminue.*/

            /** On calcule dans dist la distance entre la moto et la piste. Plus cette distance est
             * grande, plus la vitesse de la moto est pénalisée.*/
            int dist;
            if(this.etat.vitesse > 0) this.etat.vitesse-=2;
            else this.etat.vitesse = 0;
            if (this.etat.getPos().x > affichage.getLigneRouteD().get(0).x) {
                /** Si la moto est à droite de la piste*/
                dist = this.etat.getPos().x - affichage.getLigneRouteD().get(0).x;
                int tmp = Affichage.getWIDTH() - this.etat.getPos().x;
                return (vitesseMax * ((float)(Affichage.getWIDTH()/2)/ tmp)) + dist/5;
            } else {
                /** Si la moto est à gauche de la piste*/
                dist = affichage.getLigneRouteG().get(0).x - this.etat.getPos().x;
            }
            return (vitesseMax * (480 / (float) (this.etat.getPos().x + (float) Affichage.getLargeurMoto())))
                    + (float) dist/5;
        }

    }

    /** ---- BOUCLE PRINCIPALE ---- **/
    @Override
    public void run(){
        while (!etat.testPerdu()){
            /*On calcule la vitesse à laquelle le thread va s'executer. Celle-ci dépend
              de la position de la moto par rapport à la piste. On ajoute au résultat obtenu les pénalités,
              qui correspondent au ralentissement causé par une collision avec un obstacle.*/
            this.vitesse = calculVitesse() +  etat.penalite;
            //System.out.print("Vitesse : " + this.vitesse + " ");
            if (this.etat.km > 2000 && this.vitesse > this.vitesseMin) etat.gameOver = true;

            if (etat.penalite > 0) etat.penalite -=50;
            this.inGame = true;
            //mise a jour de la route
            this.etat.route.updateRoute();

            //Si la moto est dans un mouvement, on signale à l'etat qu'il faut
            //effectuer le mouvement adéquat.
            if (etat.left) this.etat.moveLeft();
            if (etat.right) this.etat.moveRight();

            /* Gestion des points de contrôle */
            if(!this.etat.route.getCheckpoints().isEmpty()) //mise a jour des points de controle existants
                this.etat.route.updateCheckpoint();
            //recréation des points de controle apres une certaine distance :
            if(this.etat.km%(Affichage.getMove()*50) <= 0) this.etat.route.createCheckpoint();
            //le timer est crédité quand on passe un point de controle :
            if(!this.etat.route.getCheckpoints().isEmpty() && this.etat.timer.isAlive() &&
                    this.etat.getPos().y < this.etat.route.getCheckpoints().get(0).y){
                if (credit > 1){
                    this.etat.timer.chrono += credit; //Un nombre de crédit est attribué au chronometre
                    credit--; //Le nombre de crédit attribué diminue en passant de plus en plus de points de controle
                }else
                    this.etat.timer.chrono += credit;
            }

            /* Gestion du score : mise à jour des km parcourus*/
            this.etat.km += Affichage.getMove();

            /* Gestion des Obstacles */
            this.etat.route.updateObstacles(); //mise à jour des obstacles existants
            try {
                this.etat.route.createObstacles(); //Création de nouveaux obstacles
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Vérifie les collisions avec le décor, notamment pour détecter la collision avec un obstacle.
            this.etat.checkCollision();
            System.out.println(this.etat.vitesse);


            try { Thread.sleep((long) vitesse);} //Pause thread
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
            }
        }
    }
}
