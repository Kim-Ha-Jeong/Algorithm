import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10942 {
    static int n;
    static int[] arr;
    static boolean[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new boolean[n][n];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();
        int m = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(dp[a][b]){
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve(){
        for(int i=0; i<n; i++){
            dp[i][i] = true;
        }

        for(int i=0; i<n-1; i++){
            if(arr[i] == arr[i+1]) dp[i][i+1] = true;
        }

        for(int i=2; i<n; i++){
            for(int j=0; i+j<n; j++){
                if(arr[j] == arr[j+i] && dp[j+1][j+i-1]){
                    dp[j][j+i] = true;
                }
            }
        }

    }
}
