import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/** Lancement du menu principal */
public class Main extends JPanel implements ActionListener {
    private JFrame menu;
    private String title = "Main menu";

    /** Boutons du menu */
    private  JButton start;
    private  JButton exit;

    /** Dimension de la fenetre */
    private int width = 600;
    private int height = 300;

    public Main() throws IOException {
        //Création de la fenetre
        menu = new JFrame(title);
        menu.setLocation(300,33);

        //Icone de la fenetre
        ImageIcon img = new ImageIcon("Assets/moto0.png");
        menu.setIconImage(img.getImage());

        //Image d'arriere-plan
        JLabel background = new JLabel(new ImageIcon("Assets/route.png"));
        background.setLocation(0,0);
        background.setLayout(new FlowLayout(FlowLayout.CENTER,20,width/3));

        //Création des boutons
        start = new JButton("start");
        start.setPreferredSize(new Dimension(100,20));
        start.addActionListener(this);
        background.add(start);

        exit = new JButton("quit");
        exit.setPreferredSize(new Dimension(100,20));
        exit.addActionListener(this);
        background.add(exit);

        //Affichage
        this.add(background);
        menu.getContentPane().add(this);
        menu.setPreferredSize(new Dimension(width,height));
        menu.pack();
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            //fermeture de la fenetre
            this.menu.dispose();
            String[] args1 = {"10"};
            try {
                //lancement du jeu
                new Game().main(args1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //fermeture de la fenetre
        if(e.getSource() == exit) this.menu.dispose();


    }
    public static void main(String [] args) throws IOException {
        new Main();
    }
}
