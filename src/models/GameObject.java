package models;

public class GameObject implements Fallable {

    int x=0;
    int y=0;

    public GameObject() {
        //
    }

    private void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    public void fall() {
        move(0, 1);
    }

    public void moveLeft() {
        move(-1,0);
    }

    public void moveRight() {
        move(1,0);
    }
}