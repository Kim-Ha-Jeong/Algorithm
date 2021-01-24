import java.io.*;
public class BOJ_5904 {
	static int m;
	static char result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		m = Integer.parseInt(br.readLine());
		solve(m);
		
		bw.write(result+"");
		bw.flush();
		bw.close();
	}
	
	static void solve(int n) {
		int size = 3;
		int index = 0;
		
		if(n == 1)
			result ='m';
		else if(n<=3)
			result ='o';
		else {
			while(size < n) {
				size = 2*size +index+4;
				index++;
			}
		}
		
		int front_back = (size-index-3)/2;
		
		if(size-front_back+1<=n)
			solve(n-size+front_back);
		else if(n == front_back+1)
			result='m';
		else
			result='o';
	}

}
