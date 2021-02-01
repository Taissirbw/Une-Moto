import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Classe Control: effectue les changement dans le modèle et informe
 *                  l'affichage de la modification
 * */
public class Control implements MouseListener {
    //Attributs
    public Etat etat;
    public Affichage affichage;

    //Constructeur
    public Control(Etat etat, Affichage affichage){
        this.etat = etat;
        this.affichage = affichage;
    }

    /**Methodes héritées de la classe MouseListener**/
    @Override
    public void mouseClicked(MouseEvent e) {
        //etat.jump(); //Modifie la hauteur
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
}