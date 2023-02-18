package simulandoTrenecitos.ui.gui.componentes;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class Casilla extends JButton {
	
	private int x;
	private int y;
	private String dir;
	
	
	public Casilla(int x, int y, String dir) {
		this.setBoardX(x);
		this.setBoardY(y);
		this.setBoardDir(dir);
		setMinimumSize(new Dimension(30,30));
		setMaximumSize(new Dimension(30,30));
		
	}



	public int getBoardY() {
		return y;
	}


	public void setBoardY(int y) {
		this.y = y;
	}


	public int getBoardX() {
		return x;
	}


	public void setBoardX(int x) {
		this.x = x;
	}


	public String getBoardDir() {
		return dir;
	}


	public void setBoardDir(String dir) {
		this.dir = dir;
	}
}
