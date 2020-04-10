package models;

import java.util.List;

public class GameObject implements IGameObject {

    protected int x=0;
    protected int y=0;
    private GameObject root;
    private List<IGameObject> kids;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        root = this.copy();
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        for(IGameObject go : kids) {
            go.move(dx, dy);
        }   
    }

    private void addComponent(IGameObject go) {
        kids.add(go);
    }

    public void addComponents(IGameObject... objects) {
        for (IGameObject obj : objects) {
            addComponent(obj);
        }
    }

    public IGameObject[] getChildren() {
        return kids;
    }

    public void sumCoordinate(Coordinate c) {
        Coordinate newC = c.sum(x, y);
        
    }
}