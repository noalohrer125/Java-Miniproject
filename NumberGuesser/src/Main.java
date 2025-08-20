import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static int myNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);
    static JLabel text = new JLabel("Type a number (0-100)!");
    static JTextField textField = new JTextField();

    public static void main(String[] args) {
        openUI();
        // nextRound();
    }

    public static void openUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Guess the Number!");
        frame.setSize(400, 300);
        frame.setLocation(100, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        text.setBounds(50, 50, 200, 30);

        textField.setBounds(50, 100, 200, 30);

        JButton button = new JButton("Guess!");
        button.setBounds(50, 150, 200, 30);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String textFromTextField = textField.getText();
                    int number = Integer.parseInt(textFromTextField);
                    guess(number);
                } catch (Exception err) {
                    text.setText("Please enter a number!");
                }
            }
        });

        frame.add(button);
        frame.add(textField);
        frame.add(text);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void guess(int number) {
        if(number == myNumber) {
            text.setText("Richtig!");
        } else {
            text.setText("Falsch!");
            if(number<myNumber) {
                text.setText("to small");
            } else {
                text.setText("to big");
            }
            textField.setText("");
        }
    }
}
