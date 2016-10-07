package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import rules.Square;
import rules.*;

@SuppressWarnings("serial")
public class Panel extends JPanel{
	private final int WIDTH = Options.WIDTH;
	private final int HEIGHT = Options.HEIGHT;
	private final int SQUARE_SIZE = Options.SQUARE_SIZE;
	
	private final int NSQUARES_WIDTH = Options.NSQUARES_WIDTH;
	private final int NSQUARES_HEIGHT = Options.NSQUARES_HEIGHT;
	
	@SuppressWarnings("unused")
	private boolean draw;
	
	public static Square[][] grid;
	
	public Panel(){
		setDraw(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    
		grid = new Square[WIDTH/SQUARE_SIZE][HEIGHT/SQUARE_SIZE];
		init();
	    
	}
	
	private void drawGrid(Graphics g){
		
		for(int i = 0; i < NSQUARES_WIDTH; i++){
			for(int j = 0; j < NSQUARES_HEIGHT; j++){
				g.drawRect(i*SQUARE_SIZE, j*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);//Black Grid
				
				if(grid[i][j].isAlive()){
					g.fillRect(i*SQUARE_SIZE, j*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);//Black Square
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		drawGrid(g);
		System.out.println("repainted");
	}
	
	
	private MouseListener mouselistener = new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		int x = e.getX()/SQUARE_SIZE, y = e.getY()/SQUARE_SIZE;
    		Square s = grid[x][y];
    	
    		if(e.getButton() == MouseEvent.BUTTON1){
    			System.out.println("Clicked x = " + x + " y = " + y);
        		if(!s.isAlive()){
        			s.setAlive(true);
        		}
        		else
        			s.setAlive(false);
    		}
    		else{
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
    			
    			System.out.println("x["+x+"] y["+y+"]");
    			System.out.println("Alive : " + s.isAlive());
    			System.out.println("Has Top : " + s.Top());
    			System.out.println("Has Left : " + s.Left());
    			System.out.println("Has Right : " + s.Right());
    			System.out.println("Has Bottom : " + s.Bottom());
    			System.out.println("Has TopLeft : " + s.TopLeft());
    			System.out.println("Has TopRight : " + s.TopRight());
    			System.out.println("Has BottomLeft : " + s.BottomLeft());
    			System.out.println("Has BottomRight : " + s.BottomRight());
    			System.out.println("p : " + p);
    		}
    		repaint();
    	}
    	
	};
	
	public void setDraw(boolean draw){
		this.draw = draw;
		if(!draw)
			removeMouseListener(mouselistener);
		else
			addMouseListener(mouselistener);
	}
	
	public void clearSquares(){
		init();
		repaint();
	}
	
	private void init(){
		for(int i = 0; i < NSQUARES_WIDTH; i++){
			for(int j = 0; j < NSQUARES_HEIGHT; j++){
				grid[i][j] = new Square(i,j);
			}
		}
	}
}
