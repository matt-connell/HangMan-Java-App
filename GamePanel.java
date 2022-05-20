import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel{
    public ImageIcon backgroundImg;

    public GamePanel(){
        backgroundImg = new ImageIcon(getClass().getResource("backgroundImg.png"));

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        backgroundImg.paintIcon(this, g, 0, 0);

    }

    public void setBackground(int x){
        if (x == 1)
            backgroundImg = new ImageIcon(getClass().getResource("background1.png"));
        else if (x == 2)
            backgroundImg = new ImageIcon(getClass().getResource("background2.png"));
        else if (x == 3)
            backgroundImg = new ImageIcon(getClass().getResource("background3.png"));
        else if (x == 4)
            backgroundImg = new ImageIcon(getClass().getResource("background4.png"));
        else if (x == 5)
            backgroundImg = new ImageIcon(getClass().getResource("background5.png"));
        System.out.println("i am here, " + x);
        revalidate();
        repaint();




    }
}
