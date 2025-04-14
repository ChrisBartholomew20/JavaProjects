import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class CardMatch extends JFrame {
    private ImageIcon cardBack = new ImageIcon("cards/b1fv.png");
    private ImageIcon[] cards = {
            new ImageIcon("cards/51.png"),
            new ImageIcon("cards/40.png"),
            new ImageIcon("cards/47.png"),
            new ImageIcon("cards/25.png"),
            new ImageIcon("cards/51.png"),
            new ImageIcon("cards/40.png"),
            new ImageIcon("cards/47.png"),
            new ImageIcon("cards/25.png"),
    };

    private JButton[] buttons = new JButton[8];
    private ArrayList<ImageIcon> randomizedCards = new ArrayList<>();
    private JButton firstCard = null;
    private JButton secondCard = null;
    private int matches = 0;
    private int attempts = 0;
    private long startTime;

    public CardMatch() {
        setTitle("Matching Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(2, 4));

        // Randomize card positions
        Collections.addAll(randomizedCards, cards);
        Collections.shuffle(randomizedCards);

        // Create buttons and add listeners
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(cardBack);
            buttons[i].addActionListener(new ButtonListener(i));
            add(buttons[i]);
        }

        // Start timer
        startTime = System.currentTimeMillis();

        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        private int index;

        public ButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Ignore clicks if two cards are already flipped or the card is disabled
            if ((firstCard != null && secondCard != null) || !buttons[index].isEnabled()) {
                return;
            }

            JButton clickedButton = buttons[index];
            clickedButton.setIcon(randomizedCards.get(index));

            if (firstCard == null) {
                firstCard = clickedButton;
                return;
            }

            secondCard = clickedButton;
            attempts++;



            ImageIcon icon1 = (ImageIcon) firstCard.getIcon();
            ImageIcon icon2 = (ImageIcon) secondCard.getIcon();
            if (icon1.getImage().equals(icon2.getImage())) {
                System.out.println("Icons are equal!");
                System.out.println("First card icon: " + firstCard.getIcon());
                System.out.println("Second card icon: " + secondCard.getIcon());                firstCard.setEnabled(false);
                secondCard.setEnabled(false);
                firstCard = null;
                secondCard = null;
                matches++;
                System.out.println("Matches count: " + matches);
                checkGameOver();
            } else {
                //System.out.println("No match. First card index: " + firstIndex + ", Second card index: " + secondIndex);
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        firstCard.setIcon(cardBack);
                        secondCard.setIcon(cardBack);
                        firstCard = null;
                        secondCard = null;
                    }

                });
                timer.setRepeats(false); // Execute only once
                timer.start();
            }
        }

        private int getButtonIndex(JButton button) {
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i] == button) {
                    return i;
                }
            }
            return -1;
        }
    }

    private void checkGameOver() {
        if (matches == cards.length / 2) { // Check if all pairs are matched
            // Game over, show stats
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime) / 1000; // Time in seconds

            JOptionPane.showMessageDialog(this,
                    "Congratulations! You matched all cards!\n" +
                            "Attempts: " + attempts + "\n" +
                            "Time: " + duration + " seconds",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);

            System.exit(0); // Exit the game
        }
    }

    public static void main(String[] args) {
        new CardMatch();
    }
}
