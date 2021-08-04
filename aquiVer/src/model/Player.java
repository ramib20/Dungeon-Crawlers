package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {

    private final String name;
    private final String difficulty;
    private final int money;
    private int hp;
    private int dmg;
    private int monstersKilled;
    private int dmgDealt;

    public Player(int x, int y, int width, int height, String name,
                  String difficulty, String item) {
        super(width, height);
        setTranslateX(x);
        setTranslateY(y);
        setId("player");
        Image image = new Image("file:resources/player.png");
        setFill(new ImagePattern(image));
        this.name = name;
        this.difficulty = difficulty;
        this.hp = 300;

        switch (this.difficulty) {
        case "Easy":
            this.money = 300;
            break;
        case "Medium":
            this.money = 200;
            break;
        case "Hard":
            this.money = 100;
            break;
        default:
            this.money = 0;
            break;
        }

        switch (item) {
        case "Sword":
            this.dmg = 20;
            break;
        case "Axe":
            this.dmg = 15;
            break;
        case "Scythe":
            this.dmg = 10;
            break;
        default:
            break;
        }
    }

    public void moveLeft() {
        setTranslateX(getTranslateX() - 15);
    }

    public void moveRight() {
        setTranslateX(getTranslateX() + 15);
    }

    public void moveUp() {
        setTranslateY(getTranslateY() - 15);
    }

    public void moveDown() {
        setTranslateY(getTranslateY() + 15);
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getMoney() {
        return money;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHp() {
        return hp;
    }

    public int getDmgDealt() {
        return dmgDealt;
    }

    public int getMonstersKilled() {
        return monstersKilled;
    }

    public int getDmgTaken() {
        return 300 - this.hp;
    }

    public void takeDmg(int dmg) {
        this.hp = this.hp - dmg;
    }

    public void killedMonster() {
        monstersKilled++;
    }

    public void didDmg(int dmg) {
        dmgDealt += dmg;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }
}