package com.codlex.algorithms.unionfind;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	
	private int n;
	private int t;
	int[] fields;
	double[] results;
	
	public PercolationStats(int n, int t){
		this.results = new double [t];
		this.t = t;
		this.n = n;
		//System.out.println("Poco");		
		for (int i = 0; i < t; i++){
			//System.out.println("Radi");			
			this.results[i] = tester(n);
			//System.out.println("Zavrsio");
		}
		
//		for (int i = 0; i < t; i++)
//		{
//			System.out.println("Results: " + results[i]);
//		}
	} // perform T independent experiments on an N-by-N grid
	
	private void randomizer(int n){
		this.fields = new int [n * n];
		for (int i = 0; i < n * n; i++){
			this.fields[i] = i + 1;
		}
		StdRandom.shuffle(this.fields);	
	}
	
	private double tester(int n){
		
		Percolation percolation = new Percolation(n);
		randomizer(n);
		int field = 0;
		
		while (!percolation.percolates()) {
			int toOpen = this.fields[field];
			//System.out.println("toOpen: " + toOpen);
			percolation.open(reverseX(toOpen, n), reverseY(toOpen, n));
			field = field + 1;
		}
		double result = field;
		return result / (n * n);
	}
	
	
	private int reverseX(int field, int n){
		int x = field / n;
		if (field % n == 0) {
			return x;
		}
		return x + 1;
	}
	
	private int reverseY(int field, int n){
		int y = field - ((reverseX(field, n) - 1) * n);
		return y;
	}
	
	public double mean(){
		double sum = 0;
		for (int i = 0; i < t; i++){
			sum = sum + this.results[i];
		}
		return sum / t;
	} // sample mean of percolation threshold
	
	public double stddev(){
		double sum = 0;
		double mean = mean();
		for (int i = 0; i < t; i++){
			sum = sum + (this.results[i] - mean) * (this.results[i] - mean);
		}
		return Math.sqrt(sum / (t - 1));
	} // sample standard deviation of percolation threshold
	
	public double confidenceLo(){
		return mean() - ((1.96 * stddev()) / Math.sqrt(t));
	} // low  endpoint of 95% confidence interval
	
	public double confidenceHi(){
		return mean() + ((1.96 * stddev()) / Math.sqrt(t));
	} // high endpoint of 95% confidence interval
	

	public static void main(String[] args){
		PercolationStats percolationstats = new PercolationStats(200, 100);
		System.out.println(percolationstats.mean());
		System.out.println(percolationstats.stddev());
		System.out.println(percolationstats.confidenceLo());
		System.out.println(percolationstats.confidenceHi());
	} // test client (described below)
	
}
