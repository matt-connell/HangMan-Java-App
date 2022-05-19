import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class GamePanel extends JPanel{
    public ImageIcon backgroundImg;

    public GamePanel(){
        backgroundImg = new ImageIcon(getClass().getResource("mainpage.png"));

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        backgroundImg.paintIcon(this, g, 0, 0);

    }
}
