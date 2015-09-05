package com.codlex.algorithms.unionfind;

import java.util.Scanner;

public class QuickFind {
	
	private int ids[];
	
	public QuickFind (int n) {
		
		this.ids = new int[n];
		
		for (int i = 0; i < n; i++) {
			this.ids[i] = i;
		}
	}
	
	public boolean isConnected (int firstNode, int secondNode) {
		return root(firstNode) == root(secondNode);
	}
	
	public int root (int node) {
		int root = node;
		while (root != this.ids[root]) {
			root = this.ids[root];
		}
		return root;
	}
	
	public void union (int firstNode, int secondNode) {
		int firstRoot = root(firstNode);
		int secondRoot = root(secondNode);
		if (firstRoot != secondRoot) {
			this.ids[firstRoot] = secondRoot;
			System.out.println("Connected!");
		} else {
			System.out.println("Already Connected!");
		}
	}
	
	public static void main(String[] args) {
		QuickFind union = new QuickFind(10);
		Scanner in = new Scanner(System.in);
		
		while (true) {
			String command = in.next();
			int firstNode = in.nextInt();
			int secondNode = in.nextInt();
			
			switch (command) {
			case "union":
				union.union(firstNode, secondNode);
				System.out.println("Hello World!");
				break;
			case "connected":
				System.out.println("Connected:" + union.isConnected(firstNode, secondNode));
				break;
			}
		}
		
	}
}
