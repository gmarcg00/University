package es.unileon.prg1.candyClean;



public class Color {
	private final String BLACK = "\u001B[40m";
	private final String RED = "\u001B[41m";
	private final String GREEN = "\u001B[42m";
	private final String YELLOW = "\u001B[43m";
	private final String BLUE = "\u001B[44m";
	private final String PURPLE = "\u001B[45m";
	private final String CYAN = "\u001B[46m";
	private final String WHITE = "\u001B[47m";
	private final String RESET = "\u001B[0m";

	private String color;

	public Color(BackgroundColor color) {
		switch (color) {
		case BLACK:
			this.color = this.BLACK;
			break;
		case RED:
			this.color = this.RED;
			break;
		case GREEN:
			this.color = this.GREEN;
			break;
		case YELLOW:
			this.color = this.YELLOW;
			break;
		case BLUE:
			this.color = this.BLUE;
			break;
		case PURPLE:
			this.color = this.PURPLE;
			break;
		case CYAN:
			this.color = this.CYAN;
			break;
		case WHITE:
			this.color = this.WHITE;
			break;
		}
	}

	public Color(int i) {
		this(BackgroundColor.values()[i]);
	}

	public Color(Color another) {
		this.color = another.getColor();
	}

	private String getColor() {
		return this.color;
	}

	public String getColorName() {
		return getColor();
	}

	public boolean equals(Color another) {
		return this.color.equals(another.getColor());
	}

	public String toString(String content) {
		return this.color + content + this.RESET;
	}

	@Override
	public String toString() {
		return this.color + "  " + this.RESET;
	}

}
