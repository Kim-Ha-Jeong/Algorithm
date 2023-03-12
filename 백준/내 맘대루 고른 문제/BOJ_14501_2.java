import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14501_2 {
    static int n;
    static int[] dp;
    static Day[] day;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        day = new Day[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            day[i] = new Day(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<n; i++){
            if(day[i].time + i < n+1){
                dp[i + day[i].time] = Math.max(dp[i+day[i].time], dp[i] + day[i].price);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }

        bw.write(dp[n]+"");
        bw.flush();
        bw.close();
    }

    static class Day {
        int time;
        int price;

        Day(int time, int price){
            this.time = time;
            this.price = price;
        }
    }
}
