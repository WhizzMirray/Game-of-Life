package rules;

import graphics.*;

public class Lifeloop implements Runnable{
	
	private static final int NSQUARES_WIDTH = Options.NSQUARES_WIDTH;
	private static final int NSQUARES_HEIGHT = Options.NSQUARES_HEIGHT;
	private Thread gameThread;
	private Panel panel;
	private boolean running;
	private int sleep = 600;
	private boolean pause;
	
	public static Square[][] grid = Panel.grid;
	
	public Lifeloop(Panel panel){
		pause = false;
		running = true;
		gameThread = new Thread(this);
		this.panel = panel;
		gameThread.start();

	}
	
	@Override
	public void run() {
		System.out.println("Game thread runnined");
		while(running){
				//panel.pause(true);
				//System.out.println("draw thread is paused");
				for(int i = 0; i < NSQUARES_WIDTH; i++){
					for(int j = 0; j < NSQUARES_HEIGHT; j++){
						Square s = grid[i][j];
						int p = 0;
						if(s.Top())
		    				p++;
		    			if(s.Left())
		    				p++;
		    			if(s.Right())
		    				p++;
		    			if(s.Bottom())
		    				p++;
		    			if(s.TopLeft())
		    				p++;
		    			if(s.TopRight())
		    				p++;
		    			if(s.BottomLeft())
		    				p++;
		    			if(s.BottomRight())
		    				p++;
		    			
		    			if(s.isAlive()){
		    				if(p < 2 || p > 3)
		    					s.setAliveNext(false);
		    				else{
		    					s.setAliveNext(true);
		    				}
		    			}
		    			else{
		    				if(p == 3)
		    					s.setAliveNext(true);
		    			}
		    		
		    				
					}
				}
				for(int i = 0; i < NSQUARES_WIDTH; i++){
					for(int j = 0; j < NSQUARES_HEIGHT; j++){
						Square s = grid[i][j];
						if(s.isAliveNext())
							s.setAlive(true);
						else
							s.setAlive(false);
					}
				}
					
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				panel.repaint();
				if(pause)
					pauseThread();
			}
	}
	

	public void setSleep(int sleep){
		this.sleep = sleep;
	}
	private void pauseThread(){
		try {
			synchronized (this) {
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void stop(){
		gameThread.interrupt();
	}
	public void setRunning(boolean running){
		this.running = running;
	}
	
	public boolean isstoped() {
		return running;
	}
	public void pause(boolean pause){
		this.pause = pause;
	}
	public void resumeThread() {
        synchronized (this) {
            pause = false;
            this.notifyAll(); // Unblocks thread
        }      
    }
	public boolean ispaused() {
		return pause;
	}
}
