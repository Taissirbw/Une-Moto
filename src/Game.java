import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/** Classe principale du jeu */
public class Game {

    public static void main(String [] args) throws IOException {
        Etat etat = new Etat(); //Creation du modele
        Affichage affichage = new Affichage(etat); //Création de la vue
        Control control = new Control(etat, affichage); //Création du contrôle

        //Création de la fenetre
        JFrame fenetre = new JFrame("Une Moto !");
        fenetre.addKeyListener(control); //gestion des fleches du clavier
        fenetre.add(affichage); //La vue est ajoutée à la fenetre
        fenetre.setLocation(300,36);
        fenetre.pack();
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}