import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1943 {
    static int n;
    static Money[] money;
    static int sum;
    static int flag;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = 3;

        StringBuffer sb = new StringBuffer();
        while(tc--> 0){
            n = Integer.parseInt(br.readLine());
            money = new Money[n];
            flag = 0;
            sum = 0;

            boolean[] dp = new boolean[100001];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                money[i] = new Money(c, cnt);

                for(int j=1; j<cnt+1; j++){
                    dp[c*j] = true;
                }
                sum += c*cnt;
            }

            if(sum % 2 == 1) sb.append(0);
            else if(dp[sum/2]){
                sb.append(1);
            } else {
                dp[0] = true;
                for(int i=0; i<n; i++){
                    int coin = money[i].coin;
                    int cnt = money[i].cnt;
                    for(int j=sum/2; j>=coin; j--){
                        if(dp[j - coin]){
                            for(int k=0; k<cnt; k++){
                                if(j + k * coin > sum/2) break;
                                dp[j+ k*coin] = true;
                            }
                        }
                    }
                }

                if(dp[sum/2]) sb.append(1);
                else sb.append(0);
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Money{
        int coin;
        int cnt;

        Money(int coin, int cnt){
            this.coin = coin;
            this.cnt = cnt;
        }
    }
}
