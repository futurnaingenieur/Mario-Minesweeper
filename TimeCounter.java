/*
 * The TimeCounter class is diretly linked to the Segment class.
 * When a time counter is started, it make the segment change of digit
 * each second. It's how the time is manage in the MainFrame.
 */
public class TimeCounter implements Runnable {
	Thread thread;
	Segment aff;
	boolean marche=true;
	boolean threadSuspended=false;

	public TimeCounter(Segment compteur) {
		aff=compteur;
	}
	public void run() {
		while (marche) {
			try {
				thread.sleep(1000);
				if (threadSuspended) {
					synchronized(this) {
						while (threadSuspended)
							wait();
					}
				}
			}
			catch(java.lang.InterruptedException e) {}
			int time = aff.getValeur();
			if (marche && time<999) {//Taking more than 999s, it's kind of impossible!
				aff.setValeur(time+1);
			}

		}
	}
	
	public void start() {
		if (thread==null) thread = new Thread(this);
		thread.setPriority(thread.MAX_PRIORITY);
		thread.start();
	}
	
	public void stop() {
		if (thread!=null) thread = null;
	}
	
	public void cancel() {
		marche=false;
	}
	
	public void suspend() {
		threadSuspended=true;
	}
	
	public synchronized void resume() {
		threadSuspended=false;
		notify();
	}
}
