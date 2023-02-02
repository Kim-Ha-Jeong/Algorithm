import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1590 {
    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long t = Long.parseLong(st.nextToken());


        long ans = Long.MAX_VALUE;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            long start = Long.parseLong(st.nextToken());
            long term = Long.parseLong(st.nextToken());
            int bus = Integer.parseInt(st.nextToken());

            for(int j=0; j<bus; j++){
                long now = start + term * j;
                if(now >= t){
                    ans = Math.min(now, ans);
                    break;
                }
            }
        }

        if(ans == Long.MAX_VALUE){
            ans = -1;
        } else {
            ans -= t;
        }

        StringBuffer sb = new StringBuffer();
        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }
}
