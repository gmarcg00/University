
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persona persona = Persona.getInstance();
		Persona persona_2=Persona.getInstance();
		persona.setEdad(22);
		System.out.println(persona_2.getEdad());
	}

}
