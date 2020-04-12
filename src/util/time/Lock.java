package util.time;

public class Lock {

    private boolean isLocked = false;
  
    public synchronized void lock()
    throws InterruptedException{
        isLocked = true;
        while(isLocked){
            wait();
        }
    }
  
    public synchronized void unlock(){
        isLocked = false;
        notifyAll();
    }

    public synchronized boolean isLocked() {
        return isLocked;
    }
  }