import java.io.*;

public class BOJ_2839 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        int ans = 0;
        while (true) {
            if (n % 5 == 0) {
                ans += (n / 5);
                sb.append(ans);
                break;
            } else if (n <= 0) {
                sb.append(-1);
                break;
            }
            n -= 3;
            ans++;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
