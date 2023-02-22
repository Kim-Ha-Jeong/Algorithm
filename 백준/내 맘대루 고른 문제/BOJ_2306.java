import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2306 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

       char[] ch = br.readLine().toCharArray();
        int len = ch.length;
        int[][] dp = new int[len][len];

        for(int size=1; size<len; size++){
            for(int start=0; start+size<len; start++){
                int end = start + size;

                if((ch[start] == 'a' && ch[end] == 't') || (ch[start] == 'g' && ch[end] == 'c')){
                    dp[start][end] = dp[start+1][end-1] + 2;
                }

                for(int mid=start; mid<end; mid++){
                    dp[start][end] = Math.max(dp[start][end], dp[start][mid] + dp[mid+1][end]);
                }
            }
        }

        bw.write(dp[0][len-1]+"");
        bw.flush();
        bw.close();
    }
}
