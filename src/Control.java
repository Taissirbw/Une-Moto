import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Classe Control: effectue les changement dans le modèle et informe
 *                  l'affichage de la modification
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
    public void mouseClicked(MouseEvent e) {
        etat.moveRight(); //Modifie la hauteur
        affichage.repaint(); //actualise l'affichage
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**Methodes héritées de la classe KeyListener**/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); //On évalue la touche pressée
        if(keyCode == KeyEvent.VK_LEFT) //Si c'est la flèche directionnelle gauche
            etat.moveLeft(); //on déplace la moto à gauche
        else if(keyCode == KeyEvent.VK_RIGHT) //Si c'est la flèche directionnelle droite
            etat.moveRight(); //on déplace la moto à droite
        else if(keyCode == KeyEvent.VK_DOWN) //Si c'est la flèche directionnelle bas
            etat.moveDown(); //on déplace la moto en bas
        else if(keyCode == KeyEvent.VK_UP) //Si c'est la flèche directionnelle haut
            etat.moveUp(); //on déplace la moto en haut

        affichage.repaint(); //actualise l'affichage
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}