package com.codlex.algorithms.unionfind;

import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean table[][];
	private WeightedQuickUnionUF unionFindPercolate;
	private WeightedQuickUnionUF unionFindFull;
	private int n;
	private int source;
	
	public Percolation(int n) {
		this.n = n;
		this.table = new boolean[n][n];
		this.unionFindPercolate = new WeightedQuickUnionUF(n * n + 2);
		this.unionFindFull = new WeightedQuickUnionUF(n * n + 1);
		this.source = 0;
		for (int j = 1; j < n+1; j++) {
			this.unionFindFull.union(source, calculator(1, j));
			this.unionFindPercolate.union(source, calculator(1, j));
			this.unionFindPercolate.union(calculator(n, j), this.n * this.n + 1 );
		}
	} // create N-by-N grid, with all sites blocked
	   
	public void open(int i, int j){
		this.table[i-1][j-1] = true;
		
		if (i - 1 > 0 && isOpen(i-1,j)) {
			this.unionFindFull.union(calculator(i, j), calculator(i-1, j));
			this.unionFindPercolate.union(calculator(i, j), calculator(i-1, j));
		}
		
		if (j - 1 > 0 && isOpen(i, j - 1)) {
			this.unionFindFull.union(calculator(i, j), calculator(i, j - 1));
			this.unionFindPercolate.union(calculator(i, j), calculator(i, j - 1));
		}
		
		if (i + 1 <= n && isOpen(i + 1, j)) {
			this.unionFindFull.union(calculator(i, j), calculator(i + 1, j));
			this.unionFindPercolate.union(calculator(i, j), calculator(i + 1, j));
		}
		
		if (j + 1 <= n && isOpen(i, j + 1)) {
			this.unionFindFull.union(calculator(i, j), calculator(i, j + 1));
			this.unionFindPercolate.union(calculator(i, j), calculator(i, j + 1));
		}
		
	} // open site (row i, column j) if it is not open already
	
	public boolean isOpen(int i, int j){
		return this.table[i-1][j-1];
	} // is site (row i, column j) open?
	   
	private int calculator(int i, int j){
		return (i-1) * this.n + j;
	}
	
	public boolean isFull(int i, int j){
		return this.unionFindFull.connected(source, calculator(i, j)) && isOpen(i, j);
	} // is site (row i, column j) full?
	   
	public boolean percolates(){
		return this.unionFindPercolate.connected(source, this.n * this.n+1);
	} // does the system percolate?

	public static void main(String[] args){
		
		Percolation percolation = new Percolation(3);
		
		while (true) {
			String command = StdIn.readString();
			
			switch (command) {
			case "open":
				int openX = StdIn.readInt();
				int openY = StdIn.readInt();
				percolation.open(openX, openY);
				System.out.println(openX + " " + openY + " is now open");
				break;
			case "isFull":
				int fullX = StdIn.readInt();
				int fullY = StdIn.readInt();
				System.out.println("Is full: " + percolation.isFull(fullX, fullY));
				break;
			case "isOpen":
				int isOpenX = StdIn.readInt();
				int isOpenY = StdIn.readInt();
				System.out.println("Is already open: " + percolation.isOpen(isOpenX, isOpenY));
				break;
			case "percolates":
				System.out.println("Percolates: " + percolation.percolates());
			}
		}
	} // test client (optional)
}
