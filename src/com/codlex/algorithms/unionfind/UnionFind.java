package com.codlex.algorithms.unionfind;

import java.util.Scanner;

public class UnionFind {

	private int[] ids;
	
	public UnionFind (int n) {
		
		this.ids = new int[n];
		
		for (int i = 0; i < n; i++) {
			this.ids[i] = i;
		}
	}
	
	public boolean isConnected (int p, int q) {
		return this.ids[p] == this.ids[q];
	}
	
	public void union (int firstNode, int secondNode) {
		
		int firstId = this.ids[firstNode];
		int secondId= this.ids[secondNode];
		
		for (int i = 0; i < this.ids.length; i++) {
			if (this.ids[i] == firstId) {
				this.ids[i] = secondId;
			}
		}
	}
	
	public static void main(String[] args) {
		UnionFind union = new UnionFind(10);
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
