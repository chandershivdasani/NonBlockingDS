package org.chanders.nonblocking.stack;

public class StackTest {
	
	public static void main(String[] args) {
		NonBlockingStack<Node<String>> stack = new NonBlockingStack<Node<String>>();
		
		Node<String> first = new Node<String>("first");
		Node<String> second = new Node<String>("second");
		Node<String> third = new Node<String>("third");
		Node<String> fourth = new Node<String>("fourth");
		Node<String> fifth = new Node<String>("fifth");
		
		stack.push(first);
		stack.push(second);
		stack.push(third);
		stack.push(fourth);
		stack.push(fifth);
		
		for (Node<String> node : stack) {
			System.out.println(node.data);
		}
	}
}
