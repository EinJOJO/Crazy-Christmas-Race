import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BeispielWelt extends World
{
    
    //Schritt 1 : Timer deklarieren und initiieren.
    Timer timer = new Timer(); 
    Timer timer2 = new Timer(); // Für andere Zeit

    public BeispielWelt()
    {    
        super(600, 400, 1); 

        //Schritt 2. Einmalig!!! den Timer starten und das Ende.
        timer.start();
        timer.setEnd(5 * 1000); // 5 Sekunden.
        

        timer2.start();
        timer.setEnd(2 * 1000); // 2 Sekunden

        // Was wird passieren? 
        // -> 5 Sekunden nachdem der Konstruktor bzw diese Methode ausgeführt wurde, wird die Abfrage [isFinished()] true zurück geben. 

    }
    
    @Override
    public void act() {
       // Die Act methode wird schleifen ähnlich ausgeführt. 
       // Bei jedem Durchgang wollen wir gucken, was mit dem Timer los ist,
       // um Konditionelle Anweisungen zu geben.


       if (timer.isRunning()) { // Erstmal wird geguckt, ob der Timer überhaupt läuft.
        if (timer.isFinished()) { // Wenn der Timer die 5 Sekunden erreicht hat: 
            timer.stop(); // Wenn der Timer nicht gestoppt wird, wird alles hier drinne absofort jede act() ausgeführt.

            // Soll irgendwas ausgeführt werden...
            System.out.println("Timer ist zu Ende.");    
            
        }
       } else {
        // Wird nichts getan. 
       }
    }



    public void repeatingTask() {
        if (timer.isRunning()) {
            if (timer.isFinished()) {
                timer.start(); // Setzt den Startzeitpunkt zuürck.

                // Code hier wird alle 5 Sekunden ausgeführt.
            }
        }
    }

    public void alternativeSchreibweise() {
        if (!timer.isRunning()) return; 
        if (!timer.isFinished()) return;
        timer.stop(); // Sorgt für einmalige Ausführung.

        // Code hier wird einmalig nach 5 Sekunden ausgeführt.
    }

    public void zweiTimer() {
        if (timer.isRunning()) {
            if (timer.isFinished()) { // Nach 5 Sekunden 
                timer.stop();
                System.out.println("Timer 1 aus zweiTimer() hat 5 Sekunden gewartet.");
            }
        }

        if (timer2.isRunning()) {
            if (timer2.isFinished()) { // Nach 2 Sekunden wird die Methode ständig ausgeführt.
                System.out.print("a"); 
            }
        }
        
    }


}
