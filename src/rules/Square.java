package rules;
public class Square {
	private final int x;
	private final int y;
	private boolean alive;
	private boolean aliveNext;
	public Square(int x,int y){
		this.x = x;
		this.y = y;
		setAlive(false);
		setAliveNext(false);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}


	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean  Top(){
		if(y == 0)
			return false;
		return Lifeloop.grid[x][y-1].isAlive();
	}
	
	public boolean Bottom(){
		if(y == Options.NSQUARES_HEIGHT-1)
			return false;
		return Lifeloop.grid[x][y+1].isAlive();
	}
	
	public boolean Left(){
		if(x == 0)
			return false;
		return Lifeloop.grid[x-1][y].isAlive();		
	}
	
	public boolean Right(){
		if(x == Options.NSQUARES_WIDTH - 1)
			return false;
		return Lifeloop.grid[x+1][y].isAlive();
	}
	
	public boolean TopLeft(){
		if(y == 0 || x == 0)
			return false;
		return Lifeloop.grid[x-1][y-1].isAlive();
	}
	
	public boolean TopRight(){
		if(y == 0 || x == Options.NSQUARES_WIDTH - 1)
			return false;
		return Lifeloop.grid[x+1][y-1].isAlive();
	}
	
	public boolean BottomLeft(){
		if(y == Options.NSQUARES_HEIGHT - 1 || x == 0)
			return false;
		return Lifeloop.grid[x-1][y+1].isAlive();
	}
	
	public boolean BottomRight(){
		if(y == Options.NSQUARES_HEIGHT - 1 || x == Options.NSQUARES_WIDTH - 1)
			return false;
		return Lifeloop.grid[x+1][y+1].isAlive();
	}

	public boolean isAliveNext() {
		return aliveNext;
	}

	public void setAliveNext(boolean aliveNext) {
		this.aliveNext = aliveNext;
	}
	
}
