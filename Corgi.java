import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// +-------------------------------------------------------------------------+
// |   _____     __      __     __  __         _____              _       __ |
// |  / ___/__ _/ /_____/ /    / /_/ /  ___   / ___/__  _______ _(_)     / / |
// | / /__/ _ `/ __/ __/ _ \  / __/ _ \/ -_) / /__/ _ \/ __/ _ `/ /     /_/  |
// | \___/\_,_/\__/\__/_//_/  \__/_//_/\__/  \___/\___/_/  \_, /_/     (_)   |
// |                                                      /___/              |
// +-------------------------------------------------------------------------+
// |                                                                         |
// |Le Corgi s'est échappé, courant dans tous les sens sur ton bureau. Tu as |
// |7 chances pour l'attraper. Attention, à chaque fois le corgi courra plus |
// |                                 vite !                                  |
// |                                                                         |
// +-------------------------------------------------------------------------+
public class Corgi extends JFrame{

    Container c;
    JLabel label;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    Icon icon;
    String texte;
    int compteurVie = 7;
    int vitesse = 1;

    public Corgi() {
        c = getContentPane();

        //transparent
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));

        //taille
        setSize(400,400);

        //label
        icon = new ImageIcon("src/corgie.gif");
        label = new JLabel(texte, icon,JLabel.CENTER);
        label.setFont(new Font("Monospaced", Font.BOLD, 55));
        label.setHorizontalTextPosition(JLabel.CENTER);

        //ajouter listener
        label.addMouseListener(new MListener());

        //ajouter le label au conteneur
        c.add(label);

    }

    // souris listener
    public class MListener extends MouseAdapter{
        public void mousePressed(MouseEvent e) {
            label.setText(compteurVie-- +" ");
            vitesse +=2;
        }
    }


    public void run() throws Exception {

        //position de départ
        int y = 300;
        int x = 500;
        boolean yb = false;
        boolean xb = false;

        //boucle pour les pas
        while (compteurVie >= 0) {
            Thread.sleep(10);
            setLocation(x,y);

            // direction
            if (y >= dim.getHeight() - 200) {
                yb = true;
            }else if (y <= -200) {
                yb = false;
            }

            if (x >= dim.getWidth() - 200) {
                xb = true;
            }else if(x <= -200) {
                xb = false;
            }

            if (yb) {
                y -= vitesse;
            } else {
                y += vitesse;
            }

            if (xb) {
                x -= vitesse;
            }else {
                x += vitesse;
            }


        }

        label.setText(" ");
        label.setIcon(new ImageIcon("src/explosion.gif"));
        //Attend 1.8 seconde
        Thread.sleep(1800);
        System.exit(0);

    }



    //////////////   début MAIN    ///////////////////
    public static void main(String[] args) {

        Corgi frame = new Corgi();
        frame.setVisible(true);

        try {
            frame.run();
        } catch (Exception e) {
        }

    }
    //////////////   Fin MAIN    ///////////////////

}
