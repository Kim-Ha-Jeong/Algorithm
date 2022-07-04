import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_6064 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        StringTokenizer st;

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x == y) {
                sb.append(x).append("\n");
                continue;
            }

            int ans = -1;
            int cnt = 0;

            int limit = LCM(Math.max(m, n), Math.min(m, n));

            while (cnt * n < limit) {
                if ((cnt * n + y - x) % m == 0) {
                    ans = cnt * n + y;
                    break;
                }
                cnt++;
            }

            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int LCM(int a, int b) {
        int mul = a * b;

        int r = -1;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return mul / a;
    }

}
