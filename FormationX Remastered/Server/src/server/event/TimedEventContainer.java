package server.event;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Arrowzftw
 *
 */

public class TimedEventContainer {

	public void addEvent(TimedEvent e) {
		doEvent(e);
	}

	public void removeEvent(TimedEvent e) {
		e.stop();
	}

	public void doEvent(final TimedEvent e) {
		final Timer timer = new Timer();
		try {

			timer.schedule(new TimerTask() {
				public void run() {
					e.execute();

					if (e.runTimes > 0) {
						e.runTimes -= 1;
					}
					if (e.runTimes > 0 || e.repeating) {
                                                 timer.cancel();
						doEvent(e);
					}
					if (e.runTimes == 0 && e.repeating == false) {
						timer.cancel();
						removeEvent(e);
					} else if (e.repeating == false && e.runTimes <= 0) {
						timer.cancel();
						removeEvent(e);
					}

				}
			}, e.runDelay);

		} catch (Exception i) {
			timer.cancel();
			if (e != null) {
				removeEvent(e);

			}
		}
	}

}