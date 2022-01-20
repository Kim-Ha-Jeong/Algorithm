import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14476 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] front = new int[n];
        int[] back = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        front[0] = arr[0];
        back[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            front[i] = gcd(front[i - 1], arr[i]);
            back[n - i - 1] = gcd(back[n - i], arr[n - i - 1]);
        }

        int ans = -1;
        int k = -1;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (back[1] > ans) {
                    k = i;
                    ans = back[1];
                }
            } else if (i == n - 1) {
                if (front[n - 2] > ans) {
                    k = i;
                    ans = front[n - 2];
                }
            } else {
                int tmp = gcd(front[i - 1], back[i + 1]);
                if (tmp > ans) {
                    k = i;
                    ans = tmp;
                }
            }
        }

        if (k == -1 || arr[k] % ans == 0)
            sb.append(-1);
        else
            sb.append(ans).append(" ").append(arr[k]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int gcd(int a, int b) {
        if (b > a) {
            int tmp = b;
            b = a;
            a = tmp;
        }

        int r = -1;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}
