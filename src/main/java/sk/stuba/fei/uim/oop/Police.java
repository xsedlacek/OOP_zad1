package sk.stuba.fei.uim.oop;

public class Police extends Actions{
    public void policeAction(Player player){
        player.setPosition(6);
        player.setJail(true);
    }
}
