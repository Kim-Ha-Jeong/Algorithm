import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2618 {
    static int n, m;
    static int[][] dp;

    static Node[] crime;
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        crime = new Node[m+1];
        dp = new int[m+1][m+1];

        for(int i=1; i<m+1; i++){
            st = new StringTokenizer(br.readLine());
            crime[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m+1; i++){
            Arrays.fill(dp[i], -1);
        }

        sb.append(dfs(0,0)).append("\n");
        getAns(0,0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void getAns(int p1, int p2){
        if(p1 == m || p2 == m) return;

        int next = Math.max(p1, p2)+1;
        int dist1, dist2;

        if(p1 == 0){
            dist1 = getDist(1,1,crime[next].x,crime[next].y);
        } else {
            dist1 = getDist(crime[p1].x,crime[p1].y,crime[next].x,crime[next].y);
        }

        if(p2 == 0){
            dist2 = getDist(n,n,crime[next].x, crime[next].y);
        } else {
            dist2 = getDist(crime[p2].x,crime[p2].y,crime[next].x,crime[next].y);
        }

        if(dp[next][p2] + dist1 < dp[p1][next] + dist2){
            sb.append("1\n");
            getAns(next,p2);
        } else {
            sb.append("2\n");
            getAns(p1,next);
        }
    }

    static int dfs(int p1, int p2){
        if(p1 == m || p2 == m) return 0;
        if(dp[p1][p2] != -1) return dp[p1][p2];

        int next = Math.max(p1, p2)+1;

        int dist1, dist2;

        if(p1 == 0){
            dist1 = getDist(1,1,crime[next].x,crime[next].y);
        } else {
            dist1 = getDist(crime[p1].x,crime[p1].y,crime[next].x,crime[next].y);
        }

        if(p2 == 0){
            dist2 = getDist(n,n,crime[next].x, crime[next].y);
        } else {
            dist2 = getDist(crime[p2].x,crime[p2].y,crime[next].x,crime[next].y);
        }

        int ret1 = dist1 + dfs(next,p2);
        int ret2 = dist2 + dfs(p1, next);

        return dp[p1][p2] = Math.min(ret1, ret2);
    }

    static int getDist(int x, int y, int nx, int ny){
        return Math.abs(x - nx) + Math.abs(ny - y);
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
