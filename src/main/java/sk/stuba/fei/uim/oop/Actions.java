package sk.stuba.fei.uim.oop;

import java.util.List;
import java.util.Random;

public abstract class Actions {


    public void fine(Player player1, Player player2, Property property) {
        System.out.println("Skocil si na nehnutelnost vlastnenu hracom " + property.getOwner().getName() + " platis :" + property.getPrice() / 10);

        int fine = property.getPrice() / 10;

        int accountStatus;

        accountStatus = player1.getAccountStatus() - fine;
        player1.setAccountStatus(accountStatus);

        accountStatus = player2.getAccountStatus() + fine;
        player2.setAccountStatus(accountStatus);

    }

    public void tax(Player player, int taxAmount) {
        int accountStatus = player.getAccountStatus() - taxAmount;

        player.setAccountStatus(accountStatus);


    }

    public void buy(Player player, Property property) {

        int accountStatus = player.getAccountStatus() - property.getPrice();
        player.setAccountStatus(accountStatus);

        property.setOwned(true);
        property.setOwner(player);

        System.out.println("Uspesne si kupil nehnutelnost!");
    }

    public int diceRoll() {

        Random rand = new Random();

        return rand.nextInt(6) + 1;
    }

    public void move(Player player, int diceRoll) {
        int playerPos = player.getPosition() + diceRoll;

        player.setPosition(playerPos);

    }

    public void start(Player player) {

        System.out.println("Presiel si startom, dostavas 1000 penazi");
        player.setPosition(player.getPosition() - 24);

        player.setAccountStatus(player.getAccountStatus() + 1000);
    }

    public void positionAnalyzer(Player player, Property property,List<Chance> list) {

        int playerPos = player.getPosition();
        if ((playerPos == 0) || (playerPos == 6) || (playerPos == 12) || (playerPos == 18)||(playerPos == 3) ||(playerPos == 9) ||(playerPos == 15) ||(playerPos == 21)) {
            switch (playerPos) {
                case 0:
                    break;
                case 6:
                    System.out.println("Skocil si na policko vazenie, si na navsteve");
                    break;
                case 12:
                    System.out.println("Skocil si na policko dan, platis 1500");
                    if (player.getAccountStatus()>1500){
                        tax(player, 1500);
                    }
                    else{
                        lose(player);
                    }
                    break;
                case 18:
                    Police police = new Police();
                    police.policeAction(player);
                    break;
                case 3: case 9: case 15: case 21:
                    System.out.println("Skocil si na policko sanca!");
                    for (int i = 0;i<list.size();i++) {
                        if (!list.get(i).isUsed()) {
                            list.get(i).cardsActions(list.get(i), player);
                            list.get(i).setUsed(true);
                            break;
                        }
                        if (list.get(list.size() - 1).isUsed()) {
                            list.get(i).setUnused(list);

                        }
                    }
                    break;

            }
        }
         else {
            if (property.isOwned()) {

                if (player.getAccountStatus() < property.getPrice()/10){
                    lose(player);
                }

                fine(player, property.getOwner(), property);
            } else {
                if (player.getAccountStatus() > property.getPrice()) {
                    System.out.println("Skocil si na nehnutelnost ktoru nikto nevlastni, ak ju chces kupit stlac Y");
                    char input = KeyboardInput.readChar(0);
                    if ((input == 'Y')||(input == 'y')) {
                        buy(player, property);
                    }
                }
            }


        }


    }

    public void lose(Player player){
        System.out.println("Nemas peniaze na zaplatenie pokuty, dohral si !!");
        player.setPlaying(false);
    }

}
