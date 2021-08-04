package model;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Monster extends Rectangle {

    private int hp;
    private int dmg;

    public Monster() {
        String[] allTypes = new String[]{"big", "medium", "small"};
        Random rand = new Random();
        setTranslateX(rand.nextInt(560) + 20);
        setTranslateY(rand.nextInt(560) + 20);
        setId("monster");
        String type = allTypes[rand.nextInt(3)];
        Image image = new Image("file:resources/" + type + "monster.png");
        setFill(new ImagePattern(image));
        switch (type) {
        case "big":
            setHeight(100);
            setWidth(80);
            this.hp = 100;
            this.dmg = 10;
            break;
        case "medium":
            setHeight(80);
            setWidth(60);
            this.hp = 60;
            this.dmg = 5;
            break;
        case "small":
            setHeight(60);
            setWidth(40);
            this.hp = 20;
            this.dmg = 1;
            break;
        default:
            break;
        }
    }

    public int getDmg() {
        return dmg;
    }

    public void takeDmg(int dmg) {
        this.hp -= dmg;
    }

    public int getHp() {
        return hp;
    }
}
