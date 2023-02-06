import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_23971 {
    static int h,w,n,m;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken())+1;
        m = Integer.parseInt(st.nextToken())+1;

        long ans = 0;
        int row = (int)Math.ceil(h / (double)n);
        int col = (int)Math.ceil(w / (double)m);

        ans = row * col;
        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
