import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20044 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[2*n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            ans = Math.min(ans, arr[i] + arr[2*n-i-1]);
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();

    }
}
