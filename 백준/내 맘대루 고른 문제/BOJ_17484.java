import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17484 {
    static int n,m;
    static int[][] dist;
    static int[][][] dp;
    static int max = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n][m];
        dp = new int[n][m][3];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        for(int i=0; i<m; i++){
            Arrays.fill(dp[0][i], dist[0][i]);
        }

        for(int i=1; i<n; i++){
            dp[i][0][0] = max;
            dp[i][m-1][2] = max;

            for(int j=0; j<m; j++) {
                if (j != 0) dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + dist[i][j];
                dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + dist[i][j];
                if (j != m - 1) dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + dist[i][j];
            }
        }

        int ans = max;

        for(int i=0; i<m; i++){
            for(int j=0; j<3; j++){
                ans = Math.min(ans, dp[n-1][i][j]);
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node>{
        int d;
        int cost;

        Node(int d, int cost){
            this.d = d;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }
}
