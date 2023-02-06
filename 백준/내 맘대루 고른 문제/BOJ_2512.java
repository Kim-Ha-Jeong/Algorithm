import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {
    static int n, m;
    static int[] arr;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        sum = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        int ans = arr[n-1];

        for(int i=0; i<n; i++){
            if(i == 0) sum[i] = arr[i];
            else sum[i] = sum[i-1]+arr[i];
        }

        if(sum[n-1] >= m) {
            int idx = -1;
            for(int i=0; i<n; i++){
                int tmp = i == 0 ? arr[i]*n : sum[i-1] + arr[i]*(n-i);
                if(tmp >= m) {
                    idx = i;
                    break;
                }
            }

            if(idx == 0){
                ans = solve(0, arr[idx], idx);
            } else {
                ans = solve(arr[idx - 1], arr[idx], idx);
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static int solve(int start, int end, int idx){
        while(start < end){
            if(start+1 == end) {
                int tmp = idx == 0 ? end*n : sum[idx-1] + end*(n-idx);
                if(tmp <= m){
                    start = end;
                }
                break;
            }
            int mid = (start + end)/2;

            int tmp = idx == 0? mid * n : sum[idx-1] + mid * (n-idx);

            if(tmp > m){
                end = mid-1;
            } else if(tmp <= m){
                start = mid;
                if(tmp == m){
                    break;
                }
            }
        }

        return start;
    }
}
