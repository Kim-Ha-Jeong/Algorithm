import java.util.*;
import java.io.*;
public class BOJ_11729 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		

		bw.write((int)(Math.pow(2, n)-1)+"\n");
		hanoi(n,1,2,3);
		
		bw.flush();
		bw.close();
		
	}
	
	static void hanoi(int n, int start, int mid, int end) throws Exception{
		if(n == 1) {
			bw.write(start+" "+end+"\n");
			return;
		}
		
		hanoi(n-1, start, end, mid);
		
		bw.write(start+" "+end+"\n");
		
		hanoi(n-1, mid, start, end);
	}

}
