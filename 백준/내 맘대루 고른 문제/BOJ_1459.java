import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1459 {
    static long x,y,w,s;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Long.parseLong(st.nextToken());
        y = Long.parseLong(st.nextToken());
        w = Long.parseLong(st.nextToken());
        s = Long.parseLong(st.nextToken());

        long ans = 0;

        long min = Math.min(x,y);
        long max = Math.max(x,y);

        if(w > s){
            ans += s * min;
            long tmp = max - min;
            if(tmp % 2 == 1){
                ans += w;
                tmp--;
            }
            ans += s * tmp;
        } else if(2*w > s){
            ans += s * min;
            ans += (max - min) * w;
        } else {
            ans += (max + min) * w;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
