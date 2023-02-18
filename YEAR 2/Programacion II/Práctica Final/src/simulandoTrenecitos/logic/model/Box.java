package simulandoTrenecitos.logic.model;

import simulandoTrenecitos.logic.MovimientoTren;

public class Box {
	
	private String direccion;
	private boolean head;
	private BoxStatus status;
	private boolean locked;
	
	
	public Box(String direccion,boolean head,boolean locked) {
		this.direccion=direccion;
		this.head=head;
		this.status=BoxStatus.EMPTY;
		this.locked=locked;
		
	}
	
	 Box(Box box) {
		this.direccion=new String(box.direccion);
		this.head=box.head;
	}

	public void setHead(boolean head) {
		this.head=head;
	}
	
	public boolean getHead() {
		return this.head;
	}
	
	
	public void setDireccion(String direccion) {
		this.direccion=direccion;
		switch(direccion) {
		case "1":
			status=BoxStatus.UP;
			break;
		case "0":
			status=BoxStatus.DOWN;
			break;
		case "2":
			status=BoxStatus.LEFT;
			break;
		case "3":
			status=BoxStatus.RIGHT;
			break;
		case ".":
			status=BoxStatus.EMPTY;
			break;
		case "X":
			status=BoxStatus.COLLISION;
			break;
		
	}
	}
	
	public String getDirection() {
		return this.direccion;
	}
	
	
	@Override
	public String toString() {
		String output=" ";
		output+=direccion;
		
		return output;
		
	}

	public BoxStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(BoxStatus status) {
		this.status=status;
	}

	public boolean getLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
