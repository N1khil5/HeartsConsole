/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hearts;

/**
 *
 * @author nzs52
 */
public class Player {

    private String my_name;
    private Cards[] myHand = new Cards[13];
    private int points;

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
        //my_list.addAll(list);

        for (Cards cards : myHand) {
            cards.setOwner(my_name);
            cards.setUsableCard(true);
        }
    }

}
