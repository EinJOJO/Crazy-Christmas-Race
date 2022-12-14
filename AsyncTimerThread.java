public class AsyncTimerThread extends Thread {
    private Timer timer;
    Task task;

    public AsyncTimerThread (Task runnable, long time) {
        this.start();
        this.timer = new Timer();
        timer.setEnd(time);
        this.task = runnable;
    }

    public AsyncTimerThread(Task runnable, Timer timer) {
        this.timer = timer;
        this.task = runnable;
        this.start();
    }

    @Override
    public void run() {
        // System.out.println("[#" + this.getId() + "] Started Async Thread");
        if (!timer.isRunning()) {
            timer.start();
        }

        while (!timer.isFinished()) {
        }
        task.toRun();
        timer.stop();
        // System.out.println("[#" + this.getId() + "] Has finished the task");
        this.stop();
    }

}

interface Task {
    void toRun();
}