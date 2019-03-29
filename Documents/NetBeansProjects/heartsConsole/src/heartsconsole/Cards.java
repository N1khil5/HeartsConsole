package HeartsConsole;
/**
 *
 * @author nzs52
 */
public class Cards extends Object{

    //Class for all 52 cards in a deck.
    //Cards are named by Rank and then by Suit.
    private String faceName, suit;
    private int value;
    private Boolean usableCard;
    private String owner;

    public Cards(String faceName, String suit, int rank) {
        this.suit = suit;
        this.faceName = faceName;
        this.value = value;
    }

    public String toAString() {
        return " " + faceName + " of " + suit;
    }

    public static Cards makeCard(String name) {
        String[] names = {name.substring(0, 1), name.substring(1)};
        String[] tempNames = translate(names[0], names[1]);
        Cards tempCards = new Cards(tempNames[0], tempNames[1], Integer.parseInt(tempNames[2]));
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

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Boolean isSmaller(Cards cards) {
        if (this.value < cards.getValue()) {
            return true;
        }
        return false;
    }

    public Boolean isGreater(Cards cards) {
        if (this.value > cards.getValue()) {
            return true;
        }
        return false;
    }

    public Boolean isHearts(Cards cards) {
        if (this.getSuit() == "Hearts") {
            return true;
        }
        return false;
    }

    public Boolean getUsableCard() {
        return usableCard;
    }

    public void setUsableCard(Boolean usableCard) {
        this.usableCard = usableCard;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean isClubs2() {
        if (this.getSuit() == "Clubs" && this.getValue() == 2) {
            return true;
        }
        return false;
    }

    public Boolean isQueenSpades() {
        if (this.getSuit() == "Spades" && this.getValue() == 12) {
            return true;
        }
        return false;
    }
}
