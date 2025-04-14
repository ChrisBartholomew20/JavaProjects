import javax.swing.*;
import java.awt.*;

public class TestImageIcon extends JFrame {
    private ImageIcon cardOne = new ImageIcon("card/6.png");
    private ImageIcon cardTwo = new ImageIcon("53.png");
    private ImageIcon cardThree = new ImageIcon("52.png");
    private ImageIcon cardFour= new ImageIcon("51.png");

    public TestImageIcon() {
        setLayout(new GridLayout(1, 4, 5, 5));
        add(new JButton(cardOne));
        add(new JButton(cardTwo));
        add(new JButton(cardThree));
        add(new JButton(cardFour));
    }

    /** Main method */
    public static void main(String[] args) {
        TestImageIcon frame = new TestImageIcon();
        frame.setTitle("ThreeCards");
        frame.setSize(350, 160);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}