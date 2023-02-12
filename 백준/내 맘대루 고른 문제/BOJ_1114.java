import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1114 {
    static int l,c,k;
    static int[] arr;
    static int ansIdx = -1, ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        StringBuffer sb = new StringBuffer();

        arr = new int[k+2];
        st = new StringTokenizer(br.readLine());
        arr[0] = 0;
        for(int i=1; i<k+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[k+1] = l;
        Arrays.sort(arr);


        solve();
        sb.append(ans).append(" ").append(ansIdx);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve(){
        int start = 1;
        int end = l;

        while(start <= end){
            int mid = (start + end)/2;

            if(isPossible(mid)){
                ans = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
    }

    static boolean isPossible(int length){
        int diff = 0;
        int idx = -1;
        int cnt = 0;
        for(int i=k; i>=0; i--){
            int tmp = arr[i+1] - arr[i];
            if(tmp > length){
                cnt = c+1;
                break;
            }
            diff += tmp;
            if(diff > length){
                diff = tmp;
                cnt++;
            }
        }

        if(cnt < c){
            idx = arr[1];
        } else {
            idx = diff;
        }

        if(cnt <= c) {
            ansIdx = idx;
            return true;
        }
        return false;
    }
}
