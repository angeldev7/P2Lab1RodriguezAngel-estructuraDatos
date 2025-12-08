package model;

import java.util.Stack;

public class ArbolBinario {
	protected Node root;
	
	public ArbolBinario() {
		this.root = null;
	}
	public ArbolBinario(Node root) {
		this.root = root;
	}
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public static Node newTree(Node left, Object data, Node right) {
		return new Node(left, data, right);
	}
}
