package sk.stuba.fei.uim.oop;

public class Property extends Actions {
    private final int price;
    private boolean owned;
    private Player owner;


    public Property(int price, boolean owned) {
        this.price = price;
        this.owned = owned;
    }

    public int getPrice() {

        return price;
    }





    public boolean isOwned() {

        return owned;
    }

    public void setOwned(boolean owned) {

        this.owned = owned;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
