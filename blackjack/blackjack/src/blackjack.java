import java.util.Scanner;

public class blackjack {
    // helper
    private String[] append(String[] appendTo, String appension) {
        String[] temp = new String[appendTo.length + 1];
        for (int i = 0; i < appendTo.length; i++) {
            temp[i] = appendTo[i];
        }
        temp[temp.length - 1] = appension;
        return temp;
    }

    // param
    private Scanner input = game.input;
    private String[] playerHand, dealerHand;
    private int userScore, dealerScore;
    private player dealer, user;
    private Deck deck;

    // constructer
    public blackjack(Deck d) {
        deck = d;
        deck.shuffle();
        playerHand = new String[0];
        dealerHand = new String[0];
        for (int x = 0; x < 2; x++) {
            playerHand = append(playerHand, d.draw());
            dealerHand = append(dealerHand, d.draw());
        }
        dealer = new player(dealerHand, playerHand);
        user = new player(playerHand);
        userScore = user.getScore();
        dealerScore = dealer.getScore();
    }

    // getter
    public String[] getDealerHand() {
        return dealerHand;
    }

    public String[] getPlayerHand() {
        return playerHand;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public int getPlayerScore() {
        return userScore;
    }

    // mutator
    public void play() {
        char userPlay = ' ';
        while (user.getScore() < 21 && dealer.getScore() < 21) {
            System.out.print("Dealer Hand: ");
            for (int i = 0; i < dealerHand.length; i++) {
                System.out.print(dealerHand[i] + " ");
            }
            System.out.print("Dealer Score: " + dealer.getScore() + "\nPlayer Hand: ");
            for (int i = 0; i < playerHand.length; i++) {
                System.out.print(playerHand[i] + " ");
            }
            System.out.print("Player Score: " + user.getScore() + "\n");
            userPlay = user.play(input);
            if (userPlay == 's' || userPlay == 'S') {
                while (dealer.play() != 's') {
                    dealerHand = append(dealerHand, deck.draw());
                    dealer.updateHand(dealerHand);
                }
                break;
            } else if (userPlay == 'h' || userPlay == 'H') {
                playerHand = append(playerHand, deck.draw());
                user.updateHand(playerHand);
                if (dealer.play() == 'h') {
                    dealerHand = append(dealerHand, deck.draw());
                    dealer.updateHand(dealerHand);
                }
            } else {
                System.out.println("Either type \"hit\" or \"stand\"");
            }
        }
        userScore = user.getScore();
        dealerScore = dealer.getScore();
        System.out.print("Dealer Hand: ");
        for (int i = 0; i < dealerHand.length; i++) {
            System.out.print(dealerHand[i] + " ");
        }
        System.out.print("Dealer Score: " + dealer.getScore() + "\nPlayer Hand: ");
        for (int i = 0; i < playerHand.length; i++) {
            System.out.print(playerHand[i] + " ");
        }
        System.out.print("Player Score: " + user.getScore() + "\n");
        if ((userScore > 21 && dealerScore > 21) || userScore == dealerScore) {
            System.out.println("Tie");
        } else if (userScore > 21) {
            System.out.println("You busted, dealer wins");
        } else if (dealerScore > 21) {
            System.out.println("Dealer busted, you win");
        } else if (dealerScore > userScore) {
            System.out.println("Dealer wins");
        } else if (userScore > dealerScore) {
            System.out.println("User wins");
        }
    }
}
