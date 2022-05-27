import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel{
    public ImageIcon backgroundImg;
    public int currentImg = 0;
    public GamePanel(int x){
        currentImg = x;
        setCurrentImg(x);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        backgroundImg.paintIcon(this, g, 0, 0);

    }

    public void setCurrentImg(int x){
        this.currentImg = x;
        String str = "background" + x + ".png";
        backgroundImg = new ImageIcon(getClass().getResource(str));
        repaint();

    }

    public int getCurrentImg(){
        return this.currentImg;
    }

    public void setBackground(int x){
        if (x == 0){
            backgroundImg = new ImageIcon(getClass().getResource("background0.png"));
            repaint();
        } else if (x == 1){
            backgroundImg = new ImageIcon(getClass().getResource("background1.png"));
            repaint();
        }else if (x == 2){
            backgroundImg = new ImageIcon(getClass().getResource("background2.png"));
            repaint();
        }else if (x == 3){
            backgroundImg = new ImageIcon(getClass().getResource("background3.png"));
            repaint();
        }else if (x == 4){
            backgroundImg = new ImageIcon(getClass().getResource("background4.png"));
            repaint();
        }else if (x == 5){
            backgroundImg = new ImageIcon(getClass().getResource("background5.png"));
            repaint();
        }
        System.out.println("i am here, " + x);
        revalidate();
        repaint();




    }
}
