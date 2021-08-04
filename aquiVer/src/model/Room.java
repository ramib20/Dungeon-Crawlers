package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Room {
    private final HashMap<String, Rectangle> allDoors;
    private HashMap<String, Rectangle> doors;
    private final ArrayList<Monster> monsters;

    public Room(String id) {
        this.allDoors = new HashMap<>();
        this.doors = new HashMap<>();
        this.monsters = new ArrayList<>();
        initAllDoors();
        if (id.equals("1")) {
            this.doors = allDoors;
        } else {
            generateDoors(id);
            initMonsters(id);
        }
    }

    private void initAllDoors() {
        Rectangle topDoor = new Rectangle(100, 30);
        topDoor.setTranslateX(300);
        topDoor.setTranslateY(0);
        Image image = new Image("file:resources/topdoor.png");
        topDoor.setFill(new ImagePattern(image));
        this.allDoors.put("W", topDoor);

        Rectangle bottomDoor = new Rectangle(100, 30);
        bottomDoor.setTranslateX(300);
        bottomDoor.setTranslateY(670);
        image = new Image("file:resources/bottomdoor.png");
        bottomDoor.setFill(new ImagePattern(image));
        this.allDoors.put("S", bottomDoor);

        Rectangle leftDoor = new Rectangle(30, 100);
        leftDoor.setTranslateX(0);
        leftDoor.setTranslateY(300);
        image = new Image("file:resources/rightdoor.png");
        leftDoor.setFill(new ImagePattern(image));
        this.allDoors.put("A", leftDoor);

        Rectangle rightDoor = new Rectangle(30, 100);
        rightDoor.setTranslateX(670);
        rightDoor.setTranslateY(300);
        image = new Image("file:resources/leftdoor.png");
        rightDoor.setFill(new ImagePattern(image));
        this.allDoors.put("D", rightDoor);
    }

    private void initMonsters(String id) {
        Random rand = new Random();
        if (id.length() == 8) {
            int numMonsters = rand.nextInt(3) + 5;
            for (int i = 0; i < numMonsters; i++) {
                monsters.add(new Monster());
            }
        } else {
            int numMonsters = rand.nextInt(3) + 1;
            for (int i = 0; i < numMonsters; i++) {
                monsters.add(new Monster());
            }
        }
    }

    private void generateDoors(String id) {
        ArrayList<String> indices = new ArrayList<>();
        Random rand = new Random();
        switch (id.charAt(id.length() - 1)) {
        case 'S':
            this.doors.put("W", this.allDoors.get("W"));
            if (id.length() == 8) {
                break;
            }
            indices.add("A");
            indices.add("S");
            indices.add("D");
            break;
        case 'W':
            this.doors.put("S", this.allDoors.get("S"));
            if (id.length() == 8) {
                break;
            }
            indices.add("A");
            indices.add("W");
            indices.add("D");
            break;
        case 'A':
            this.doors.put("D", this.allDoors.get("D"));
            if (id.length() == 8) {
                break;
            }
            indices.add("W");
            indices.add("S");
            indices.add("A");
            break;
        case 'D':
            this.doors.put("A", this.allDoors.get("A"));
            if (id.length() == 8) {
                break;
            }
            indices.add("D");
            indices.add("S");
            indices.add("W");
            break;
        default:
            break;
        }
        int numDoors = rand.nextInt(3) + 1;
        for (int i = 0; i < numDoors; i++) {
            if (!indices.isEmpty()) {
                int num = rand.nextInt(indices.size());
                String ch = indices.remove(num);
                doors.put(ch, this.allDoors.get(ch));
            } else {
                break;
            }
        }
    }

    public HashMap<String, Rectangle> getDoors() {
        return doors;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void killMonster(Monster monster) {
        monsters.remove(monster);
    }
}
