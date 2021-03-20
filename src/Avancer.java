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
            this.etat.route.updateCheckpoint();//mise a jour des points de controle
            this.etat.km += Affichage.getMove(); //mise a jour des km parcourues (score)
            affichage.revalidate();
            affichage.repaint(); //actualisation de l'affichage
            try { Thread.sleep((long) calculVitesse()); } //Pause
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
            }
        }
    }
}
