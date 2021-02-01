import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/** Classe principale */
public class Main {

    public static void main(String [] args) throws IOException {
        //Création de la fenetre
        JFrame fenetre = new JFrame("Une Moto !");
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Etat etat = new Etat(); //Creation du modele
        Affichage affichage = new Affichage(etat); //Création de la vue
        fenetre.add(affichage); //La vue est ajoutée à la fenetre
        fenetre.pack();
        fenetre.setVisible(true);
    }
}