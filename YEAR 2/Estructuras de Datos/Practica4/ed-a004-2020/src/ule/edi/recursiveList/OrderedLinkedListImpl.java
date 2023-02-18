package ule.edi.recursiveList;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.recursiveList.AbstractLinkedListImpl.Node;

public class OrderedLinkedListImpl<T extends Comparable<? super T>> extends
		AbstractLinkedListImpl<T> implements OrderedListADT<T> {

	public OrderedLinkedListImpl() {
		// Vacía
	}

	public OrderedLinkedListImpl(T... v) {
		// Añade todos los elementos del array 'v'
		for (T Vi : v) {
			add(Vi);
		}
	}

	

	
	@Override
	public void add(T element) {
		if(element==null)
			throw new NullPointerException();
		
				
		Node<T> actual=front;
		int cont=0;
		addRecursivo(actual,element,cont);
	
	}
	
	private void addRecursivo(Node<T> actual, T element,int cont) {
		if(isEmpty()) {
			Node<T> aux=new Node<T>(element);
			front=aux;
			return;
		}
		
		if(actual.next==null && actual==front) {
			if(actual.elem.compareTo(element)>0) {
				Node<T> aux=front;
				front=new Node<T>(element);
				front.next=aux;
				return;
			}else {
				front.next=new Node<T>(element);
				return;
			}
		}
		
		
		if(actual.next!=null) {
			if(actual.elem.compareTo(element)>0) {
				if(actual==front) {
					Node<T> aux=front;
					front=new Node<T>(element);
					front.next=aux;
					return;
				}
				Node<T> aux2=actual;
				Node<T> aux3=getNode(cont);
				actual=new Node<T>(element);
				aux3.next=actual;
				actual.next=aux2;
				return;
			}else {
				cont++;
				addRecursivo(actual.next,element,cont);
			}
		}else {
			if(actual.elem.compareTo(element)>=0) {
				Node<T> aux2=actual;
				Node<T> aux3=getNode(cont);
				actual=new Node<T>(element);
				aux3.next=actual;
				actual.next=aux2;
				return;
			}else {
				actual.next=new Node<T>(element);
				return;
			}
		}
		
		
		
	}
	
	
	private Node<T> getNode(int pos){
		Node<T> aux=front;
		int posicion=1;
		
		return getNodeRecursivo(aux,pos,posicion);
	}
	
	private Node<T> getNodeRecursivo(Node<T> aux,int pos,int posicion){
		if(posicion==pos) {
			return aux;
		}
		posicion++;
		return getNodeRecursivo(aux.next,pos,posicion);
	}
	
	

	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		if(isEmpty())
			throw new EmptyCollectionException("La lista esta vacia");
		
		int times=0;
		Node<T> aux=front;
		Node<T> actual=front.next;

		
		return removeDuplicateOrdenado(aux,actual,times);
	
	}
	
	private int removeDuplicateOrdenado(Node<T> aux, Node<T> actual, int times) {
		
		if(aux.next!=null) {
			if(aux.elem.equals(actual.elem)) {
				aux.next=actual.next;
				times++;
				times=removeDuplicateOrdenado(aux,actual.next,times);
			}else {
				times=removeDuplicateOrdenado(aux.next,actual.next,times);
			}
		}
		return times;
		
	}
	
	

	
	



	

		

}
