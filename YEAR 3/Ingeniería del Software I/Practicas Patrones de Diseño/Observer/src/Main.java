
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// motor observa el acelerador(sujetoObservable)
		
		Motor b8=new Motor();
		Acelerador x=new Acelerador();
		
		x.enlazarObservador(b8);
		
		x.pisarAcelerador();

	}

}
