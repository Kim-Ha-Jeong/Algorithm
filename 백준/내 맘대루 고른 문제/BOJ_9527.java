import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_9527 {
    static long start, end;
    static long[] dp = new long[55];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());

        dp[0] = 1;

        for(int i=1; i<55; i++){
            dp[i] = 2*dp[i-1] + (1L << i);
        }

        long ans = getCount(end) - getCount(start-1);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static long getCount(long x){
        String str = Long.toBinaryString(x);
        int len = str.length()-1;
        long ret = x & 1;

        for(int i=len; i>0; i--){
            if(str.charAt(len-i) == '1'){
                ret += dp[i-1] + (x - (1L << i) + 1);
                x -= (1L << i);
            }
        }

        return ret;
    }

}
