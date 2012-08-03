package org.chanders.nonblocking.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An implementation of non-blocking stack
 * @author chanders
 *
 */
public class NonBlockingStack<E> implements Iterable<E>{
	
	AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();
	private int size = 0;
	
	public void push(E item) {
		Node<E> newNode = new Node<E>(item);
		Node<E> oldHead;

		//Loop until you are successful in updating the 
		//head node.
		do{
			oldHead = head.get();
			newNode.next = oldHead;
		}while(!head.compareAndSet(oldHead, newNode));
		
		size++;
	}
	
	public E pop() {
		Node<E> oldHead = null;
		Node<E> newHead = null;

		do {
			if(oldHead == null) 
				return null;
			
			oldHead = head.get();
			newHead = oldHead.next;
			
		}while(head.compareAndSet(oldHead, newHead));
		size--;
		return oldHead.data;
	}
	
	public int size() {
		return this.size;
	}
	
	
	/**
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<E> {
		private Node<E> current = head.get();
		@Override
		public boolean hasNext() {
			return current !=null;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Node<E> item = current;
			current = current.next;
			return item.data;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Operation not supported");
			
		}
		
	}
}
