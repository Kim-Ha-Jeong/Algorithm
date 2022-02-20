import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;

public class BOJ_1339 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int[] alphabet = new int[26];

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            int len = c.length;
            for (int j = 0; j < len; j++) {
                alphabet[c[j] - 'A'] += (int) Math.pow(10, len - j - 1);
            }
        }

        Arrays.sort(alphabet);

        int sum = 0;
        int num = 9;

        for (int i = 25; i >= 0; i--) {
            if (alphabet[i] == 0)
                break;
            sum += num * alphabet[i];
            num--;
        }

        bw.write(sb.append(sum).toString());
        bw.flush();
        bw.close();

    }

}
