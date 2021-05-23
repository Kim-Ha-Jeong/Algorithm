import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Graph_2 {
	static int[][] arr;
	static int INF = 10000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i == j) 
					continue;
				arr[i][j] = INF;
			}
		}
		
		for(int i=0; i<m; i++) {
			String[] s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int cost = Integer.parseInt(s[2]);
			arr[start][end] = Math.min(cost,arr[start][end]);
		}
		
		for(int mid=1; mid<n+1; mid++) {
			for(int start=1; start<n+1; start++) {
				for(int end=1; end<n+1; end++) {
					arr[start][end] = Math.min(arr[start][mid]+arr[mid][end], arr[start][end]);
				}
			}
		}
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(arr[i][j] >= INF)
					sb.append("0 ");
				else
					sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
