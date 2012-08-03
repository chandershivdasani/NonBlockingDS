package org.chanders.nonblocking.stack;

public class Node<E> {
	E data;
	Node<E> next;
	
	public Node(E data) {
		this.data = data;
	}
}
