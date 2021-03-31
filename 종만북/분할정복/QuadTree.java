import java.io.*;

public class QuadTree {
	static int arr[][], m;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String s[] = br.readLine().split("");
			for(int j=0; j<n; j++)
				arr[i][j] = Integer.parseInt(s[j]);
		}
		
		quadTree(0,0,n);
		
		bw.flush();
		bw.close();
		
	}
	
	static void quadTree(int y, int x,int size) throws Exception {
		if(check(y,x,size))
			bw.write(m+"");
		else {
			bw.write("(");
			size /= 2;
			
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++)
					quadTree(y+size*i, x+size*j, size);
			}
			
			bw.write(")");
		}
	}
	
	static boolean check(int y, int x, int size) {
		for(int i=y; i<y+size; i++) {
			for(int j=x; j<x+size; j++) {
				if(arr[y][x] != arr[i][j])
					return false;
			}
		}
		
		m = arr[y][x];
		return true;
	}

}
