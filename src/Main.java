import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JPanel implements ActionListener {
    private JFrame menu;
    private String title = "Main menu";
    private  JButton start;
    public Main(){
        menu = new JFrame(title);
        start = new JButton("start");
        start.setPreferredSize(new Dimension(100,40));
        start.addActionListener(this);
        start.setLocation(0,0);
        menu.setLocation(300,33);


        this.add(start);
        menu.getContentPane().add(this);
        menu.setPreferredSize(new Dimension(500,200));
        menu.pack();
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            this.menu.dispose();
            String[] args1 = {"10"};
            try {
                new Game().main(args1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    public static void main(String [] args) throws IOException {
        new Main();
    }
}
