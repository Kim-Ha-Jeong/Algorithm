import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_3687 {
    static String[][] dp = new String[101][2];
    static int[] num = {6,2,5,5,4,5,6,3,7,6};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        dp[2][1] = dp[2][0] = "1";
        dp[3][1] = dp[3][0] = "7";
        dp[4][1] = "11";
        dp[4][0] = "4";
        dp[5][1] = "71";
        dp[5][0] = "2";
        dp[6][1] = "111";
        dp[6][0] = "6";
        dp[7][1] = "711";
        dp[7][0] = "8";

        for(int i=8; i<101; i++){
            dp[i][1] = "";
            dp[i][0] = "99999999999999999999999999999999999999999999999999999";
            for(int j=2; j<i/2+1; j++){
                int a = j;
                int b = i - j;

                String maxA = dp[a][1] + dp[b][1];
                String maxB = dp[b][1] + dp[a][1];
                String max = getBig(maxA, maxB);
                dp[i][1] = getBig(max, dp[i][1]);

                String minA = dp[a][0] + dp[b][0];
                if(b == 6){
                    String minAA = dp[a][0] + "0";
                    minA = getSmall(minA, minAA);
                }
                String minB = dp[b][0] + dp[a][0];
                if(a == 6){
                    String minBB = dp[b][0] + "0";
                    minB = getSmall(minB, minBB);
                }
                String min = getSmall(minA, minB);
                dp[i][0] = getSmall(min, dp[i][0]);
            }
        }

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0){
            int n = Integer.parseInt(br.readLine());

            sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static String getBig(String A, String B){
        int aLen = A.length();
        int bLen = B.length();

        if(aLen > bLen) return A;
        else if(bLen > aLen) return B;
        else {
            if(A.compareTo(B) > 0) return A;
            else return B;
        }
    }

    static String getSmall(String A, String B){
        int aLen = A.length();
        int bLen = B.length();

        if(aLen < bLen) return A;
        else if(bLen < aLen) return B;
        else {
            if(A.compareTo(B) < 0) return A;
            else return B;
        }
    }

}
