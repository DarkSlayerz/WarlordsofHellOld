package server.event;

/**
 * @author Arrowzftw
 *
 */

public abstract class TimedEvent
{
    /*
     * Repeat Event
     * 
     */
    public TimedEvent(int runDelay)
    {
        this.repeating = true;
        this.runDelay = runDelay;
        defaultDelay = runDelay;
    }
    
    /*
     * Play Event once if false
     * 
     */

    public TimedEvent(int runDelay, boolean repeating)
    {
        this.repeating = repeating;
        this.runDelay = runDelay;
        defaultDelay = runDelay;
    }

    /*
     * Certain amount of times repeating
     * 
     */
    public TimedEvent(int runDelay, int runTimes)
    {
        this.runDelay = runDelay;
        this.runTimes = runTimes;
        defaultDelay = runDelay;
    }

    public abstract void execute();



    public void stop()
    {
        repeating = false;
        runTimes = 0;
    }

    public int runDelay;
    public int defaultDelay;
    public int runTimes;
    public boolean repeating;
}