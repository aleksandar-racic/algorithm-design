package com.codlex.algorithms.unionfind;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	
	private int n;
	private int t;
	private double[] results;
	
	public PercolationStats(int n, int t){
		
		if (n <= 0 || t <= 0){
			throw new IllegalArgumentException();
		}
		
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
	
	private int[] randomizer(int n){
		int[] fields = new int [n * n];
		for (int i = 0; i < n * n; i++){
			fields[i] = i + 1;
		}
		StdRandom.shuffle(fields);
		
		return fields;
	}
	
	private double tester(int n){
		
		Percolation percolation = new Percolation(n);
		int[] fields = randomizer(n);
		int field = 0;
		
		while (!percolation.percolates()) {
			int toOpen = fields[field];
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
	
	public static void main(String[] args) {
		PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		
		StdOut.printf("mean\t= %f\n", percolationStats.mean());
		StdOut.printf("stddev\t= %f\n", percolationStats.stddev());
		StdOut.printf("95%% confidence interval\t= %f, %f\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
	}
	
//	public static void main(String[] args){
//		PercolationStats percolationstats = new PercolationStats(200, 100);
//		System.out.println(percolationstats.mean());
//		System.out.println(percolationstats.stddev());
//		System.out.println(percolationstats.confidenceLo());
//		System.out.println(percolationstats.confidenceHi());
//	} // test client (described below)
//	
}
