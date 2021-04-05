import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/** Classe Control: effectue les changement dans le modèle et informe
 *  l'affichage de la modification.
 * */
public class Control implements MouseListener, KeyListener {

    /** Etat du modele */
    public Etat etat;
    /**Affichage du modèle.*/
    public Affichage affichage;

    /**
     * CONSTRUCTEUR DE LA CLASSE CONTROL
     *
     * */
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
        /** En fonction de la touche reconnue, on indique au modèle que la moto se déplace dans une direction.
         * L'arrêt du déplacement est geré par la méthode keyReleased. */
        int keyCode = e.getKeyCode(); //On évalue la touche pressée
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                etat.left = true;
                    try {
                        affichage.moto = ImageIO.read(new File("Assets/moto1G.png"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                break;
            case KeyEvent.VK_RIGHT:
                etat.right = true;
                try {
                    affichage.moto = ImageIO.read(new File("Assets/moto1D.png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case KeyEvent.VK_UP:
                /**Si le jeu n'est pas encore lancé, on démarre le thread de la boucle principale du jeu, ainsi
                 * que le timer.*/
                if(!this.affichage.getAvance().isAlive() && !this.affichage.getAvance().inGame) {
                    this.affichage.getAvance().start(); //lancement du thread
                    this.etat.timer.start(); //lancement du chronometre
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
        /* Lorsque la touche est relachée, on indique au modèle que la moto n'est plus en train
         * de se déplacer.*/
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                etat.left = false;
                try {
                    affichage.moto = ImageIO.read(new File("Assets/moto1.png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case KeyEvent.VK_RIGHT:
                etat.right = false;
                try {
                    affichage.moto = ImageIO.read(new File("Assets/moto1.png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}