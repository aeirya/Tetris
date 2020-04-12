package models;

public class GameObject implements IGameObject {

    protected int x=0;
    protected int y=0;
    private IGameObject kid = null;
    private Runnable revert = () -> move(0,0);
    
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

    public boolean collides(IGameObject[][] objects) {
        if (y<0) return false;
        // System.out.println(x+","+y);
        // System.out.println(objects[0].length + "," + objects.length);
        // System.out.println(objects[x][y]);
        return objects[x][y]!=null;
        // return false;
    }

    public IGameObject copy() {
        return kid.copy();
    }

    public void addTo(IGameObject[][] map) {
        map[x][y] = this;
    }
}