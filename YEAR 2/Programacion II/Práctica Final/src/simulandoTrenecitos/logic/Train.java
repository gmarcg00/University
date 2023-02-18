package simulandoTrenecitos.logic;

public class Train {
		private int length;
		private int x;
		private int y;
		private MovimientoTren move;
		
		public Train(MovimientoTren move, int length, int x, int y) {
			this.move=move;
			this.x=x;
			this.y=y;
			this.length=length;
		}
		
		public int getLength() {
			return this.length;
		}
		
		public void setLength(int length) {
			this.length=length;
		}
		
		public int getX() {
			return this.x;
		}
		
		public void setX(int x) {
			this.x=x;
		}
		
		public int getY() {
			return this.y;
		}
		
		public void setY(int y) {
			this.y=y;
		}
		
		public MovimientoTren getMove() {
			return this.move;
		}
		
		public void setMove(MovimientoTren move) {
			this.move=move;
		}
		
}
