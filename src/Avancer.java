/** Classe Avancer: gestion de l'avancée de l'ovale sur le parcours par le biais d'un thread
 * */
public class Avancer extends Thread {
    Etat etat; //etat du modele
    Affichage affichage; //affichage dans la fenetre
    public boolean arret = false; //Condition de lancement du thread
    public int vitesseMax = 80;

    public Avancer(Etat etat, Affichage affichage){
        this.etat = etat;
        this.affichage = affichage;
        this.arret = false;
    }

    /** Calcul de la vitesse par rapport a la moitié de la fenetre**/
    public float calculVitesse (){

        if (this.etat.getPos().x < Affichage.getWIDTH()/2)
                // + l'abscisse de la moto est éloigné du centre + le délai du thread augmente
                return vitesseMax * ((float)(Affichage.getWIDTH()/2)/this.etat.getPos().x);
        else {
            //Complementaire
            int tmp = Affichage.getWIDTH() - this.etat.getPos().x;
            return (vitesseMax * ((float)(Affichage.getWIDTH()/2)/ tmp))+10;
        }

    }

    @Override
    public void run(){
        while (!this.arret){ //boucle infinie
            this.etat.route.updateRoute(); //mise a jour de la route

            if(!this.etat.route.getCheckpoints().isEmpty())
                this.etat.route.updateCheckpoint();//mise a jour des points de controle

            //recréation des points de controle apres une certaine distance
            if(this.etat.km%(Affichage.getMove()*100) == 0) this.etat.route.createCheckpoint();
            this.etat.km += Affichage.getMove(); //mise a jour des km parcourues (score)

            //le timer est crédité quand on passe un point de controle
            if(!this.etat.route.getCheckpoints().isEmpty() && this.etat.timer.isAlive() &&
                    this.etat.getPos().y < this.etat.route.getCheckpoints().get(0).y) this.etat.timer.chrono += 2;

            ////actualisation de l'affichage
            affichage.revalidate();
            affichage.repaint();

            try { Thread.sleep((long) calculVitesse()); } //Pause thread
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
            }
        }
    }
}
