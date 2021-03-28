import java.io.*;

public class BOJ_2104 {
	static long a[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		a = new long[n];
		
		String s[] = br.readLine().split(" ");
		for(int i=0; i<n; i++) 
			a[i] = Long.parseLong(s[i]);
		
		bw.write(solve(0, n-1)+"");
		bw.flush();
		bw.close();
	}
	
	static long solve(int start, int end) {
		if(start == end) return (long)a[end]*a[end];
		
		int mid = (start+end)/2;
		
		long result = (long)Math.max(solve(start,mid), solve(mid+1,end));
		
		int low = mid, high = mid;
		long sum = a[mid];
		long min = a[mid];
		
		while(high - low < end - start) {
			
			long lValue = low > start ? a[low-1] : -1;
			long HValue = high < end ? a[high+1] : -1;
			
			if(lValue > HValue) {
				sum += lValue;
				min = Math.min(min, lValue);
				low--;
			} else {
				sum += HValue;
				min = Math.min(min, HValue);
				high++;
			}
			
			result = Math.max(result, sum*min);
		}
		
		return result;
	}

}
