package study;

import java.util.Scanner;

public class Dwarf {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int height[] = new int[9];
		int sum = 0;
		int non[] = new int[2];
		
		for(int i=0; i<9; i++) {
			height[i] = sc.nextInt();
			sum += height[i];
		}
		
		for(int i=0; i<9; i++) {
			
			for(int j=0; j<9; j++) {
				if(i == j)
					break;
				sum-=(height[i]+height[j]);
				if(sum == 100) {
					non[0] = height[i];
					non[1] = height[j];
					break;
				} else 
					sum+=(height[i]+height[j]);
			}
		}
		
		int tmp;
		
		for(int i=0; i<9; i++) {
			for(int j=1; j<9; j++) {
				if(height[j-1] > height[j]) {
					tmp = height[j-1];
					height[j-1] = height[j];
					height[j] = tmp;
				}
					
			}
		}
		
		for(int i=0; i<9; i++) {
			if(height[i] != non[0] && height[i]!= non[1])
				System.out.println(height[i]);
		}
			

	}

}
