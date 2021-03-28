import java.io.*;
public class BOJ_1725 {
	static int h[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		h = new int[n];
		
		for(int i=0; i<n; i++) 
			h[i] = Integer.parseInt(br.readLine());
		
		bw.write(solve(0,n-1)+"");
		
		bw.flush();
		bw.close();
	}
	
	static long solve(int left, int right) {
		if(left == right) return h[left];
		
		int mid = (left+right)/2;
		
		long result = (long)Math.max(solve(left, mid), solve(mid+1,right));
		
		int low = mid, high = mid+1;
		
		long height = (long)Math.min(h[low], h[high]);
		
		result = (long)Math.max(result, height*2);
		
		while(left < low || high < right) {
			if(high < right && (left == low || h[low-1] < h[high+1])) {
				++high;
				height = (long)Math.min(height, h[high]);
			} else {
				--low;
				height = (long)Math.min(height, h[low]);
			}
			
			result = (long)Math.max(result, height*(high-low+1));
		}
		
		return result;
	}

}
