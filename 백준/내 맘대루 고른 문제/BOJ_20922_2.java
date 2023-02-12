import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_20922_2 {
    static int n,k;
    static int[] arr, cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        cnt = new int[100001];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int front = 0, back = 0;
        while(back < n){
            while(back < n && cnt[arr[back]] + 1 <= k){
                cnt[arr[back]]++;
                back++;
            }
            int len = back - front;
            ans = Math.max(ans, len);
            cnt[arr[front]]--;
            front++;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
