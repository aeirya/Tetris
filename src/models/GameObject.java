package models;

public class GameObject implements IGameObject {

    private int x=0;
    private int y=0;
    private IGameObject[] kids = new GameObject[4];

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        for(IGameObject go : kids) {
            go.move(dx, dy);
        }
    }
}