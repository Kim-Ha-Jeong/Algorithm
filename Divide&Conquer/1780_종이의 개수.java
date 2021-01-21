import java.io.*;

public class BOJ_1780 {
	static int arr[][], result;
	static int minus=0,zero=0,one=0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String s[] = br.readLine().split(" ");
			for(int j=0; j<n; j++)
				arr[i][j] = Integer.parseInt(s[j]);
		}
		
		divide(0,0,n);
		
		bw.write(minus+"\n"+zero+"\n"+one);
		bw.flush();
		bw.close();
	}
	
	static void divide(int y, int x, int size) {
		if(check(y,x,size)) {
			if(result == 1)	one++;
			else if(result == 0) zero++;
			else minus++;
		} else {
			size /= 3;
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++)
					divide(y+size*i, x+size*j, size);
			}
		}
	}
	
	static boolean check(int y, int x, int size) {
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				if(arr[y][x] != arr[i][j])
					return false;
			}
		}
		
		result = arr[y][x];
		return true;
	}

}
