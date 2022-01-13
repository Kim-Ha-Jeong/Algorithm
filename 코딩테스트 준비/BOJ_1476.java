import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ_1476 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        String[] s = br.readLine().split(" ");

        int[] arr = new int[3];

        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(s[i]);
            if (arr[0] == 15 || arr[1] == 28 || arr[2] == 19)
                arr[i] = 0;
        }

        int m = 0;
        int x;

        while (true) {
            x = 28 * m + arr[1];
            if (x == 0)
                x = 28 * (++m) + arr[1];

            if (x % 15 == arr[0] && x % 19 == arr[2])
                break;
            m++;
        }

        sb.append(x);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
