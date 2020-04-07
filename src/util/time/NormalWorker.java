package util.time;

import javax.swing.SwingWorker;

public class NormalWorker extends SwingWorker <Void,Void> {

    Runnable function;

    public NormalWorker (Runnable function) {
        this.function = function;
    }

    protected Void doInBackground() throws Exception {
        function.run();
        return null;
    }
}