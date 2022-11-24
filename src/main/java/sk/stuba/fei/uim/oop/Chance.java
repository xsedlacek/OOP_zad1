package sk.stuba.fei.uim.oop;

import java.util.Collections;
import java.util.List;

public class Chance extends Actions{
    private boolean used;
    private int action;

    public Chance() {
    }

    public Chance(boolean used, int action) {
        this.used = used;
        this.action = action;
    }

    public void shuffle(List<Chance> list){
        Collections.shuffle(list);
    }

    public void setUnused(List<Chance> list){
        for (Chance chance : list) {
            chance.setUsed(false);
        }
    }

    public void cardsActions(Chance chance,Player player){
        switch (chance.getAction() % 5) {
            case 0:
                System.out.println("Ides do vazenia");
                player.setJail(true);
                player.setPosition(6);
                break;
            case 1:
                System.out.println("Platis dan 2000");
                Tax tax = new Tax();
                tax.taxAction(player, 2000);
                break;
            case 2:
                System.out.println("Dostavas 2000 penazi");
                player.setAccountStatus(player.getAccountStatus() + 2000);
                break;
            case 3:
                System.out.println("Ides na start a dostavas peniaze");
                player.setPosition(24);
                start(player);
                break;
            case 4:
                System.out.println("Ides na policko dan ale neplatis nic");
                player.setPosition(12);
                break;
        }

    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getAction() {
        return action;
    }

}
