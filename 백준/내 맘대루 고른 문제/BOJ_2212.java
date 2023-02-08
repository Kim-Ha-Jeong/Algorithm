import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2212 {
    static int n,k;
    static int[] sensor;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st ;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        sensor = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            sensor[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        if(k < n) {
            Arrays.sort(sensor);
            int[] diff = new int[n-1];
            for(int i=0; i<n-1; i++){
                diff[i] = sensor[i+1] - sensor[i];
            }
            Arrays.sort(diff);

            for(int i=0; i<n-k; i++){
                ans += diff[i];
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
