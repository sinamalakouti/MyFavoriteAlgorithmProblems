package week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 
 * 
 * 
 * 
 * 
 * The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 3 lecture on heap applications). 
 * The text file contains a list of the integers from 1 to 10000 in unsorted order;
 * you should treat this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file,
 * the kth median mk is defined as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number
 * among x1,…,xk; if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)
 * In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). 
 * That is, you should compute (m1+m2+m3+⋯+m10000)mod10000.
 * 
 * 	The answer is 1213
 * 
 * 
 */

public class MedianMaintenance {
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
	PriorityQueue<Integer> minHeap  = new PriorityQueue<Integer>();
	

	public  int findMedian(int input ){
		
		if ( minHeap.size() == 0)
			minHeap.add(input);
		else	if (input < minHeap.peek() ){		
			maxHeap.add(input);
		}else {
			minHeap.add(input);
		}
		
		if ( maxHeap.size() - minHeap.size() > 1){
			
			minHeap.add(maxHeap.poll());
			
		}
		
		if ( minHeap.size() - maxHeap.size() > 1)
			maxHeap.add(minHeap.poll());
		if ( minHeap.size() == maxHeap.size())
			return maxHeap.peek();
		else if (minHeap.size() > maxHeap.size())
		{
			return minHeap.peek();
		}
		else return maxHeap.peek();
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		File file = new File("medianMaintencance.txt");
		MedianMaintenance m = new MedianMaintenance();
		try {
			Scanner scan = new Scanner(file);
			long result =0;
			int counter =0;
			while ( counter < 10000){
				int number = scan.nextInt();
				
				result +=  m.findMedian(number);
				counter ++;		
			}
			
			result = result %10000;
			System.out.println(result);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
