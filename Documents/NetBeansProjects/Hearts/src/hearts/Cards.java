/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author nzs52
 */
public class Cards extends Object implements Constants {

    //Class for all 52 cards in a deck.
    //Cards are named by Rank and then by Suit.
    private String faceName, suit;
    private int rank;
    private BufferedImage cardImage;

    public Cards(String faceName, String suit, int rank, BufferedImage card) {
        this.suit = suit;
        this.faceName = faceName;
        this.rank = rank;
        this.cardImage = card;
    }

    public String toAString() {
        return " " + faceName + " of " + suit;
    }

    public static void main(String[] args) throws IOException {

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
        BufferedImage[] cardImages = new BufferedImage[52];
        Cards[] cards = new Cards[52];
        for (int i = 0; i < Deck.length; i++) {
            cardImages[i] = makeImage(Deck[i]);
            cards[i] = makeCard(Deck[i], cardImages[i]);
            System.out.print(cards[i].toAString());
        }
    }

    public static BufferedImage makeImage(String name) throws IOException {

        BufferedImage tempImage = ImageIO.read(new File("../Hearts/cards/" + name + ".png"));
        return tempImage;
    }

    public static Cards makeCard(String name, BufferedImage image) {
        String[] names = {name.substring(0, 1), name.substring(1)};
        String[] tempNames = translate(names[0], names[1]);
        Cards tempCards = new Cards(tempNames[0], tempNames[1], Integer.parseInt(tempNames[2]), image);
        return tempCards;
    }

    public static String[] translate(String num, String suit) {
        String[] temp = new String[3];
        switch (num) {
            case "1":
                temp[0] = "Ten";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "2":
                temp[0] = "Two";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "3":
                temp[0] = "Three";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "4":
                temp[0] = "Four";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "5":
                temp[0] = "Five";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "6":
                temp[0] = "Six";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "7":
                temp[0] = "Seven";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "8":
                temp[0] = "Eight";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "9":
                temp[0] = "Nine";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "J":
                temp[0] = "Jack";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "Q":
                temp[0] = "Queen";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "K":
                temp[0] = "King";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            case "A":
                temp[0] = "Ace";
                temp[1] = getSuit(suit);
                temp[2] = getVal(num);
                break;
            default:
                break;
        }
        return temp;
    }

    public static String getSuit(String suit) {
        switch (suit) {
            case "C":
                return "Clubs";
            case "D":
                return "Diamonds";
            case "H":
                return "Hearts";
            case "S":
                return "Spades";
            default:
                return "o";
        }
    }

    public static String getVal(String num) {
        switch (num) {
            case "1":
                return "10";
            case "2":
                return "2";
            case "3":
                return "3";
            case "4":
                return "4";
            case "5":
                return "5";
            case "6":
                return "6";
            case "7":
                return "7";
            case "8":
                return "8";
            case "9":
                return "9";
            case "J":
                return "11";
            case "Q":
                return "12";
            case "K":
                return "13";
            case "A":
                return "14";
            default:
                break;
        }
        return "o";
    }
    
    public static void shuffle(String cards[])
    {
        int n = cards.length;
        Random random = new Random();
        
        for(int i=0;i<cards.length;i++)
        {
            int randomValue = i + random.nextInt(n-i);
            String randomElement = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomElement;
        }
    }
    
    public static void deal(String cards[])
    {
        //This would be done when the clients are added to send to each one.
        //Once player class has been added, add these to individual client.
        for(int i =0; i<4;i++)
        {
            System.out.println("Person " + (i +1) + " ");
            for (int j = 0; i < 13; j++)
            {
                System.out.println(cards[i]);
            }
        }
    }
}