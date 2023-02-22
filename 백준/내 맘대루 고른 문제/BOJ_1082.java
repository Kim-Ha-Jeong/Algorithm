import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1082 {
    static int n;
    static int[] p;
    static int m;

    static String[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        p = new int[n];

        for(int i=0; i<n; i++){
            p[i] = Integer.parseInt(str[i]);
        }

        m = Integer.parseInt(br.readLine());
        dp = new String[51];

        String ans = "";
        for(int i=0; i<n; i++){
            dp[p[i]] = String.valueOf(i);
        }

        int zero = p[0];
        for(int i=1; i<m+1; i++){
            if(dp[i] != null) ans = get(dp[i], ans);
            for(int j=1; j<i/2+1; j++){
                int a = j;
                int b = i - j;

                if(dp[a] == null || dp[b] == null) continue;

                String maxA = dp[a].equals("0") ? "" : dp[a] + dp[b];
                if(b == zero && a != zero){
                    String tmp = dp[a]+"0";
                    maxA = get(maxA, tmp);
                }
                String maxB = dp[b].equals("0") ? "" : dp[b] + dp[a];
                if(a == zero && b != zero){
                    String tmp = dp[b]+"0";
                    maxB = get(maxB, tmp);
                }
                String max = get(maxA, maxB);
                if(dp[i] == null){
                    if(!max.equals("")) {
                        dp[i] = max;
                        ans = get(ans, dp[i]);
                    }
                } else {
                    dp[i] = get(max, dp[i]);
                    ans = get(ans, dp[i]);
                }
            }
        }

        if(ans.equals("")) ans = "0";
        bw.write(ans);
        bw.flush();
        bw.close();
    }

    static String get(String a, String b){
        int aLen = a.length();
        int bLen = b.length();

        if(aLen > bLen) return a;
        else if(aLen < bLen) return b;
        else {
            if(a.compareTo(b) > 0) return a;
            else return b;
        }
    }
}
