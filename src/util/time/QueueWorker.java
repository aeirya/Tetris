package util.time;

import java.util.List;

import javax.swing.SwingWorker;

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