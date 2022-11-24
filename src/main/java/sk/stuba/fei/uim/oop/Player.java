package sk.stuba.fei.uim.oop;

public class Player extends Actions{
    private int accountStatus;
    private int position;
    private final String name;
    private boolean jail;
    private boolean playing;



    public Player(int accountStatus, int position, String name, boolean jail, boolean playing) {
        this.accountStatus = accountStatus;
        this.position = position;
        this.name = name;
        this.jail = jail;
        this.playing = playing;
    }


    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isJail() {
        return jail;
    }

    public void setJail(boolean jail) {
        this.jail = jail;
    }

    public String getName() {
        return name;
    }
}
