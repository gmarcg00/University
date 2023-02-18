package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.queuewithrep.LinkedQueueWithRepImpl.QueueWithRepNode;


public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {

	// Atributos
	private QueueWithRepNode<T> front;
	int count;


	// Clase interna
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next;

		public QueueWithRepNode (T elem, int num){
			this.elem=elem;
			this.num=num;
		}

	}

	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedQueueWithRepIterator<T> implements Iterator<T> {
		private QueueWithRepNode<T> node;
		int instancias = 0;


		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
			node = nodo;
		}

		@Override
		public boolean hasNext() {
			return node!=null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				QueueWithRepNode<T> node1=node;
				instancias++;
				if(instancias==node.num) {
					node=node.next;
					instancias = 0;
				}
				return node1.elem;
			}
			throw new NoSuchElementException();
		}	

	}
	////// FIN ITERATOR

	public LinkedQueueWithRepImpl() {
		front=null;
		count=0;
	}

	/////////////
	@Override
	public void add(T element) {
		add(element,1);

	}

	@Override
	public void add(T element, int times) {
		if(element==null) 
			throw new NullPointerException();
		if(times<0)
			throw new IllegalArgumentException();
		if(isEmpty()) {
			front=new QueueWithRepNode<T>(element, times);
		}else {
			if(contains(element)) {
				int position = 0;
				QueueWithRepNode<T> nodeToGet = front;
				while (position < count) {
					if(nodeToGet.elem.equals(element)) {
						nodeToGet.num+=times;
						return;
					}
					nodeToGet = nodeToGet.next;
					position++;
				}
			}else {
				int position = 0;
				QueueWithRepNode<T> nodeToGet = front;
				while (position < count-1) {
					nodeToGet = nodeToGet.next;
					position++;
				}
				nodeToGet.next=new QueueWithRepNode<T>(element,times);
			}

		}
		count++;		
	}


	@Override
	public void remove(T element, int times) {
		if(element==null) 
			throw new NullPointerException();
		if(times<0)
			throw new IllegalArgumentException();
		int position = 0;
		if(!isEmpty()) {
			if(times!=0) {
				if(contains(element)) {
					QueueWithRepNode<T> nodeToGet = front;
					while (position < count) {
						if(nodeToGet.elem.equals(element))
							if(nodeToGet.num>times)
								nodeToGet.num = nodeToGet.num-times;
							else
								throw new IllegalArgumentException();
						nodeToGet = nodeToGet.next;
						position++;
					}

				}else
					throw new NoSuchElementException();
			}
		}
	}


	@Override
	public boolean contains(T element) {
		if(element==null) 
			throw new NullPointerException();
		int position = 0;
		QueueWithRepNode<T> nodeToGet = front;
		while (position < count) {
			if(nodeToGet.elem.equals(element))
				return true;
			nodeToGet = nodeToGet.next;
			position++;
		}
		return false;


	}

	@Override
	public long size() {
		int position = 0;
		int instancias = 0;

		QueueWithRepNode<T> nodeToGet = front;
		while (position < count) {
			instancias = instancias + nodeToGet.num;
			nodeToGet = nodeToGet.next;
			position++;
		}
		return instancias;

	}

	@Override
	public boolean isEmpty() {
		return count==0;

	}

	@Override
	public int remove() throws EmptyCollectionException {
		if(count==0) {
			throw new EmptyCollectionException("Cola vacia");
		}
		int instancias = front.num;
		front = front.next;
		count--;
		return instancias;
	}

	@Override
	public void clear() {
		count=0;
		front = null;
	}

	@Override
	public int count(T element) {
		if(element==null) 
			throw new NullPointerException();
		int position = 0;
		QueueWithRepNode<T> nodeToGet = front;
		while (position < count) {
			if(nodeToGet.elem.equals(element))
				return nodeToGet.num;
			nodeToGet = nodeToGet.next;
			position++;
		}
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueWithRepIterator<T>(front);
	}


	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
		QueueWithRepNode<T> nodeToGet = front;
		int position = 0;
		while(position<count) {
			int numero = 0;
			while(numero<nodeToGet.num) {
				buffer.append(nodeToGet.elem + " ");
				numero++;
			}
			nodeToGet = nodeToGet.next;
			position++;
		}

		buffer.append(")");
		return buffer.toString();
	}




}
