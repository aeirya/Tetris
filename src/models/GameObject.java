package models;

public class GameObject implements Fallable {

    int x=0;
    int y=0;

    public GameObject() {
        //
    }
    
    public void fall() {
        y-=1;
    }

    public void move() {
        //
    }
}