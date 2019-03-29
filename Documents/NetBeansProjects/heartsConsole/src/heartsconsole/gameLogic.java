package HeartsConsole;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nzs52
 */
public class gameLogic {

    public static void shuffle(Cards cards[]) {
        int n = cards.length;
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Cards randomElement = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomElement;
        }
    }

    public static Cards[] deal(String[] nPlayer) throws IOException {

        String[] Deck = {"2C", "2D", "2H", "2S",
            "3C", "3D", "3H", "3S",
            "4C", "4D", "4H", "4S",
            "5C", "5D", "5H", "5S",
            "6C", "6D", "6H", "6S",
            "7C", "7D", "7H", "7S",
            "8C", "8D", "8H", "8S",
            "9C", "9D", "9H", "9S",
            "1C", "1D", "1H", "1S",
            "AC", "AD", "AH", "AS",
            "JC", "JD", "JH", "JS",
            "QC", "QD", "QH", "QS",
            "KC", "KD", "KH", "KS"};
        
        Cards[] cards = new Cards[52];
        for (int i = 0; i < Deck.length; i++) {
            System.out.print(cards[i].toAString());
        }
        shuffle(cards);
        for(int i =0; i<cards.length; i++){
            if(i <13){
            cards[i].setOwner(nPlayer[0]);                
            }
            else if(i>12 && i <26){
                cards[i].setOwner(nPlayer[1]);
            }
            else if(i>25 && i <39){
                cards[i].setOwner(nPlayer[2]);
            }
            else {
                cards[i].setOwner(nPlayer[3]);
            }
        }
        return cards;
    }
}
