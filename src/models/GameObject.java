package models;

public class GameObject implements IGameObject {

    protected int x=0;
    protected int y=0;
    private IGameObject kid = null;
    private Runnable revert;
    
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
        if (kid!=null) {
            kid.move(dx, dy);
        }
        revert = () -> move(-1*dx, -1*dy);
    }
    
    private void revertMove() {
        this.revert.run();
        revert = () -> {};
    }

    public void revert() {
        revertMove();
    }

    public boolean collides(Coordinate...coordinates) {
        for (Coordinate c : coordinates) {
            if (c.getX()==x && c.getY()==y) {
                return true;
            }
        }
        return false;
    }

    public IGameObject copy() {
        return kid.copy();
    }

    public void addTo(IGameObject[][] map) {
        map[x][y] = this;
    }
}