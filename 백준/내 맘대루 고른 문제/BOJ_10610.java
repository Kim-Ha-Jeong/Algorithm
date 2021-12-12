import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Arrays;

public class BOJ_10610 {
    static char[] c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        c = br.readLine().toCharArray();
        int len = c.length;

        int[] num = new int[len];

        for (int i = 0; i < len; i++) {
            num[i] = c[i] - '0';
        }

        Arrays.sort(num);

        if (num[0] != 0) {
            System.out.println(-1);
            return;
        }

        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += num[i];
            sb.append(num[i]);
        }

        if (sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}