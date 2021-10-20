import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class game {
    static Scanner input;
    public static void main(String[] args) throws InterruptedException{
        input=new Scanner(System.in);
        System.out.println("How many games do you want to play:");
        int numGames=input.nextInt();
        for (int i=0;i<numGames;i++){
            Deck d=new Deck(true,8);
            blackjack game=new blackjack(d);
            game.play();
            TimeUnit.SECONDS.sleep(2);
            System.out.print("\n");
        }
        input.close();
    }
}
