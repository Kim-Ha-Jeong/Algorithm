import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2631_2 {
    static int[] arr;
    static int n;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        dp = new int[n];
        dp[0] = arr[0];

        int cnt = 1;
        for(int i=1; i<n; i++){
           if(dp[cnt-1] < arr[i]){
               dp[cnt++] = arr[i];
           } else {
               int idx = getLowerBound(arr[i],0,cnt);
               dp[idx] = arr[i];
           }
        }

        bw.write((n - cnt)+"");
        bw.flush();
        bw.close();

    }

    static int getLowerBound(int num, int start, int end){
        while(start < end){
            int mid = (start + end)/2;

            if(dp[mid] >= num){
                end = mid;
            } else {
                start = mid+1;
            }
        }

        return start;
    }
}
