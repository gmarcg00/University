package ule.edi.tree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Ã�rbol binario de bÃºsqueda (binary search tree, BST).
 * 
 * El cÃ³digo fuente estÃ¡ en UTF-8, y la constante EMPTY_TREE_MARK definida en
 * AbstractTreeADT del proyecto API deberÃ­a ser el sÃ­mbolo de conjunto vacÃ­o:
 * âˆ…
 * 
 * Si aparecen caracteres "raros", es porque el proyecto no estÃ¡ bien
 * configurado en Eclipse para usar esa codificaciÃ³n de caracteres.
 *
 * En el toString() que estÃ¡ ya implementado en AbstractTreeADT se usa el
 * formato:
 * 
 * Un Ã¡rbol vacÃ­o se representa como "âˆ…". Un Ã¡rbol no vacÃ­o como
 * "{(informaciÃ³n raÃ­z), sub-Ã¡rbol 1, sub-Ã¡rbol 2, ...}".
 * 
 * Por ejemplo, {A, {B, âˆ…, âˆ…}, âˆ…} es un Ã¡rbol binario con raÃ­z "A" y un
 * Ãºnico sub-Ã¡rbol, a su izquierda, con raÃ­z "B".
 * 
 * El mÃ©todo render() tambiÃ©n representa un Ã¡rbol, pero con otro formato; por
 * ejemplo, un Ã¡rbol {M, {E, âˆ…, âˆ…}, {S, âˆ…, âˆ…}} se muestra como:
 * 
 * M | E | | âˆ… | | âˆ… | S | | âˆ… | | âˆ…
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para adjuntar
 * informaciÃ³n extra. Si es el caso, tanto toString() como render() mostrarÃ¡n
 * los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor) y con
 * {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en los
 * elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> serÃ­a muy restrictivo; en su lugar se
 * permiten tipos que sean comparables no sÃ³lo con exactamente T sino tambiÃ©n
 * con tipos por encima de T en la herencia.
 * 
 * @param <T> tipo de la informaciÃ³n en cada nodo, comparable.
 */
public class BinarySearchTreeImpl<T extends Comparable<? super T>> extends AbstractBinaryTreeADT<T> {

	BinarySearchTreeImpl<T> father; // referencia a su nodo padre)

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda izquierdo.
	 */
	protected BinarySearchTreeImpl<T> getLeftBST() {
		// El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		// aquÃ­ se sabe que es ademÃ¡s de bÃºsqueda binario
		//
		return (BinarySearchTreeImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeImpl<T> left) {
		this.leftSubtree = left;
	}

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda derecho.
	 */
	protected BinarySearchTreeImpl<T> getRightBST() {
		return (BinarySearchTreeImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeImpl<T> right) {
		this.rightSubtree = right;
	}

	/**
	 * Ã�rbol BST vacÃ­o
	 */
	public BinarySearchTreeImpl() {
		// TODO HACER QUE THIS SEA EL NODO VACÃ�O
		this.content = null;
		setLeftBST(null);
		setRightBST(null);
		

	}

	public BinarySearchTreeImpl(BinarySearchTreeImpl<T> father) {
		// TODO HACER QUE THIS SEA EL NODO VACÃ�O, asignando como padre el parÃ¡metro
		// recibido

	}

	private BinarySearchTreeImpl<T> emptyBST(BinarySearchTreeImpl<T> father) {
		return new BinarySearchTreeImpl<T>(father);
	}

	/**
	 * Inserta los elementos de una colecciÃ³n en el Ã¡rbol. si alguno es 'null', NO
	 * INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements valores a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los
	 *         inserta)
	 */
	public int insert(Collection<T> elements) {
		// si alguno es 'null', ni siquiera se comienza a insertar (no inserta ninguno)
		// TODO Implementar el mÃ©todo
		for(T elem:elements) {
			if(elem==null) {
				return 0;
			}
		}

		int contador=0;
		for(T elem:elements) {
			insert(elem);
			contador++;
		}
		
		return contador;
		
	}

	/**
	 * Inserta los elementos de un array en el Ã¡rbol. si alguno es 'null', NO
	 * INSERTA NINGUNO
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 * @return numero de elementos insertados en el arbol (los que ya estÃ¡n no los
	 *         inserta)
	 */
	public int insert(T... elements) {
		for(int i=0;i<elements.length;i++) {
			if(elements[i]==null) {
				return 0;
			}
		}
		int contador=0;
		for(int i=0;i<elements.length;i++) {
			insert(elements[i]);
				contador++;
		}
		
		return contador;
	}

	/**
	 * Inserta (como hoja) un nuevo elemento en el Ã¡rbol de bÃºsqueda.
	 * 
	 * Debe asignarse valor a su atributo father (referencia a su nodo padre o null
	 * si es la raÃ­z)
	 * 
	 * No se permiten elementos null. Si element es null dispara excepciÃ³n:
	 * IllegalArgumentException Si el elemento ya existe en el Ã¡rbol NO lo inserta.
	 * 
	 * @param element valor a insertar.
	 * @return true si se pudo insertar (no existia ese elemento en el arbol, false
	 *         en caso contrario
	 * @throws IllegalArgumentException si element es null
	 */
	public boolean insert(T element) {
		if (element == null)
			throw new IllegalArgumentException();
		 if(contains(element))
			 return false;
		
		if (isEmpty()) {
			this.content = element;
			newNode();	
			return true;
		}

		boolean insertado = false;
		
		if (element.compareTo(this.content) < 0) {
			insertado=getLeftBST().insert(element);
		} else {
			insertado=getRightBST().insert(element);
		}
		return insertado;
	}

	private void newNode() {
		
		this.leftSubtree = new BinarySearchTreeImpl<T>(this);
		this.rightSubtree = new BinarySearchTreeImpl<T>(this);

	}

	

	/**
	 * Busca el elemento en el Ã¡rbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param element valor a buscar.
	 * @return true si el elemento estÃ¡ en el Ã¡rbol, false en caso contrario
	 */
	public boolean contains(T element) {
		if (element == null)
			throw new IllegalArgumentException();
		
		if(this.content==null) {
			return false;
		}
		
		if(this.content.equals(element)) {
			return true;
		}

		boolean find = false;
		
		if(element.compareTo(this.content)<0){
			find=getLeftBST().contains(element);
		}else {
			find=getRightBST().contains(element);
		}

		return find;
	}

	

	/**
	 * Elimina los valores en un array del Ã¡rbol. O todos o ninguno; si alguno es
	 * 'null'o no lo contiene el Ã¡rbol, no se eliminarÃ¡ ningÃºn elemento
	 * 
	 * @throws NoSuchElementException si alguno de los elementos a eliminar no estÃ¡
	 *                                en el Ã¡rbol
	 */
	public void remove(T... elements) {
		for(int i=0;i<elements.length;i++) {
			if(!contains(elements[i]))
				throw new NoSuchElementException();
		}
		
		for(int i=0;i<elements.length;i++) {
			remove(elements[i]);
		}
	}

	/**
	 * Elimina un elemento del Ã¡rbol.
	 * 
	 * Si el elemento tiene dos hijos, se tomarÃ¡ el criterio de sustituir el
	 * elemento por el menor de sus mayores y eliminar el menor de los mayores.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no estÃ¡ en el
	 *                                Ã¡rbol
	 */
	public void remove(T element) {
		// TODO Implementar el mÃ©todo
		if(!contains(element))
			throw new NoSuchElementException();
		
		if(this.content.equals(element)) {
			if(this.leftSubtree.content==null && this.rightSubtree.content==null) {
				//HOJA
				this.content=null;
				this.leftSubtree=null;
				this.rightSubtree=null;
				return;
				
			}else if(this.leftSubtree.content!=null && this.rightSubtree.content!=null) {
				//2 HIJOS
				BinarySearchTreeImpl<T> min=getMin(getRightBST());
				this.content=min.content;
				min.content=null;
				min.leftSubtree=null;
				min.rightSubtree=null;
				return;
	
			}else {
				if(this.leftSubtree.content!=null) {
					//HIJO IZQUIERDO
					this.content=leftSubtree.content;
					if(leftSubtree.leftSubtree!=null) {
						this.leftSubtree=leftSubtree.leftSubtree;
					}
					if(leftSubtree.rightSubtree!=null) {
						this.rightSubtree=leftSubtree.rightSubtree;
					}
					return;
				}else {
					//HIJO DERECHO
					this.content=rightSubtree.content;
					if(rightSubtree.rightSubtree!=null) {
						this.rightSubtree=rightSubtree.rightSubtree;
					}
					if(rightSubtree.leftSubtree!=null) {
						this.leftSubtree=rightSubtree.leftSubtree;
					}
					return;
				}

			}
		}else {
			if(element.compareTo(this.content)<0) {
				getLeftBST().remove(element);
			}else {
				getRightBST().remove(element);
			}
		}

	}
	
	private BinarySearchTreeImpl<T> getMin(BinarySearchTreeImpl<T> tree ){
		if(tree.leftSubtree.content!=null) {
			return getMin(tree.getLeftBST());
		}else {
			return tree;
		}
	}

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con la etiqueta "height" y el valor correspondiente a la
	 * altura del nodo.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
	 * 10 | 5 | | 2 | | | âˆ… | | | âˆ… | | âˆ… | 20 | | 15 | | | âˆ… | | | âˆ… | |
	 * 30 | | | âˆ… | | | âˆ…
	 * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], âˆ…, âˆ…}, âˆ…}, {20
	 * [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public void tagHeight() {
		int contador=1;
		tagHeightRec(contador);
	}
	
	private void tagHeightRec(int cont) {
		if(this.content!=null) {
			this.setTag("height", cont);
			if(this.leftSubtree.content==null && this.rightSubtree.content==null) {
				return;
			}else if(this.leftSubtree.content!=null && this.rightSubtree.content!=null) {
				cont++;
				getLeftBST().tagHeightRec(cont);
				getRightBST().tagHeightRec(cont);
			}else {
				if(this.leftSubtree.content!=null) {
					cont++;
					getLeftBST().tagHeightRec(cont);
				}
				if(this.rightSubtree.content!=null) {
					cont++;
					getRightBST().tagHeightRec(cont);
				}
			}
		}
	}

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Etiqueta cada nodo con el valor correspondiente al nÃºmero de descendientes
	 * que tiene en este Ã¡rbol.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
	 * 10 | 5 | | 2 | | | âˆ… | | | âˆ… | | âˆ… | 20 | | 15 | | | âˆ… | | | âˆ… | |
	 * 30 | | | âˆ… | | | âˆ…
	 * 
	 * 
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], âˆ…, âˆ…},
	 * âˆ…}, {20 [(decendents, 2)], {15 [(decendents, 0)], âˆ…, âˆ…}, {30
	 * [(decendents, 0)], âˆ…, âˆ…}}}
	 * 
	 * 
	 */
	public void tagDecendents() {
		descendentRec();
	}
	
	private int descendentRec() {
		if(this.content!=null) {
            if(this.leftSubtree.content==null && this.rightSubtree.content==null) {
                this.setTag("decendents", 0);
                return 0;
            } else {
            	int self=0,left=0,right=0;            
                if(this.leftSubtree.content!=null) {
                	self++;
                    left=getLeftBST().descendentRec();
                }
                if(this.rightSubtree.content!=null) {
                	self++;
                    right=getRightBST().descendentRec();
                    
                }
                this.setTag("decendents",self+right+left);
                return right+left+self;
            }
        }
		return 0;
	}

	/**
	 * Devuelve un iterador que recorre los elementos del arbol por niveles segÃºn
	 * el recorrido en anchura
	 * 
	 * Por ejemplo, con el Ã¡rbol
	 * 
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * y devolverÃ­a el iterador que recorrerÃ­a los nodos en el orden: 50, 30, 80,
	 * 10, 40, 60
	 * 
	 * 
	 * 
	 * @return iterador para el recorrido en anchura
	 */

	public Iterator<T> iteratorWidth() {
		// TODO Implementar mÃ©todo
		// puede implementarse creando una lista con el recorrido en anchura de los
		// elementos del Ã¡rbol y devolver el iterador de dicha lista
		return null;
	}

	/**
	 * Importante: Solamente se puede recorrer el Ã¡rbol una vez
	 * 
	 * Calcula y devuelve el nÃºmero de nodos que son hijos Ãºnicos y etiqueta cada
	 * nodo que sea hijo Ãºnico (no tenga hermano hijo del mismo padre) con la
	 * etiqueta "onlySon" y el valor correspondiente a su posiciÃ³n segÃºn el
	 * recorrido inorden en este Ã¡rbol.
	 * 
	 * La raÃ­z no se considera hijo Ãºnico.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A", que tiene 3 hijos Ãºnicos, los va etiquetando
	 * segÃºn su recorrido en inorden.
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, {15, âˆ…, âˆ…}, {30, âˆ…, âˆ…}}}
	 * 
	 *
	 * el Ã¡rbol quedarÃ­a etiquetado:
	 * 
	 * {10, {5, {2 [(onlySon, 1)], âˆ…, âˆ…}, âˆ…}, {20, {15 [(onlySon, 3)], {12
	 * [(onlySon, 2)], âˆ…, âˆ…}, âˆ…}, âˆ…}}
	 * 
	 */
	public int tagOnlySonInorder() {
		// TODO Implementar el mÃ©todo
		return 0;
	}

}
