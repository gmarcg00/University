package ule.edi.recursiveList;

import java.util.NoSuchElementException;


public class UnorderedLinkedListImpl<T> extends AbstractLinkedListImpl<T> implements UnorderedListADT<T> {

	public UnorderedLinkedListImpl() {
		//	Vacía
	}
	
	public UnorderedLinkedListImpl(T ... v) {
		//	Añadir en el mismo orden que en 'v'
		for (T Vi : v) {
			addLast(Vi);
		}
	}
	
	@Override
	public void addFirst(T element) {
     Node<T> aux =new Node<T>(element);
     aux.next=front;
     front=aux;
	}
	
	
	@Override
	public void addLast(T element) {
		if(element==null)
			throw new NullPointerException();
		
		if(isEmpty()) {
			front=new Node<T>(element);
			return;
		}
		
		//Si llegamos aqui es que el front ya no es null (la lista no esta vacia)
		Node<T> aux=front;
		addLastRecursivo(aux,element);
	
	}	
	
	private void addLastRecursivo(Node<T> aux, T element) {
		if(aux.next==null) {
			Node<T> aux2=new Node <T>(element);
			aux.next=aux2;
		}else
			addLastRecursivo(aux.next, element);
	}

	
	@Override
	public void addBefore(T element, T target) {
		if(element==null || target==null)
			throw new NullPointerException();
		
		Node<T> aux=front;
		
		addBeforeRecursivo(aux,element,target);
				
	}
	
	private void addBeforeRecursivo(Node<T> aux,T element,T target) {
		if(aux.next==null && !(aux.elem==target))
			throw new NoSuchElementException();
		
		if(aux.next==null ||aux.elem==target) {
			Node<T> aux2=new Node<T>(element);
			aux2.next=aux;
			front=aux2;
			return;
		}
		
		if(aux.next.elem.equals(target)) {
			Node<T>aux3=aux.next;
			Node<T> aux2=new Node<T>(element);
			aux.next=aux2;
			aux2.next=aux3;
		}else {
			addBeforeRecursivo(aux.next,element,target);
		}
	}

		
}
