import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        while(tc-->0){
            int n = Integer.parseInt(br.readLine());
            long[] arr = new long[n];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                arr[i] = Long.parseLong(st.nextToken());
            }

            long[] max = new long[n];
            max[n-1] = arr[n-1];
            for(int i=n-2; i>=0; i--){
                max[i] = Math.max(max[i+1], arr[i]);
            }

            long sum = 0;
            for(int i=0; i<n; i++){
                if(max[i] > arr[i]) sum += (max[i] - arr[i]);
            }

            sb.append(sum).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
