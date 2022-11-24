package sk.stuba.fei.uim.oop;



import java.util.ArrayList;
import java.util.List;

public class Game {



    public void start() {
        Player currentPlayer;
        Property property;
        String name;
        int numberOfPlayers;
        List<Player> list = new ArrayList<>();
        Property[] properties = new Property[24];

        List<Chance> listC= new ArrayList<>();
        Chance chance = new Chance();

        for (int i = 1; i<24; i+=3){
            properties[i] = new Property(i*200,false);
            properties[i+1] = new Property((i+1)*200,false);
        }

        for(int i = 0;i < 5;i++){
            listC.add(new Chance(false,i));
        }
        
        chance.shuffle(listC);

        System.out.println("Zadaj pocet hracov:");
        numberOfPlayers = KeyboardInput.readInt(10,"Zadaj cele cislo");


        for (int i = 0; i < numberOfPlayers; i++){
            System.out.println("Zadaj meno hraca cislo " + (i+1));
            name = KeyboardInput.readString(1);
            list.add(new Player(10000,0,name,false,true));
        }

        int i = 0;
        int players;

        do {
            players = 0;
            int diceRoll;
            currentPlayer = list.get(i);


            if (!currentPlayer.isJail() & currentPlayer.isPlaying()) {

                System.out.println("Ide hrac " + currentPlayer.getName() + " ktory je na pozicii " + currentPlayer.getPosition() + " a ma " + currentPlayer.getAccountStatus() + " penazi");
                System.out.println("Stlac enter pre hod kockou....");
                KeyboardInput.readChar();
                diceRoll = currentPlayer.diceRoll();
                System.out.println("Hrac " + currentPlayer.getName() + " hodil:" + diceRoll);
                System.out.println("Hrac " + currentPlayer.getName() + " ma " + currentPlayer.getAccountStatus() + "penazi");
                currentPlayer.move(currentPlayer, diceRoll);

                if (currentPlayer.getPosition() > 23) {
                    Start start = new Start();
                    start.startAction(currentPlayer);
                }

                System.out.println("Hrac " + currentPlayer.getName() + " sa posuva na poziciu " + currentPlayer.getPosition());
                property = properties[currentPlayer.getPosition()];
                currentPlayer.positionAnalyzer(currentPlayer, property,listC);


            }
            else {

                if (currentPlayer.isJail()) {

                    System.out.println("Hrac " + currentPlayer.getName() + " je vo vazeni ");
                    currentPlayer.setJail(false);
                }

            }
            i++;

            if (i > numberOfPlayers - 1) {
                i -= numberOfPlayers;
            }

            System.out.println("------------------------------------------------");

            for (int j = 0; j < numberOfPlayers; j++) {
                if (!list.get(j).isPlaying()) {
                    players++;
                }
            }
        } while (players != numberOfPlayers - 1);

        for (i = 0; i < numberOfPlayers; i++) {
            if (list.get(i).isPlaying()) {
                System.out.println(list.get(i).getName() + " je VÍ-ŤAZ");
            }
        }




    }

}
