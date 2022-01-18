import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ_2003 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int[] arr = new int[n + 1];

        s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        int front = 0, back = 0;
        int sum = arr[0];
        int ans = 0;

        while (true) {
            if (back == n)
                break;
            if (sum >= m) {
                if (sum == m)
                    ans++;
                sum -= arr[front++];
            } else {
                sum += arr[++back];
            }
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
    }

}
