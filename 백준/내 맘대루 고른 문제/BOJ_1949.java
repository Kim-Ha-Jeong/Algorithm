import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1949 {
    static int n;
    static int[] people;
    static ArrayList<Integer>[] con;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        con = new ArrayList[n+1];
        people = new int[n+1];
        dp = new int[n+1][2];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++){
            con[i] = new ArrayList<>();
            people[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            con[a].add(b);
            con[b].add(a);
        }

        dfs(1,0);

        bw.write(Math.max(dp[1][0], dp[1][1])+"");
        bw.flush();
        bw.close();
    }

    static void dfs(int n, int parent){
        for(int node : con[n]){
            if(node != parent) {
                dfs(node, n);
                dp[n][0] += Math.max(dp[node][0], dp[node][1]);
                dp[n][1] += dp[node][0];
            }
        }

        dp[n][1] += people[n];
    }
}
