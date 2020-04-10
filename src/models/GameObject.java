package models;

public class GameObject implements IGameObject {

    protected int x=0;
    protected int y=0;
    private IGameObject kid;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(IGameObject kid, int x, int y) {
        this(x,y);
        this.kid = kid;
        kid.move(x, y);
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;  
    }

    public IGameObject copy() {
        return kid.copy();
    }
}