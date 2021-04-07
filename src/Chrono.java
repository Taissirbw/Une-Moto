/** Classe Chrono: indique le temps restant  la moto
 *  pour passer un point de controle
 * */
public class Chrono extends Thread {

    int start = 8; //temps initial au lancement du jeu
    int chrono;
    public boolean arret = false; //Condition de lancement du thread

    /**
     * CONSTRUCTEUR DE LA CLASSE CHRONO
     *
     * */
    public Chrono(){
        this.chrono = start;
    }


    @Override
    public void run(){
        while (chrono >0){ //boucle infinie
            chrono -= 1;
            try { Thread.sleep(1000); } //Pause
            catch (Exception e) {
                Thread.currentThread().interrupt(); //Interruption du thread
            }
        }
    }
}
