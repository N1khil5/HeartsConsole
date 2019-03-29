package hearts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nzs52
 */
public class Player {

    private String my_name;
    private Cards[] myHand = new Cards[13];
    private int points;
    private List<Cards> playList = new ArrayList<Cards>();

    public Player(String my_name, Cards[] myHand) {
        this.my_name = my_name;
        this.myHand = myHand;
        this.points = 0;
    }

    public String getMy_name() {
        return my_name;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void reducePoints(int points) {
        this.points -= points;
    }

    public int getPoints() {
        return points;
    }

    public Cards[] getMyHand() {
        return myHand;
    }

    public void setMyHand(Cards[] myHand) {
        this.myHand = null;
        this.myHand = myHand;

        for (Cards cards : myHand) {
            cards.setOwner(my_name);
            cards.setUsableCard(true);
        }
    }

    public void get3Cards(Cards[] myHand) {

        for (Cards cards : myHand) {
            cards.setOwner(my_name);
            cards.setUsableCard(true);
        }
    }
    
    public void remove3Cards(Cards[] removeCards) {

        int count = 0;
        for (Cards removeC : removeCards) {
            for (int i = 0; i < myHand.length; i++) {
                if (myHand[i].getValue() ==removeC.getValue()) {
                    count++;
                    myHand[i].equals(null);
                    break;
                }
            }
        }
        if(count !=3) {
            System.out.println("Error removing 3 cards" + count);
        }
    }
    
    public Boolean isFirstPlayer() {
        for (Cards cards : myHand) {
            if (cards.isClubs2()) {
                return true;
            }
        }
        return false;
    }
    
    public void addplayList(Cards cards) {
        if(cards.getOwner() == null) {
            System.out.println("Invalid card to " + my_name);
        }
        playList.add(cards);
    }
    
    public void resetplayList() {
        this.playList.clear();
        this.playList = new ArrayList<Cards>();
    }
    
    public void update_rule(Boolean firstRound, Boolean isHeartsBroken) {
        
        for(Cards cards : myHand) {
            cards.setUsableCard(false);
        }
        
        int usable_count = 0;
        
        
        if(playList.size()==0){
        for(Cards cards: myHand) {
            if(cards.isClubs2()) {
                cards.setUsableCard(true);
                return;
            }
        }
        
        if(!isHeartsBroken) {
            for(Cards cards : myHand) {
                if(!cards.isHearts(cards)) {
                    cards.setUsableCard(true);
                    usable_count++;
                }
            }
        }
        
        if(firstRound) {
            for(Cards cards : myHand) {
                if(cards.isQueenSpades()) {
                    cards.setUsableCard(false);
                    usable_count--;
                }
            }
        }
    }
        else {
            for(Cards cards :myHand) {
                try {
                    if(cards.getSuit().equals(playList.get(0).getSuit())) {
                        cards.setUsableCard(true);
                        usable_count++;
                    }
                } catch (NullPointerException exception) {
                    System.out.println(my_name + " error");
                }
            }
        }
        
        if(usable_count == 0){
            for(Cards cards : myHand) {
                cards.setUsableCard(true);
            }
        }
    }
        
}
