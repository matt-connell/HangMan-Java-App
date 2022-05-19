
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends JFrame{
    final JFrame popup;
    JPanel topPanel, lowPanel;
    JPopupMenu menu;
    public JButton submit;
    public JLabel label, nothing;
    public JTextField text;
    String hiddenWord;
    ArrayList<Character>hiddenWordList = new ArrayList<>();
    ArrayList<Character>guessList = new ArrayList<>();
    ArrayList<Character>foundWordsList = new ArrayList<>();
    ArrayList<Integer>spacePositionList = new ArrayList<>();
    
    Integer incorrectGuessCount = 0;

    ImageIcon backgroundImg;

    Scanner reader = new Scanner(System.in);

    public Game(){
        super("HangMan");
        setLayout(new BorderLayout());

        //popup window
        popup = new JFrame();
        submit = new JButton("Confirm");
        popup.add(submit);
        popup.pack();
        popup.setAlwaysOnTop(true);
        popup.setVisible(true);
        popup.setSize(50,50);


        //this will be the top panel that is displayed to get user input
        topPanel = new JPanel();
        label = new JLabel("Enter Hidden Word: ");
        nothing = new JLabel();

        //topPanel.add(label);
        //text = new JTextField(10);
        //text.add(menu);
        //text.setComponentPopupMenu(menu);
        //topPanel.add(text);
        //submit = new JButton("Confirm");
        //topPanel.add(submit);

        GamePanel gp = new GamePanel();

        lowPanel = new JPanel();

        add(gp, BorderLayout.CENTER);

        //add to north part of frame
        add(topPanel, BorderLayout.NORTH);
        add(lowPanel, BorderLayout.SOUTH);
        System.out.println("\t\t________");
        System.out.println("\t\t|");
        System.out.println("\t\t|");
        System.out.println("\t\t|");
        System.out.println("\t\t|");
        System.out.println("\t\t|");
        System.out.println("\t\t|");
        System.out.println("\t________|___________");


        //when user clicks confirm
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hiddenWord = JOptionPane.showInputDialog(popup,"Enter a string...");

                int spaceCount = 0;
                for (int i = 0; i < hiddenWord.length(); i++){
                    int temp = i;
                    //add letter to the arraylist
                    if (hiddenWord.charAt(i) != ' '){
                        hiddenWordList.add(hiddenWord.charAt(i));
                        foundWordsList.add('_');

                    } else if (hiddenWord.charAt(i) == ' '){
                        hiddenWordList.add(' ');
                        spaceCount++;
                    }  else {
                    }

                    //set i back to correct index after lookahead
                    i = temp;
                }
                popup.setVisible(false);
                System.out.print("\nHiddenWordList: ");
                for (char ch: hiddenWordList){
                    if (ch == ' ')
                        System.out.print("  ");
                    else 
                        System.out.print("_");
                    
                }
                System.out.println("");
                try {
                    Thread.sleep(1500);
                } catch (Exception err) {
                    //TODO: handle exception
                }
                guessingFunction();

            }

        });
    }


    public void guessingFunction(){

        while(true){
            //this will display the guessed incorrect letters and remaining lives, if there is any
            if (!guessList.isEmpty()){
                System.out.print("\nGuessed Letters: ");
                for (char ch: guessList){
                    System.out.print(ch + " ");
                }
                System.out.println("\nLives Remaining: " + (6-incorrectGuessCount));
            }

            //get input from the user
            System.out.print("\nPlease guess a letter: ");
            char c = reader.next().charAt(0);
            System.out.println("");

            //if the guessed word is found in the arraylist
            if (hiddenWordList.contains(c)){
                System.out.println(c + " is found");
                int spaceCount = 0;
                for (int i = 0; i < hiddenWordList.size(); i++){
                    //will print space depending on position
                    if(hiddenWordList.get(i) == c){
                        foundWordsList.set(i-spaceCount, c);

                    } else if(hiddenWordList.get(i) == ' '){
                        spaceCount++;
                    }

                }

        
            //if the guessed letter is not in the arraylist
            } else {
                
                System.out.println(c + " is not found");
                //add to the guessed words list
                guessList.add(c);
                incorrectGuessCount++;
            }

            //this will display word/
            int spaceCount = 0;
            for (int i = 0; i < hiddenWordList.size(); i++){
                if (hiddenWordList.get(i) != ' '){
                    System.out.print(foundWordsList.get(i-spaceCount) + " ");
                }

                if(hiddenWordList.get(i) == ' '){
                    System.out.print("   ");
                    spaceCount++;
                }

            }
            //if this is true, user has won the game
            if (!foundWordsList.contains('_')){
                System.out.println("\nyou have won the game!!!!!");
                break;
            } else if (incorrectGuessCount >= 5){
                System.out.println("\nyou lost my brotha :(");
                break;
            }

            System.out.println("");
        }
    }
}
