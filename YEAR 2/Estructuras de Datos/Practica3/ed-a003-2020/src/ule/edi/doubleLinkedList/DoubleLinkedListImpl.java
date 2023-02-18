package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


	//	referencia al primer nodo de la lista
	private DoubleNode<T> front;

	//	referencia al Último nodo de la lista
	private DoubleNode<T> last;


	private class DoubleNode<T> {

		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}

		T elem;

		DoubleNode<T> next;
		DoubleNode<T> prev;
	}

	///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIterator<T> implements Iterator<T> {
		int repeticiones=0;
		DoubleNode<T> node;



		public DoubleLinkedListIterator(DoubleNode<T> nodo) {
			node=nodo;

		}

		@Override
		public boolean hasNext() {
			return node!=null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				DoubleNode<T> aux=node;
				node=node.next;
				return aux.elem;	
			}else {
				throw new NoSuchElementException();
			}
		}


	}

	////// FIN ITERATOR



	/// TODO :  AÑADIR OTRAS CLASES PARA LOS OTROS 3 ITERADORES
	@SuppressWarnings("hiding")
	private class DoubleLinkedListProgressIterator<T> implements Iterator<T> {
		int i=1;
		DoubleNode<T> node;



		public DoubleLinkedListProgressIterator(DoubleNode<T> nodo) {
			node=nodo;

		}

		@Override
		public boolean hasNext() {
			return node!=null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				DoubleNode<T> aux =node;
				int count=0;
				while(count<i && node!=null) {
						node=node.next;
						count++;
				}
				i++;		
				return aux.elem;

			}else {
				throw new NoSuchElementException();
			}
		}


	}


	@SuppressWarnings("hiding")
	private class DoubleLinkedListReverseIterator<T> implements Iterator<T> {
		int repeticiones=0;
		DoubleNode<T> node;



		public DoubleLinkedListReverseIterator(DoubleNode<T> nodo) {
			node=nodo;

		}

		@Override
		public boolean hasNext() {
			return node!=null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				DoubleNode<T> aux=node;
				node=node.prev;
				return aux.elem;	
			}else {
				throw new NoSuchElementException();
			}
		}


	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListEvenPositionsIterator<T> implements Iterator<T> {
		int repeticiones=0;
		DoubleNode<T> node=null;



		public DoubleLinkedListEvenPositionsIterator(DoubleNode<T> nodo) {
			if(nodo.next!=null) node=nodo.next;

		}

		@Override
		public boolean hasNext() {
			return node!=null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				DoubleNode<T> aux=node;
				node=node.next;
				if(hasNext()){
					node = node.next;
				}
				return aux.elem;	
			}else {
				throw new NoSuchElementException();
			}
		}


	}

	/////

	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		for (T elem:v) {
			this.insertLast(elem);
		}
	}


	@Override
	public boolean isEmpty() {
		return front==null;
	}


	@Override
	public void clear() {
		front=null;
		last=null;

	}


	@Override
	public void insertFirst(T elem) {
		if(elem==null)
			throw new NullPointerException();
		if(isEmpty()) {
			front=last=new DoubleNode<T>(elem);
		}else {
			DoubleNode<T> aux=front;
			front=new DoubleNode<T>(elem);
			front.prev=null;
			front.next=aux;
			aux.prev=front;
		}

	}


	@Override
	public void insertLast(T elem) {
		if(elem==null)
			throw new NullPointerException();
		if(isEmpty()) {
			front=last=new DoubleNode<T>(elem);
		}else {
			DoubleNode<T> aux=last;
			last=new DoubleNode<T>(elem);
			last.next=null;
			last.prev=aux;
			aux.next=last;
		}

	}


	@Override
	public T removeFirst() throws EmptyCollectionException{
		if(isEmpty())
			throw new EmptyCollectionException(null);

		DoubleNode<T> aux=front;

		front=front.next;
		front.prev=null;


		return aux.elem;
	}



	@Override
	public T removeLast()  throws EmptyCollectionException{
		if(isEmpty())
			throw new EmptyCollectionException(null);

		DoubleNode<T> aux=last;

		last=last.prev;
		last.next=null;

		return aux.elem;
	}


	@Override
	public void insertPos(T elem, int position) {
		if(elem==null)
			throw new NullPointerException();
		if(position<=0)
			throw new IllegalArgumentException();

		int contador=1;

		DoubleNode<T> newNode= new DoubleNode<T>(elem);//Nodo que se coloca en la posicion que te pasan

		if(position>size()) {
			insertLast(elem);
		}else if(position==1){
			insertFirst(elem);
		}else {

			DoubleNode<T> node=front;//Nodo que estaba en la posicion que te pasan
			while(contador<position ) {
				node = node.next;
				contador++;
			}
			newNode.prev=node.prev;
			newNode.next=node;

			node.prev.next=newNode;
			node.prev=newNode;
		}

	}

	public DoubleNode<T> getNode(int pos){
		int position=1;
		DoubleNode<T> aux= front;
		while(position<pos) {
			aux=aux.next;
			position++;
		}
		return aux;
	}


	@Override
	public void insertBefore(T elem, T target) {
		DoubleNode<T> aux=front;

		while(!aux.elem.equals(target)){
			aux=aux.next;
		}
		DoubleNode<T> newNode=new DoubleNode<T>(elem);


		aux.prev.next=newNode;
		aux.prev=newNode;

		newNode.next=aux;
		newNode.prev=aux.prev;
	}


	@Override
	public T getElemPos(int position) {
		if(position<1 || position>size())
			throw new IllegalArgumentException();

		return getNode(position).elem;
	}


	@Override
	public int getPosFirst(T elem) {
		if(elem==null)
			throw new NullPointerException();
		int pos=1;
		DoubleNode<T> aux = front;
		while(aux!=null) {
			if(aux.elem.equals(elem))
				return pos;
			aux=aux.next;
			pos++;
		}
		throw new NoSuchElementException();
	}


	@Override
	public int getPosLast(T elem) {
		if(elem==null)
			throw new NullPointerException();
		int pos=size();
		DoubleNode<T> aux = last;
		while(aux!=null) {
			if(aux.elem.equals(elem))
				return pos;
			aux=aux.prev;
			pos--;
		}
		throw new NoSuchElementException();
	}


	@Override
	public T removePos(int pos) {
		if(pos<1 || pos>size() )
			throw new IllegalArgumentException();

		DoubleNode<T> aux=front;
		int contador=1;

		while(contador<pos) {
			aux=aux.next;
			contador++;
		}

		aux.prev.next=aux.next;
		aux.next.prev=aux.prev;

		return aux.elem;
	}


	@Override
	public int removeAll(T elem) {
		if(elem==null)
			throw new NullPointerException();

		int instancias=0;
		DoubleNode<T> aux=front;

		do{
			if(front.elem.equals(elem)) {
				front = front.next;
				if(front!=null)
					front.prev=null;
				instancias++;
				aux=front;
			}else {
				if(aux.elem.equals(elem)) {
					if(aux.next!=null) 
						aux.next.prev=aux.prev; 
					if(aux.prev!=null)
						aux.prev.next = aux.next;
					instancias++;
				}
				aux=aux.next;	
			}
		}while(aux!=null);

		if(instancias==0)
			throw new NoSuchElementException();

		if(size()==0) last=null;

		return instancias;
	}


	@Override
	public boolean contains(T elem) {
		DoubleNode<T> aux=front;

		while(aux!=null) {
			if(aux.elem.equals(elem))
				return true;
			aux=aux.next;
		}
		return false;
	}


	@Override
	public int size() {
		if(isEmpty())
			return 0;

		DoubleNode<T> aux=front;
		int position=0;
		int tam=0;
		while(aux!=null) {
			tam++;
			aux=aux.next;
		}

		return tam;
	}


	@Override
	public String toStringReverse() {
		StringBuffer salida=new StringBuffer();
		salida.append("(");
		int position=0;
		DoubleNode<T> aux = last;

		while(aux!=null) {
			salida.append(aux.elem);
			salida.append(" ");
			aux=aux.prev;
			position++;
		}

		salida.append(")");

		return salida.toString();
	}

	@Override
	public DoubleList<T> reverse() {
		DoubleLinkedListImpl<T> aux=new DoubleLinkedListImpl<T>();
		DoubleNode<T> node=this.front;

		while(node!=null) {
			aux.insertFirst(node.elem);
			node=node.next;
		}
		return aux;
	}


	@Override
	public int maxRepeated() {
		int repetido=0;
		int contador = 0;
		DoubleNode<T> primero = front;
		while(primero!=null) {
			DoubleNode<T> aux = front;
			contador = 0;
			while(aux!=null) {
				if(aux.elem.equals(primero.elem)) {
					contador++;
				}
				aux=aux.next;
			}
			if(contador>repetido) {
				repetido = contador;
			}
			primero=primero.next;
		}
		return repetido;
	}


	@Override
	public boolean isEquals(DoubleList<T> other) {
		if(other==null)
			throw new NullPointerException();

		if(this.size()==0 && other.size()==0) 
			return true;

		if(this.size()!=other.size())
			return false;

		DoubleNode<T> nodeAux=this.front;

		int contador=1;

			while(contador<other.size()) {
				if(!nodeAux.elem.equals(other.getElemPos(contador)))
					return false;
				contador++;
				nodeAux=nodeAux.next;
			}

		return true;
	}


	@Override
	public boolean containsAll(DoubleList<T> other) {
		if(other==null)
			throw new NullPointerException();
		if(other.size()==0)
			return true;
		int contador=1;
		while(contador<other.size()) {
			if(!this.contains(other.getElemPos(contador)))
				return false;
			contador++;
		}
		return true;
	}


	@Override
	public boolean isSubList(DoubleList<T> other) {
		if(other==null)
			throw new NullPointerException();
		if(other.size()==0)
			return true;
		if(this==other)
			return true;
		if(other.size()>this.size())
			return false;

		DoubleNode<T> aux=this.front;
		DoubleNode<T> aux2=this.front;

		int contador=1;
		while(contador<this.size()) {
			if(other.getElemPos(contador).equals(aux.elem)) {
				if(contador==other.size())
					return true;
				aux=aux.next;
				contador++;
			}else {
				contador=1;
				aux2=aux2.next;
				aux=aux2;
				if(aux.next==null)
					return false;
			}
		}
		return false;
	}


	@Override
	public String toStringFromUntil(int from, int until) {
		StringBuffer salida=new StringBuffer();
		DoubleNode<T> aux=front;

		int inferior=1;
		salida.append("(");

		while(inferior<from) {
			aux=aux.next;
			inferior++;
		}

		while(inferior<=until) {
			salida.append(aux.elem);
			salida.append(" ");
			aux=aux.next;
			inferior++;
		}

		salida.append(")");

		return salida.toString();
	}

	@Override
	public String toString() {
		StringBuffer salida=new StringBuffer();
		DoubleNode<T> nodeToGet=front;
		salida.append("(");
		int position=0;

		while(nodeToGet!=null) {
			salida.append(nodeToGet.elem);
			salida.append(" ");
			nodeToGet=nodeToGet.next;
			position++;
		}

		salida.append(")");

		return salida.toString();
	}

	@Override
	public Iterator<T> iterator() {	
		return new DoubleLinkedListIterator<T>(front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DoubleLinkedListReverseIterator<T>(last);
	}


	@Override
	public Iterator<T> evenPositionsIterator() {
		return new DoubleLinkedListEvenPositionsIterator<T>(front);
	}


	@Override
	public Iterator<T> progressIterator() {
		return new DoubleLinkedListProgressIterator<T>(front);
	}


}
