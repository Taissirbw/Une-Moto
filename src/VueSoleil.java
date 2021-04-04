import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** Classe VueSoleil: Thread de l'animation du soleil créé
 * */
public class VueSoleil {

    /** liste des frame d'une image */
    ArrayList<BufferedImage> frames;

    /** Affichage */
    Affichage affichage;

    /** liste de sol a afficher*/
    ArrayList<Soleil> sun;

    /** Dimension de l'image */
    private int width = 200;
    private int height = 200;

    public VueSoleil(Affichage affichage) {
        this.frames = new ArrayList<>();
        this.affichage = affichage;
        this.sun = new ArrayList<>();
    }

    /** Créé un nouveau soleil et l'ajoute a la liste de soleil */
    public void addSoleil(){
        sun.add(new Soleil(affichage));
    }

    /** affichage des images */
    public void dessiner(Graphics g) throws IOException {

        addSoleil();
        Soleil s = sun.get(0);
        if(!s.getRunning()){
            s.start(); //lancement du thread
            loadEtat(); //chargement des etats de l'image
        }

        //affichage de l'image
        g.drawImage(frames.get(s.getFrame()), s.getPosition() - width/2, s.getHauteur(),width,height, null);

        //actualisation de l'affichage
        affichage.revalidate();
        affichage.repaint();

    }

    /** charge dans la liste etat les differents etat de l'image */
    public void loadEtat() throws IOException {
        //Chargement des différentes frames d'une image
        BufferedImage soleil1 = ImageIO.read(new File("Assets/sun/sun1.png"));
        BufferedImage soleil2 = ImageIO.read(new File("Assets/sun/sun2.png"));
        BufferedImage soleil3 = ImageIO.read(new File("Assets/sun/sun3.png"));
        BufferedImage soleil4 = ImageIO.read(new File("Assets/sun/sun4.png"));
        BufferedImage soleil5 = ImageIO.read(new File("Assets/sun/sun5.png"));

        //Ajout des frames dans la liste
        this.frames.add(soleil1);
        this.frames.add(soleil2);
        this.frames.add(soleil3);
        this.frames.add(soleil4);
        this.frames.add(soleil5);
    }
}
