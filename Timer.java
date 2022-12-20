import java.util.HashMap;
import java.util.Map;

/**
 * Timer Klasse von Johannes für Alle.
 * 
 * 
 * Um den Timer zu nutzen: 
 * 1. Variable erstellen,
 *      Timer timer = new Timer();
 * 2. Timer Starten
 *      timer.start();
 * 3. Abfragen. 
 */
public class Timer implements Logger.Loggable
{

    private long timerStarted; 
    private boolean running = false;
    private long ende = 0;
    private boolean paused;
    private long pauseStartTimeMillis;
    private long pausedTimeMillis;
    
    /**
     * @return ob der Timer gerade läuft. 
     */
    public boolean isRunning() {
        return running && !paused;
    }
    
    /**
     * Setze den Startzeitpunkt.
     */
    public void start() {
        timerStarted = System.currentTimeMillis();  // Aktuelle Zeit in Millisekunden seit 1970.
        running = true;
    }


    public void pause() {
        paused = true;
        pauseStartTimeMillis = System.currentTimeMillis();
    }

    public boolean isPaused() {
        return paused;
    }

    /**
     * Lasse den Timer weiterlaufen ohne einen neuen Startzeitpunkt zu setzen
     */
    public void resume() {
        paused = false;
        pausedTimeMillis += System.currentTimeMillis() - pauseStartTimeMillis;
    }
    /**
     * @return Ob der Timer das Ende erreicht hat, was vorher definiert wurde.
     */
    public boolean isFinished() { 
        return System.currentTimeMillis() >= timerStarted + ende + pausedTimeMillis; // Ob die aktuelle Zeit größer ist als die Startzeit + die zuwartende Zeit. + Die Zeit, die mit Pause machen verbracht wurde.
    }

    public long timeRunning() {
        long deltaTime = System.currentTimeMillis() - timerStarted;
        deltaTime -= pausedTimeMillis;
        return deltaTime;
    }
    
    /**
     * @param timeMillis Abzuwartende Zeit in Millisekunden bis isFinished() wahr zurückgibt.
     * Beispiel: 
     * 10 Sekunden => 1000 * 10 => setEnd(1000 * 10)
     * 1 Minute => setEnd(1000 * 60)
     * 5 Minuten => setEnd(1000 * 60 * 5)
     */
    public void setEnd(long timeMillis) { 
        
        ende = timeMillis;
    }
    
    /*
     * Sorgt dafür, dass isRunning() false zurück gibt.
     */
    public void stop() {
        running = false;
    }

    @Override
    public Map<String, String> getLogInfo() {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<>();
        
        map.put("isRunning", String.valueOf(isRunning()));
        map.put("isFinished", String.valueOf(isFinished()));
        map.put("timerStarted", String.valueOf(timerStarted));
        map.put("end", String.valueOf(ende));

        return map;
    }
}
