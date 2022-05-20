
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends JFrame{
    final JFrame popup;
    JPanel topPanel, lowPanel, wordPanel, livesPanel;
    JPopupMenu menu;
    JButton submit;
    JLabel label, nothing, topNothing;
    JTextField text;
    JLabel livesLabel, guessedLabel, wordLabel;



    String hiddenWord;
    ArrayList<Character>hiddenWordList = new ArrayList<>();
    ArrayList<Character>guessList = new ArrayList<>();
    ArrayList<Character>foundWordsList = new ArrayList<>();
    ArrayList<Integer>spacePositionList = new ArrayList<>();
    
    Integer incorrectGuessCount = 0;

    GamePanel gp;
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

        gp = new GamePanel();

        wordPanel = new JPanel();
        wordPanel.setLayout(new BorderLayout());
        wordLabel = new JLabel("   ", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Monospace", Font.BOLD, 20));
        nothing = new JLabel("                           ");
        topNothing = new JLabel("                           ");
        wordPanel.add(wordLabel);
        wordPanel.add(nothing, BorderLayout.SOUTH);
        //wordPanel.add(topNothing, BorderLayout.NORTH);


        livesPanel = new JPanel();
        livesPanel.setLayout(new GridLayout(0,2));
        livesLabel = new JLabel("Lives Remaining: ", SwingConstants.CENTER);
        guessedLabel = new JLabel("Guessed Letters: ", SwingConstants.CENTER);

        livesPanel.add(livesLabel);
        livesPanel.add(guessedLabel);

        lowPanel = new JPanel();
        lowPanel.setBackground(Color.WHITE);
        lowPanel.setLayout(new BorderLayout());
        lowPanel.add(wordPanel);
        lowPanel.add(livesPanel, BorderLayout.SOUTH);
        add(gp, BorderLayout.CENTER);
        add(lowPanel, BorderLayout.SOUTH);


        //when user clicks confirm
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hiddenWord = JOptionPane.showInputDialog(popup,"Enter a string...");

                int spaceCount = 0;
                String wordStr = "";
                for (int i = 0; i < hiddenWord.length(); i++){
                    int temp = i;
                    //add letter to the arraylist
                    if (hiddenWord.charAt(i) != ' '){
                        hiddenWordList.add(hiddenWord.charAt(i));
                        foundWordsList.add('_');
                        wordStr += "_ ";

                    } else if (hiddenWord.charAt(i) == ' '){
                        hiddenWordList.add(' ');
                        spaceCount++;
                        wordStr += "   ";
                    } 

                    //set i back to correct index after lookahead
                    i = temp;
                }
                wordStr += "\n\n";

                wordLabel.setText(wordStr);
                wordLabel.paintImmediately(wordLabel.getVisibleRect());

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
                String guessStr = "Guessed Letters: ";
                for (char ch: guessList){
                    System.out.print(ch + " ");
                    guessStr = guessStr + " " + ch;
                }
                guessedLabel.setText(guessStr);
                guessedLabel.paintImmediately(guessedLabel.getVisibleRect());
                livesLabel.setText("Lives Remaining: " + (6-incorrectGuessCount));
                livesLabel.paintImmediately(livesLabel.getVisibleRect());
                System.out.println("\nLives Remaining: " + (6-incorrectGuessCount));
                lowPanel.revalidate();
                gp.setBackground(incorrectGuessCount);
                System.out.println("igc = " + incorrectGuessCount);
                //gp.repaint();

                repaint();
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
                gp.setBackground(incorrectGuessCount);

            }

            //this will display word/
            int spaceCount = 0;
            String wordStr = "";
            for (int i = 0; i < hiddenWordList.size(); i++){
                if (hiddenWordList.get(i) != ' '){
                    System.out.print(foundWordsList.get(i-spaceCount) + " ");
                    wordStr = wordStr + foundWordsList.get(i-spaceCount) + " ";
                }

                if(hiddenWordList.get(i) == ' '){
                    System.out.print("   ");
                    spaceCount++;
                    wordStr += "   ";
                }

            }
            wordStr += "\n\n";

            wordLabel.setText(wordStr);                
            wordLabel.paintImmediately(wordLabel.getVisibleRect());

            //if this is true, user has won the game
            if (!foundWordsList.contains('_')){
                System.out.println("\nyou have won the game!!!!!");
                wordLabel.setText("you won!!!!\n");
                wordLabel.paintImmediately(wordLabel.getVisibleRect());
                gp.setBackground(incorrectGuessCount);

                break;
            } else if (incorrectGuessCount >= 5){
                System.out.println("\nyou lost my brotha :(");
                wordLabel.setText("you lost :(\n");
                wordLabel.paintImmediately(wordLabel.getVisibleRect());
                gp.setBackground(incorrectGuessCount);

                break;
            }

            System.out.println("");
        }
    }
}
