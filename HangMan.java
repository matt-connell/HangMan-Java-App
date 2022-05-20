import javax.swing.*;

public class HangMan {

    public static void main(String[] args){

        Game frame = new Game();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,425);
        frame.setVisible(true);
    }
}