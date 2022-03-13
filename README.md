
# Another Tetris Game

## Table of contents

---

### How to

1. [How to run](#how-to-run)
1. [How to play](#how-to-play)
### Resources

1. [External Libraries](#External-Libraries-Used)
1. [Code Sources](#Code-Sources-Used)

### Assets

1. [Soundtrack](#Sounds)
1. [Image Sources](#Images)



### Code Analysis

1. [How the game works](#How-the-game-works)
1. [Design patterns used](#Design-Patterns-Used)
1. [Deeper into the code](#Even-more)
1. [What I'm actually proud of](#What-I'm-proud-of)

Before running the game I suggest checking the [build version](#Build-Version) of your libraries

---

## How to Run
```
gradle run
```
## How to Play
move: a/d
rotate: q/e/w/s/arrows
drop: space


## External Libraries Used

nothing!

## Code Sources Used

A handful of sites I checked during making this are listed below

(it's worth noting I almost copied __no code__ from them whatsoever)

1. github issues
1. github.community
1. wikipedia 
1. geeksforgeeks
1. stackoverflow
1. codingame
1. dzone
1. gameprogrammingpatterns
1. docs.oracle
1. workplace.stackexchange
1. blog.eduonix
1. journaldev
1. educba
1. cs.cmu
1. Quota
1. Javaworld
1. javapathfinder.sourceforge
1. tutorials.jenkov
1. codota
1. tecmint 
1. medium
1. baeldung
1. Java2s
1. martinfowler
1. artima .com/intv
1. tutorialspoint

## Assets

### Sounds

threre was this funny clip I saw on instagram, cut a few parts of it and used it as my game assets

https://www.instagram.com/p/B-6YDMTggjk/?utm_source=ig_web_copy_link

### Images

there's only a mute logo and tbh, idk where I found it :))

---

## How the game works

### game update structure

```java
   public void update() {
        if (!isPaused) {
            updateState();
            updateGraphics();
        } else {
            doWait();
        }
    }
    
    //generates a "game state" object and stores it
    private void updateState() {
        state = manager.update(timer.isTickTime());
    }

    //then passes it to the game grahpics
    //we're also using a timer to keep things under control
    private void updateGraphics() {
        if (!timer.isLocked()) {
            timer.queue(gameGraphics::redraw);
            timer.flush();
            timer.holdOn();
        }
        gameGraphics.update(state);
    }
```

## Design Patterns Used

1. Singleton

    ```java
    public static Game getInstance() {
        return instance;
    }
    ```

    alongside interfaces connecting to game object:

    ```java
    private IMenuCommand parseMenuCommand(final KeyEvent e) {
        if (e.getKeyCode()==17) //control
            return Game::changeGameSpeed;

        switch(e.getKeyChar()) {
            case 'l':
            return Game::load;
            case 'r':
            return Game::reset;
            case 'm':
            return Game::toggleMenu;
            case 'v':
            return Game::save;
            case 'y':
            return Game::restore;
            case 't':
            return Game::quit;
            case 'p':
            return Game::togglePause;
            case 'u':
            return Game::toggleMute;
            default:
            return null;
        }
    }
    ```

    (Class::function is the method reference syntax added in Java 8, a better alternative here would be using Java 13's switch expressions)

1. Dependency Injection

    ```java
    private Game() {
        final GameSettings settings = new GameSettings("settings.properties");
        manager = new GameManager(timer, null);
        //passing the game manager to the input
        input = new Input(manager);
        //passing the settings holder and input listener to the graphics agent
        gameGraphics.setup(settings, input);
        new UiUpdater(gameGraphics).start();
    }
    ```

### Even more

there are other few not-exactly-pattern things used here like:

1. Composition

    ```java
    public class Tetrimino implements IGameObject, IShape, Drawable, Animate {
    //responsible for:
    //the location, and movements
    private final IGameObject body;
    //the orientation
    private final IShape shape;
    //draws the object
    private transient CollidableDrawList leonardoDaVinci = null;
    //flashy stuff u see on screen :p
    private transient Animator animator = new Animator();

    private Tetrimino(final IShape shape, final IGameObject body) {
        this.shape = shape;
        this.body = body;
        update();
    }

    //updates the status of tetrimino
    private void update() {
        leonardoDaVinci = createDrawList();
    }

    //the list holding the "drawables"
    private CollidableDrawList createDrawList() {
        return new CollidableDrawList(applyShape(body));
    }

    //multiplies a box at the body's location and applies the "template" stored in the shape to it 
    public List<IGameObject> applyShape(final IGameObject body) {
        return shape.applyShape(body);
    }
    ```

1. Enum constructors

    ```java
    public enum SoundEffect {
        BAW("baw"),
        DASH("dash1"),
        FELL("fell1"),
        GAMEOVER("gameover"),
        POOF("poof"),
        STACK("stack"),
        EXPLOSION("explosion");

    private SoundEffect(String soundFileName) { /* ... */ }
    ```

1. Generics, Threads, Consumers

    ```java
    public class CustomListener<T> {

        private final T comp;
        protected final Consumer<T> func;
        private final int waitTime;
        
        public CustomListener(T comp, int waitTime, Consumer<T> func) {
            this.comp = comp;
            this.func = func;
            this.waitTime = waitTime;
        }

        public void start() {
            new Thread(this::update).start();
        }

        private synchronized void update() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    func.accept(comp);
                    wait(waitTime);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    ```

1. Lambda Expressions and List Foreach Iterations

    ```java
    private static final List<JButton> btnList = new ArrayList<>();
    private static final transient
        Map <String, ActionListener> btnMap = Map.of(
            "Sorr(y)",
            (ActionEvent e) ->
                Game.getInstance().restore()
            ,
            "(R)estart",
            (ActionEvent e) -> 
                Game.getInstance().reset()
            ,
            "(L)oad",
            (ActionEvent e) -> 
                Game.getInstance().load()
            ,
            "Sa(v)e",
            (ActionEvent e) -> 
                Game.getInstance().save()
            ,
            "Qui(t)",
            (ActionEvent e) -> 
                Game.getInstance().quit()
        );

    private Box buttonsBox() {
        Box box = Box.createHorizontalBox();
        //generates the buttons off the btnMap
        btnMap.forEach(
            (String text, ActionListener listener) -> 
                btnList.add(makeButton(text, listener))
            );
        //a little tweak to the buttons
        btnList.forEach(btn -> btn.setFocusable(false));
        //and then adds them to the box
        btnList.forEach(box::add);
        return box;
    }

    private JButton makeButton(String text, ActionListener listener) { /* ... */ }
    ```

1. Functional Interfaces

    ```java
    @FunctionalInterface
    public interface ICommand {
        void act(Tetrimino t);
    }

    public interface ICommandReceiver {
        void receiveCommand(ICommand cmd);
    }
    ```

    an implementation:

    ```java
    public void receiveCommand(ICommand cmd) {
            if (inputLock.isUnlocked() && fallLock.isUnlocked()) {
                fallLock.unlock();
                cmd.act(current);
                //current : Tetrimino
            }
        }
    ```

1. Nested Classes

    ```java
    public class GameManager implements ICommandReceiver {
        public static class Lock { /* ... */ }
        private enum GameEvent {
            LINE_REMOVE,
            END_ROUND,
            SPAWN,
            GAMEOVER
        }
        private class GameEventHandler {
            private void call(GameEvent event) {
                get(event).run();
            }

            private Runnable get(GameEvent event) {
                switch(event) {
                    case GAMEOVER:
                    return this::gameOver;
                    case LINE_REMOVE:
                    return this::lineRemove;
                    case END_ROUND:
                    return this::endRound;
                    case SPAWN:
                    return this::spawn;
                    default:
                    return () -> {};
                }
            }
        }
    }
    ```

1. Reflection(?)

    This is how objects read the game state
    
    ```java
        gameState.get(this.getClass());
    ```

    Inside game state:

    ```java
    public Object get(Object receiver) {
            if (receiver.getClass()==GamePanel.class) {
                return getGamePanelDrawables();
            }
            if (receiver.getClass()==NextPanel.class) {
                return getNextPanelDrawables();
            }
            if (receiver.getClass()==ScorePanel.class) {
                return score;
            }
            if (receiver.getClass()== GameManager.class) {
                return new ReadableGameState(level, current, next, score);
            }
            return null;
        }
    ```

1. and builder pattern

    ```java
    public DrawList getGamePanelDrawables() {
        return new DrawList().add(level).add(current);
    }

    public DrawList getNextPanelDrawables() {
        return new DrawList().add(next);
    }
    ```

1. Haaave you met my interface? :D

    ```java

    public interface IGameObject extends Serializable {
        
        void move(int x, int y);

        /** Returns a clone of game object which has moved to the coordinate c */
        default IGameObject updatedCoordinates(Coordinate c) {
            IGameObject go = copy();
            go.move(c.getX(), c.getY());
            return go;
        }

        default void moveLeft() {
            move(-1,0);
        }
        
        default void moveRight() {
            move(1,0);
        }

        default void fall() {
            move(0, 1);
        }

        default void ascend() {
            move(0, -1);
        }

        void revert();
        void addTo(Map list);
        boolean collides(Map map);
        
        IGameObject copy();
    }
    ```

    ```java
    public interface IShape extends Serializable {
        void rotate(int i);
        default void rotateLeft() {
            rotate(1);
        }
        default void rotateRight() {
            rotate(-1);
        }
        void revert();
        List<IGameObject> applyShape(IGameObject body);
    }
    ```

    ```java
    public interface Drawable {
        void draw(Graphics g);
    }
    ``` 
    ```java
    public interface Animate {
        void toggleHidden();
        void show();
    }
    ```

### A Moment of truth

i guess using swing worker threads for this game was just too much.

```java

public class QueueWorker extends SwingWorker<Timer,Runnable>{

    Timer timer;
    List<Runnable> tasks;
    Runnable onDone;
    long counter = 0;

    public QueueWorker(List<Runnable> tasks, Runnable onDone) {
        this.tasks = tasks;
        this.onDone = onDone;
    }

    @Override
    protected Timer doInBackground() throws Exception {
        timer = new Timer();
        for (Runnable task : tasks) {
            publish(task);
        }
        return timer;
    }
    
    @Override
    protected void process(List<Runnable> chunks) {
        for (Runnable task : chunks) {
            task.run();
        }
    }

    @Override
    protected void done() {
        onDone.run();
    }
}

```

### What I'm proud of

#### OOP?

Once a wise java object said:
> Don't ask me questions, tell me what to do

I tried so much not to use getters and setters and was trying to achieve a true style of oop. Although It's near to impossible omit them completely , I'm actually rather satisfied with the results

```java
    public String toString() {
        return
            "Printing game state" + 
            "\n\tCurrent: " + ( (current != null) ? current.toString() : null ) +
            "\n\tNext: " + ( (next != null) ? next.toString() : null ) +
            "\n\tScore: " + score.getScore()
        ;
    }
```

(in this example the game state prints itself instead of exposing information to other objects)

#### taking this to the extremes

Even if we really have to do this:

```java
public class ReadableGameState extends GameState {
    
    public ReadableGameState(Level level, Tetrimino current, Tetrimino next, GameScore score) {
        super(level, current, next, score);
    }

    public Level getLevel() {
        return this.level;
    }
    
    public Tetrimino getCurrent() {
        return this.current;
    }

    public Tetrimino getNext() {
        return this.next;
    }

    public GameScore getScore() {
        return this.score;
    }
}
```

---

## Build Version
    
```
Gradle Version: Gradle 6.2.2
JVM: 13.0.2 (Oracle Corporation 13.0.2+8)
```
---

Another Tetris Game

README by [aeirya](https://github.com/aeirya)