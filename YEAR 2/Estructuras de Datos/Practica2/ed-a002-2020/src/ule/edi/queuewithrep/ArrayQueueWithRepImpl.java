package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {
	
	// atributos
	
    private final int capacityDefault = 10;
	
	ElemQueueWithRep<T>[] data;
    private int count;
    
	// Clase interna 
    
	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}

	
	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {
		private int count;
		private int current;
		private int intermedio;
		private ElemQueueWithRep<T>[] cola;
		
		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			this.current=0;
			this.count=count;
			this.cola=cola;
			this.intermedio=0;
		}

		@Override
		public boolean hasNext() {
			return current<count;
		}

		@Override
		public T next() {
			if(!hasNext()) //Si ya hemos llegado al final
				throw new NoSuchElementException();
			
			T element = cola[current].elem;
			intermedio++;
			if(intermedio==cola[current].num) {
				intermedio=0;
				current++;
			}
			return element;
		}
		
	}
	////// FIN ITERATOR
	
	
    // Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data =   new ElemQueueWithRep[capacityDefault];
		count=0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data =  new ElemQueueWithRep[capacity];
		count=0;
	}
	
	
	 @SuppressWarnings("unchecked")
	 private void expandCapacity() {
		
			ElemQueueWithRep<T>[] nuevo= (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length*2];
			for(int i=0;i<data.length;i++) {
				nuevo[i]=data[i];
			}
			data=(ElemQueueWithRep<T>[]) new ElemQueueWithRep[nuevo.length];
			for(int i=0;i<data.length;i++) {
				data[i]=nuevo[i];
			}
	}
	 
	
			@Override
			public void add(T element, int times) {
				if(element==null)
					throw new NullPointerException();
				if(times<0)
					throw new IllegalArgumentException();
				if(contains(element)) {
					for(int i=0;i<count;i++) {
						if(data[i].elem.equals(element)) data[i].num+=times;
					}
				}else {
					if(count==data.length) {
						expandCapacity();
					}
					data[count]=new ElemQueueWithRep(element,times);
					count++;
					
				}		
				
			}
			


			@Override
			public void add(T element) {
				if(element==null)
					throw new NullPointerException();
				add(element,1);
			}
				
			@Override
			public void remove(T element, int times) {
				if(element==null)
					throw new NullPointerException();
				if(times!=0) {
					if(!contains(element))
						throw new NoSuchElementException();
					for(int i=0;i<count;i++) {
						if(data[i].elem.equals(element)) {
							if(times<0 || times>=data[i].num)
								throw new IllegalArgumentException();
							else
								data[i].num-=times;
						}
					}
				}
	
			}

			@Override
			public int remove() throws EmptyCollectionException {
				if(count==0)
					throw new EmptyCollectionException("Cola vacia");
				int num=data[0].num;
				
				for(int i=0;i<count-1;i++) {
					data[i]=data[i+1];
				}
				
				data[count-1]=null;
				count--;
				return num;
				
			}

			@Override
			public void clear() {
				for(int i=0;i<data.length;i++) {
					data[i]=null;
				}
				count=0;
			}
			

			@Override
			public boolean contains(T element) {
				if(element==null)
					throw new NullPointerException();
				for(int i=0;i<count;i++) {
					if(data[i].elem.equals(element)) {
						return true;
					}
				}
				return false;
			}

			@Override
			public boolean isEmpty() {
				if(count==0)
					return true;
				return false;
				
			}

			@Override
			public long size() {
				int size=0;
				for(int i=0;i<count;i++) {
					size+=data[i].num;
				}
				return size;
			}

			@Override
			public int count(T element) {
				if(element==null)
					throw new NullPointerException();
				for(int i=0;i<count;i++) {
					if(data[i].elem.equals(element)) {
						return data[i].num;
					}
				}
				return 0;
			}

			@Override
			public Iterator<T> iterator() {
				return new ArrayQueueWithRepIterator(data,count);
				
			}
			
			@Override
			public String toString() {
				
				final StringBuffer buffer = new StringBuffer();
				
				
				buffer.append("(");
				
				for(int i=0;i<count;i++) {
					for(int j=0;j<data[i].num;j++) {
						buffer.append(data[i].elem+ " ");
					}
				}

				// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
				
				buffer.append(")");
				
				return buffer.toString();
			}

	
}
