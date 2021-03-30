import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Classe Control: effectue les changement dans le modèle et informe
 *  l'affichage de la modification.
 * */
public class Control implements MouseListener, KeyListener {

    /** Etat du modele */
    public Etat etat;
    /**Affichage du modèle.*/
    public Affichage affichage;

    /**Constructeur.*/
    public Control(Etat etat, Affichage affichage){
        this.etat = etat;
        this.affichage = affichage;
    }

    /**Methodes héritées de la classe MouseListener**/
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

    /**Methodes héritées de la classe KeyListener**/
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); //On évalue la touche pressée
        /*En fonction de la touche reconnue, on indique au modèle qu'il faut effectuer une modification
         * de la position de la moto.*/
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                etat.left = true;
                break;
            case KeyEvent.VK_RIGHT:
                etat.right = true;
                break;
            case KeyEvent.VK_DOWN:
                //etat.moveDown();
                break;
            case KeyEvent.VK_UP:
                if(!this.affichage.getAvance().isAlive() && !this.affichage.getAvance().arret) {
                    this.affichage.getAvance().start(); //lancement du thread
                    this.etat.timer.start(); //lancement du chronometre
                }
                //etat.moveUp();

                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode(); //On évalue la touche pressée
        /*En fonction de la touche reconnue, on indique au modèle qu'il faut effectuer une modification
         * de la position de la moto.*/
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                etat.left = false;
                break;
            case KeyEvent.VK_RIGHT:
                etat.right = false;
                break;
            case KeyEvent.VK_DOWN:
                //etat.moveDown();
                break;
            case KeyEvent.VK_UP:
                //etat.moveUp();

                break;
            default:
                break;
        }
    }
}