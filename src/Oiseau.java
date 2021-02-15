public class Oiseau extends Thread {

    /**délai entre chaque frame du gif*/
    private int delai;
    /**numéro de l'etat indiquant quelle image est utilisée*/
    private int etat;
    /**la hauteur de l'image*/
    private int hauteur;
    /**l'abscisse de l'image*/
    private int position;
    /**Affichage*/
    private Affichage affichage;
    private boolean running = false;

    public Oiseau(Affichage affichage) {
        this.delai = 200;
        this.etat = 0;
        this.hauteur = 20;
        this.position = 0;
        this.affichage = affichage;
    }

    @Override
    public void run(){
        while (this.position <= Affichage.getWIDTH()){

            running = true;
            this.etat = (this.etat + 1)%7;
            this.position += 10;
            affichage.revalidate();
            affichage.repaint();
            try { Thread.sleep(delai); } //Pause
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
                break;
            }
        }
    }

    /**---METHODES D'ACCES AUX VARIABLES DE LA CLASSE OISEAU---*/

    public int getDelai() {
        return delai;
    }

    public int getEtat() {
        return etat;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getPosition() {
        return position;
    }

    public boolean getRunning(){return this.running;}
}
