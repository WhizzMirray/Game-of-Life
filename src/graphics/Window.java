package graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import rules.Lifeloop;

@SuppressWarnings("serial")
public class Window extends JFrame{
	private Panel panel;
	private Lifeloop life;
	private JSlider slider;
	Window(){
		panel = new Panel();
		
		JToolBar tool = new JToolBar();
		add(tool,BorderLayout.NORTH);
		tool.add(start);
		tool.add(pause);
		
		tool.add(stop);
		pause.setEnabled(false);
		stop.setEnabled(false);
		slider = new JSlider(JSlider.HORIZONTAL,0,600,300);
		slider.setValue(60);
		slider.addChangeListener(e -> life.setSleep(600-slider.getValue()));
		tool.add(slider);
		tool.add(Box.createHorizontalStrut(400));
		add(panel);
		setTitle("Game of Life");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
	}
	
	
	
	public static void main(String[] args) {
		new Window();
	}
	
	private Action start = new AbstractAction("Start") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.setDraw(false);
			stop.setEnabled(true);
			pause.setEnabled(true);
			start.setEnabled(false);
			life = new Lifeloop(panel);
			life.setSleep(600-slider.getValue());
		
		}
	};
	
	private Action pause = new AbstractAction("Pause") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!life.ispaused()){
				life.pause(true);
				pause.putValue(Action.NAME, "Resume");
			}
			else{
				life.resumeThread();
				pause.putValue(Action.NAME, "Pause");
			}
			
		}
	};
	
	private Action stop = new AbstractAction("Stop") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.clearSquares();
			panel.setDraw(true);
			stop.setEnabled(false);
			pause.setEnabled(false);
			start.setEnabled(true);
			life.setRunning(false);
		}
	};

}
