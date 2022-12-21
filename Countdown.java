import java.util.concurrent.TimeUnit;

public class Countdown extends Textbox
{
    private Timer timer;
    private Runnable onFinishCallback;
    private long runtimeMillis;
    boolean finished;
    
    public Countdown(long runtimeInMillis, Runnable onFinishCallbackMethod) {
        timer = new Timer();
        timer.setEnd(runtimeInMillis);
        this.runtimeMillis = runtimeInMillis;
        this.onFinishCallback = onFinishCallbackMethod;
        start();
    }
    
    public void act()
    {
        if (timer.isFinished()) {
            if (onFinishCallback != null) {
                onFinishCallback.run();
                onFinishCallback = null;
            };
            return;
        };
        long deltaTime = runtimeMillis - timer.timeRunning();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(deltaTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(deltaTime) - minutes * 60;
        String string = String.format("Verbleibende Zeit: %dm %ds", minutes, seconds);
        if(!timer.isFinished()){
            setText(string);
        } else {
            
        }
    }
    
    public void start(){
        if (!timer.isRunning()) {
            if (timer.isPaused()) {
                timer.resume();
            } else {
                timer.start();
            }
        }
    }
    
    public void pause() {
        timer.pause();
    }
    
    public Timer getTimer() {
        return timer;

        
    }
}
