import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1253_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int ans = 0;
        for(int i=0; i<n; i++){
            long sum = arr[i];
            int start = 0;
            int end = n-1;

            boolean flag = false;
            while(true){
                if(i == start) start++;
                if(i == end) end--;

                if(end <= start) break;

                long tmp = arr[start] + arr[end];
                if(sum == tmp){
                    flag = true;
                    break;
                } else if(sum < tmp){
                    end--;
                } else {
                    start++;
                }
            }

            if(flag) ans++;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
