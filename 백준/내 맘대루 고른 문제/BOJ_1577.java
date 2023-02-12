import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1577 {
    static int n,m;
    static boolean[][][] dir;
    static long[][] dp;
    static int size;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new long[n+1][m+1];
        dir = new boolean[n+1][m+1][2];

        size = Integer.parseInt(br.readLine());

        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(a == c){
                dir[a][Math.max(b,d)][1] = true;
            } else {
                dir[Math.max(a,c)][b][0] = true;
            }
        }

        dp[0][0] = 1;
        for(int x=1; x<n+1; x++){
            if(dir[x][0][0]) continue;
            dp[x][0] = dp[x-1][0];
        }

        for(int y=1; y<m+1; y++){
            if(dir[0][y][1]) continue;
            dp[0][y] = dp[0][y-1];
        }

        for(int x=1; x<n+1; x++){
            for(int y=1; y<m+1; y++){
                dp[x][y] = dp[x][y-1] + dp[x-1][y];
                if(dir[x][y][0]) dp[x][y] -= dp[x-1][y];
                if(dir[x][y][1]) dp[x][y] -= dp[x][y-1];
            }
        }

        bw.write(dp[n][m]+"");
        bw.flush();
        bw.close();

    }
}
