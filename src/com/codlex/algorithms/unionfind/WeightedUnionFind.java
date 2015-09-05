package com.codlex.algorithms.unionfind;

import java.util.Scanner;

public class WeightedUnionFind {

	private int parents[];
	private int sizes[];
	
	public WeightedUnionFind (int n) {
		this.parents = new int[n];
		this.sizes = new int[n];
		
		for (int i = 0; i < n; i++) {
			this.parents[i] = i;
			this.sizes[i] = 1;
		}
	}
	
	public boolean isConnected (int firstNode, int secondNode) {
		return root(firstNode) == root(secondNode);
	}
	
	public int root (int node) {
		int root = node;
		while (root != this.parents[root]) {
			root = this.parents[root];
		}
		return root;
	}
	
	public void union (int firstNode, int secondNode) {
		int firstRoot = root(firstNode);
		int secondRoot = root(secondNode);
		if (firstRoot != secondRoot) {
			if (this.sizes[firstRoot] > this.sizes[secondRoot]) {
				this.parents[secondRoot] = firstRoot;
				this.sizes[firstRoot] = this.sizes[firstRoot] + this.sizes[secondRoot];
			} else {
				this.parents[firstRoot] = secondRoot;
				this.sizes[secondRoot] = this.sizes[secondRoot] + this.sizes[firstRoot];
			}
		}
	}
	
	public static void main(String[] args) {
		WeightedUnionFind union = new WeightedUnionFind(10);
		Scanner in = new Scanner(System.in);
		
		while (true) {
			String command = in.next();
			int firstNode = in.nextInt();
			int secondNode = in.nextInt();
			
			switch (command) {
			case "union":
				union.union(firstNode, secondNode);
				System.out.println("Connection Established");
				break;
			case "connected":
				System.out.println("Connected:" + union.isConnected(firstNode, secondNode));
				break;
			}
		}
		
	}
}
