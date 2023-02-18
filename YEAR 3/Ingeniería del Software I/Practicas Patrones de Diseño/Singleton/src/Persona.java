
public class Persona {
	
	private static Persona persona;
	private int edad;
	
	private Persona() {
		
	}
	
	public static Persona getInstance() {
		if(persona==null) {
			persona=new Persona();
		}
		
		return persona;
	}
	
	public void hablar(String msg) {
		System.out.println(msg);
	}
	

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
