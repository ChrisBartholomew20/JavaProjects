import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        int SIZE = 8;
        JFrame frame = new JFrame("Checkerboard");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(SIZE,SIZE));

        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                JButton button = new JButton();

                if  ((row + column) % 2 == 0) {
                    button.setBackground(Color.WHITE);}
                else {
                button.setBackground(Color.BLACK);}
                frame.add(button);
            }
        }




        frame.setVisible(true);

    }
}