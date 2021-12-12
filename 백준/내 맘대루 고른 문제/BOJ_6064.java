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

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cnt = 0;

            if (x == y) {
                sb.append(x).append("\n");
                continue;
            }

            int limit = LCM(Math.max(m, n), Math.min(m, n));
            int ans = -1;

            while (cnt * m < limit) {
                if ((cnt * m + x - y) % n == 0) {
                    ans = cnt * m + x;
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
        int originA = a;
        int originB = b;

        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return originA * originB / a;
    }

}
