import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static int[] deck = {
            2,3,4,5,6,7,8,9,10,10,10,10,11,
            2,3,4,5,6,7,8,9,10,10,10,10,11,
            2,3,4,5,6,7,8,9,10,10,10,10,11,
            2,3,4,5,6,7,8,9,10,10,10,10,11
    };

    public static ArrayList<Integer> dealersCards = new ArrayList<>();
    public static ArrayList<Integer> usersCards = new ArrayList<>();

    public static JLabel text = new JLabel("Take a card!");
    public static JLabel playersCards = new JLabel();
    public static JLabel dealerLabel = new JLabel();

    public static JButton button = new JButton("Get Card!");
    public static JButton standButton = new JButton("Stand!");

    public static JFrame frame = new JFrame("BlackJack!");

    public static boolean continueGame = true;

    public static void openUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(400, 300);
        frame.setLocation(100, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        text.setBounds(50, 30, 300, 30);
        playersCards.setBounds(50, 60, 300, 30);
        dealerLabel.setBounds(50, 90, 300, 30);

        button.setBounds(50, 150, 200, 30);
        standButton.setBounds(50, 190, 200, 30);

        frame.add(button);
        frame.add(standButton);
        frame.add(text);
        frame.add(playersCards);
        frame.add(dealerLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::game);
    }

    public static void game() {
        openUI();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClick();
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (continueGame) {
                    continueGame = false;
                    dealerTurn(true);
                }
            }
        });
    }

    public static void buttonClick() {
        if (!continueGame) return;

        int card = getCard();
        usersCards.add(card);
        playersCards.setText("Your cards: " + usersCards + " (sum=" + sum(usersCards) + ")");

        if (sum(usersCards) > 21) {
            checkWinner();
            return;
        }

        text.setText("Dealer took a card...");
        dealerTurn();
    }

    public static void dealerTurn(boolean finish) {
        if (finish) {
            while (sum(dealersCards) < 17) {
                dealersCards.add(getCard());
            }
            checkWinner();
        } else {
            if (sum(dealersCards) < 17) {
                dealersCards.add(getCard());
            }
        }
    }
    public static void dealerTurn() {
        dealerTurn(false);
    }

    public static void checkWinner() {
        int user = sum(usersCards);
        int dealer = sum(dealersCards);

        if (dealer > 21 || user > dealer && user < 21) {
            text.setText("You win!");
            dealerLabel.setText("Dealer cards: " + dealersCards + " (sum=" + sum(dealersCards) + ")");
        } else if (user == dealer) {
            text.setText("Draw!");
            dealerLabel.setText("Dealer cards: " + dealersCards + " (sum=" + sum(dealersCards) + ")");
        } else {
            text.setText("Dealer wins!");
            dealerLabel.setText("Dealer cards: " + dealersCards + " (sum=" + sum(dealersCards) + ")");
        }
        endGame();
    }

    public static void endGame() {
        continueGame = false;
        button.setEnabled(false);
        standButton.setEnabled(false);
    }

    public static int getCard() {
        if (deck.length == 0) throw new IllegalStateException("Deck is empty!");

        int i = new Random().nextInt(deck.length); // random index
        int card = deck[i];

        int[] newDeck = new int[deck.length - 1];
        System.arraycopy(deck, 0, newDeck, 0, i);
        System.arraycopy(deck, i + 1, newDeck, i, deck.length - i - 1);
        deck = newDeck;

        return card;
    }

    public static int sum(ArrayList<Integer> list) {
        int total = 0;
        for (int n : list) total += n;
        return total;
    }
}
