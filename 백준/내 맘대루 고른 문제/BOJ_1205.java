import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1205 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int record = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        if(n != 0) st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = -1;
        int idx = 1;
        for(int i=0; i<arr.length; i++){
            if(i >= p) break;
            if(arr[i] < record){
                ans = idx;
                break;
            }
            if(arr[i] != record) idx++;
        }

        if(p > arr.length && ans == -1){
            ans = idx;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
