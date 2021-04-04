public class Soleil extends Thread {

    /**délai entre chaque frame du gif*/
    private int delai;
    /**numéro de l'etat indiquant quelle image est utilisée*/
    private int frame;
    /**la hauteur de l'image*/
    private int hauteur;
    /**l'abscisse de l'image*/
    private int position;
    /**Affichage*/
    private Affichage affichage;
    private boolean running = false;

    public Soleil(Affichage affichage) {
        this.delai = 500;
        this.frame = 0;
        this.hauteur = -50;
        this.position = Affichage.getWIDTH()/2;//La position initiale de l'oiseau est situé en dehors de la fenetre
        this.affichage = affichage;
    }

    @Override
    public void run(){
        while (true){
            running = true;
            this.frame = (this.frame + 1)%5;
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

    public int getFrame() {
        return frame;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getPosition() {
        return position;
    }

    public boolean getRunning(){return this.running;}
}
