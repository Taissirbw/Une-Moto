/** Classe Avancer: gestion de l'avancée de l'ovale sur le parcours par le biais d'un thread
 * */
public class Avancer extends Thread {
    Etat etat; //etat du modele
    Affichage affichage; //affichage dans la fenetre
    public boolean arret = false; //Condition de lancement du thread

    public Avancer(Etat etat, Affichage affichage){
        this.etat = etat;
        this.affichage = affichage;
        this.arret = false;
    }

    @Override
    public void run(){
        while (!this.arret){ //boucle infinie
            this.etat.route.updateRoute();
            this.etat.km += Affichage.getMove();
            affichage.revalidate();
            affichage.repaint(); //actualisation de l'affichage

            try { Thread.sleep(100); } //Pause
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
            }
        }
    }
}
