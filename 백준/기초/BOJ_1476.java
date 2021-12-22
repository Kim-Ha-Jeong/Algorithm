import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class BOJ_1476 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int e = 1, s = 1, m = 1;
        int cnt = 1;

        while (true) {
            e = e == 0 ? 15 : e;
            s = s == 0 ? 28 : s;
            m = m == 0 ? 19 : m;

            if (e == E && s == S && m == M)
                break;
            e = (e + 1) % 15;
            s = (s + 1) % 28;
            m = (m + 1) % 19;
            cnt++;
        }

        sb.append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
