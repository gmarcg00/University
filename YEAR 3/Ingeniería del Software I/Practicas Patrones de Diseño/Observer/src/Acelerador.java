import java.util.ArrayList;

public class Acelerador implements SujetoObservable{
	
	private ArrayList<Observador> observadores;
	
	public Acelerador() {
		observadores=new ArrayList<Observador>();
	}
	
	public void pisarAcelerador() {
		//Voy a acelerar
		notificar();
	}
	
	
	public void enlazarObservador(Observador o) {
		observadores.add(o);
	}
	
	@Override
	public void notificar() {
		for(Observador o: observadores) {
			o.update();
		}
		
	}

}
