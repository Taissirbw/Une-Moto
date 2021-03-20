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
    public int calculVitesse (){
        if (this.etat.getPos().x < Affichage.getWIDTH()/2)
                return vitesseMax * ((Affichage.getWIDTH()/2)/this.etat.getPos().x);
        else {
            //Complementaire
            int tmp = Affichage.getWIDTH() - this.etat.getPos().x;
            return (vitesseMax * ((Affichage.getWIDTH()/2)/ tmp));
        }

    }

    @Override
    public void run(){
        while (!this.arret){ //boucle infinie
            this.etat.route.updateRoute();
            this.etat.km += Affichage.getMove();
            affichage.revalidate();
            affichage.repaint(); //actualisation de l'affichage
            System.out.println(this.etat.getPos());
            System.out.println(Affichage.getWIDTH()/2);
            System.out.println(calculVitesse());
            try { Thread.sleep(calculVitesse()); } //Pause
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
            }
        }
    }
}
