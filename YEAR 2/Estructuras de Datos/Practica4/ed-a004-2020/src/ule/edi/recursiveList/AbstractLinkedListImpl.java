package ule.edi.recursiveList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Comparable;


import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.recursiveList.AbstractLinkedListImpl.Node;

public class AbstractLinkedListImpl<T> implements ListADT<T> {

	// Estructura de datos, lista simplemente enlazada
	//
	// Este es el primer nodo de la lista
	protected Node<T> front = null;

	// Clase para cada nodo en la lista
	protected  class Node<T> {

		T elem;

		Node<T> next;

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		@Override
		public String toString() {
			return "(" + elem + ")";
		}

	}
	
	private class IteratorImpl implements Iterator<T> {
		Node<T> node;
		
		public IteratorImpl(Node<T> nodo) {
			node=nodo;
		}
		
		@Override
		public boolean hasNext() {

			return node!=null;
		}

		@Override
		public T next() {
			if(hasNext()) {
				Node<T> aux=node;
				node=node.next;
				return aux.elem;	
			}else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


	// Ejemplos de ejercicios de recursividad
	//



	@Override
	public String toString() {
		// Construye y devuelve con el formato adecuado "(A B C )" 
		StringBuilder buffer =new StringBuilder();
		Node<T> aux=front;
		buffer.append("(");
		return toStringRecursivo(aux,buffer);
	}
	
	private String toStringRecursivo(Node<T> aux, StringBuilder buffer) {
		if(isEmpty()) {
			buffer.append(")");
			return buffer.toString();
		}
		if(aux.next!=null) {
			buffer.append(aux.elem);
			buffer.append(" ");
			toStringRecursivo(aux.next,buffer);
		}else {
			buffer.append(aux.elem);
			buffer.append(" ");
			buffer.append(")");	
		}
		
		return buffer.toString();
	}
	
	
	@Override
	public boolean contains(T target) {
		if(target==null)
			throw new NullPointerException();
		
		Node <T> aux=front;
		return containsRecursivo(aux,target);
	}
	
	private boolean containsRecursivo(Node <T> aux, T target) {
		boolean output;
		
		if(aux==null)
			return false;
		
		if(aux.elem.equals(target))
			output=true;
		else
			output=containsRecursivo(aux.next,target);
		
		return output;
	}

	
  @Override
	public int count(T element) {
	  Node<T> aux=front;
	  int cont=0;
	  
	  return countRecursivo(aux,element,cont);
		
	}
  
  	private int countRecursivo(Node<T> aux, T element,int contador) {
  		if(aux==null)
  			return contador;
  		if(aux.elem.equals(element)) 
  			contador++;
  		
  		return countRecursivo(aux.next,element,contador);		
  	}


	@Override
	public T getLast() throws EmptyCollectionException {
		if(isEmpty())
			throw new EmptyCollectionException("Esta vacia");
		
		Node<T> aux=front;
		return getLastRecursivo(aux);
	}
	
	private T getLastRecursivo(Node<T> aux) {
		if(aux.next==null)
			return aux.elem;
		
		return getLastRecursivo(aux.next);
	}


	
	@Override
	public boolean isOrdered() {
		Node<T> aux=front;
		
		if(isEmpty()) {
			return true;
		}
		
		return isOrderedRecursivo(aux);
	}
	
	private boolean isOrderedRecursivo(Node<T> aux) {
		boolean ordered;
		StringBuilder actual=new StringBuilder();
		StringBuilder siguiente=new StringBuilder();
		
		if(aux.next==null) {
			return true;
		}
		
		actual.append(aux.elem);
		siguiente.append(aux.next.elem);
		
		
		
		if(actual.toString().compareTo(siguiente.toString())>0) {
			ordered=false;
		}else {
			ordered=isOrderedRecursivo(aux.next);
		}
		
		return ordered;
		
		
	}

	
	@Override
	public T remove(T element) throws EmptyCollectionException {
		if(element==null)
			throw new NullPointerException();
		if(isEmpty())
			throw new EmptyCollectionException("Lista vacia");
		
		Node<T> aux=front;
		return removeRecursivo(aux,element);
		
	}
	
	private T removeRecursivo(Node<T> aux, T element) {
		if(aux.elem.equals(element)) {
			front=aux.next;
			return aux.elem;
		}
		
		if(aux.next==null && !aux.elem.equals(element))
			throw new NoSuchElementException();
		
		if(aux.next.elem.equals(element)) {
			if(aux.next.next==null) {
				Node<T> aux2=aux.next;
				aux.next=null;
			return aux2.elem;
			}else {
				aux.next=aux.next.next;
				return aux.next.elem;
			}
				
		}
		
		return removeRecursivo(aux.next,element);
	}


	@Override
	public T removeLast(T element) throws EmptyCollectionException {
		if(element==null)
			throw new NullPointerException();
		if(isEmpty())
			throw new EmptyCollectionException("Lista vacia");
		
		Node<T> anterior=front;
		Node<T> actual=front.next;
		
		return removeLastRecursivo(anterior,actual,element);
	}
	
	private T removeLastRecursivo(Node<T> anterior,Node<T> actual, T element) {
		if(actual.next==null) {
			if(actual.elem.equals(element)) {
				anterior.next=null;
				return actual.elem;
			}else {
				return null;
			}
		}
		
		T elem=removeLastRecursivo(anterior.next,actual.next,element);
		
		if(elem==null) {
			if(actual.elem.equals(element)) {
				anterior.next=actual.next;
				elem=actual.elem;
				return elem;
			}else if(anterior.elem.equals(element)) {
				if(anterior==front) {
					elem=anterior.elem;
					front=anterior.next;
					return elem;
				}
			}else if(!anterior.elem.equals(element)){
				if(anterior==front)
					throw new NoSuchElementException();
			}
		}
		
	
		return elem;
		
	}

	@Override
	public boolean isEmpty() {	
		return front==null;
	}

	@Override
	public int size() {
		Node<T> aux=front;
		int tam=sizeRecursivo(aux);
		
		return tam;
	}
	
	private int sizeRecursivo(Node<T> aux) {
		int tam;
		if(aux!=null)
			tam=1+sizeRecursivo(aux.next);
		else
			tam=0;
		
		return tam;
	}

	@Override
	public T getFirst() throws EmptyCollectionException {
		if(isEmpty())
			throw new EmptyCollectionException("Lista vacia");
		return front.elem;
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		if(from <=0 || until<=0 || until<from)
			throw new IllegalArgumentException();
		
		StringBuilder output=new StringBuilder();
		output.append("(");
		
		Node<T> aux=front;
		int contador=1;
		
		return toStringFromUntilRecursivo(aux,output,from,until,contador);	
	}
		
	
	private String toStringFromUntilRecursivo(Node<T> aux, StringBuilder output, int from, int until,int contador) {
		if(isEmpty() || from>size()) {
			output.append(")");
			return output.toString();
		}
		
		if(until>size()) {
			if(aux!=null) {
				output.append(aux.elem);
				output.append(" ");
				toStringFromUntilRecursivo(aux.next,output,from,until,contador);
			}else {
				output.append(")");
			}
			
			return output.toString();
		}
		
		
		if(contador>=from) {
				output.append(aux.elem);
				output.append(" ");
				contador++;
				if(aux.next==null) {
					output.append(")");
					return output.toString();
				}
				if(contador>until) {
					output.append(")");
					return output.toString();
				}
				toStringFromUntilRecursivo(aux.next,output,from,until,contador);
			
		}else {
			contador++;
			toStringFromUntilRecursivo(aux.next,output,from,until,contador);
		}
		
		
		return output.toString();
	}


	@Override
	public String toStringReverse() {
		StringBuilder output=new StringBuilder();
		output.append("(");
		
		Node<T> aux=front;
		String salida;
		salida=toStringReverse(aux,output);
		salida=salida+(")");
		
		return salida;
	}
	
	private String toStringReverse(Node<T> aux,StringBuilder output) {
		if(aux.next==null) {
			output.append(aux.elem);
			output.append(" ");
			
			return output.toString();
		}
		
		toStringReverse(aux.next,output);
		
		output.append(aux.elem);
		output.append(" ");
		
		return output.toString();
	}
	
	
	@Override
	public int removeDuplicates() throws EmptyCollectionException {
		if(isEmpty())
			throw new EmptyCollectionException("La lista esta vacia");
		
		int times=0;
		Node<T> aux=front;
		Node<T> actual=front.next;
		T element=aux.elem;
		
		return removeDuplicatesRecursivo(aux,actual,element,times);
	
	}
	
	private int removeDuplicatesRecursivo(Node<T> aux,Node<T> actual,T element,int times) {
		int repeticiones=0;
		if(aux!=null && actual!=null) {
			times=times+repeticiones+removeDuplicatesRecorre(aux,element,actual,repeticiones);
			if(aux.next!=null) {
				if(actual!=null && actual.next!=null) {
					element=aux.next.elem;
					times=removeDuplicatesRecursivo(aux.next,actual.next,element,times);
				}
			}
		}
		return times;
	}
	
	private int removeDuplicatesRecorre(Node<T> aux, T element,Node<T> actual,int eliminados) {
		boolean elimina=false;
		
		if(aux==actual)
			actual=actual.next;
		
		if(actual!=null) {
			if(actual.elem.equals(element)) {
				aux.next=actual.next;
				eliminados++;
				elimina=true;
			}
		}else {
			return eliminados;
		}
		
		if(elimina) {
			if(actual.next!=null) {
				eliminados=removeDuplicatesRecorre(aux,element,actual.next,eliminados);
				return eliminados;
			}else {
				return eliminados;
			}
			
	
		}else {
			eliminados=removeDuplicatesRecorre(aux.next,element,actual.next,eliminados);
			return eliminados;
		
		}
			
	}
	
	
	
	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl(front);
		
	}


}
