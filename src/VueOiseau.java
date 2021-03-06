import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** Classe VueOiseau: charge les frames d'un oiseau et lance son thread
 * */
public class VueOiseau {

    /** liste d'image a afficher*/
    ArrayList<Oiseau> oiseaux;
    /** liste des etats d'une image */
    ArrayList<BufferedImage> frames;
    /** Affichage */
    Affichage affichage;

    /**
     * CONSTRUCTEUR DE LA CLASSE VUEOISEAU
     *
     * */
    public VueOiseau(Affichage affichage) {
        this.oiseaux = new ArrayList<>();
        this.frames = new ArrayList<>();
        this.affichage = affichage;
    }

    public void addOiseau(){
        oiseaux.add(new Oiseau(affichage));
    }

    /** affichage des images */
    public void dessiner(Graphics g) throws IOException {

        addOiseau();

        Oiseau o = oiseaux.get(0);
        if(!o.getRunning()){
            o.start(); //lancement du thread
            loadEtat(); //chargement des etats de l'image
        }

        //affichage de l'image
        g.drawImage(frames.get(o.getEtat()), o.getPosition(), o.getHauteur(),100,100, null);

        //l'image est enlevée de la liste lorsque l'oiseau sort de l'écran
        if(o.getPosition() > Affichage.getWIDTH()){
            o.interrupt();
            oiseaux.remove(o);
        }

        if(oiseaux.isEmpty()) addOiseau(); //ajoute un oiseau

        //actualisation de l'affichage
        affichage.revalidate();
        affichage.repaint();

    }

    /** charge dans la liste etat les differents etat de l'image */
    public void loadEtat() throws IOException {
        //Chargement des différents état d'une image
        BufferedImage oiseau1 = ImageIO.read(new File("Assets/oiseau/oiseau1.png"));
        BufferedImage oiseau2 = ImageIO.read(new File("Assets/oiseau/oiseau2.png"));
        BufferedImage oiseau3 = ImageIO.read(new File("Assets/oiseau/oiseau3.png"));
        BufferedImage oiseau4 = ImageIO.read(new File("Assets/oiseau/oiseau4.png"));
        BufferedImage oiseau5 = ImageIO.read(new File("Assets/oiseau/oiseau5.png"));
        BufferedImage oiseau6 = ImageIO.read(new File("Assets/oiseau/oiseau6.png"));
        BufferedImage oiseau7 = ImageIO.read(new File("Assets/oiseau/oiseau7.png"));
        BufferedImage oiseau8 = ImageIO.read(new File("Assets/oiseau/oiseau8.png"));

        //Ajout des etats dans la liste
        this.frames.add(oiseau1);
        this.frames.add(oiseau2);
        this.frames.add(oiseau3);
        this.frames.add(oiseau4);
        this.frames.add(oiseau5);
        this.frames.add(oiseau6);
        this.frames.add(oiseau7);
    }
}
