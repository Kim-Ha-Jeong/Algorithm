import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ_20055 {
    static int cnt = 0;
    static int[] arr;
    static boolean[] robot;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        arr = new int[n * 2];
        robot = new boolean[n];

        s = br.readLine().split(" ");

        for (int i = 0; i < 2 * n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        int level = 1;

        while (true) {
            rotate();

            for (int i = n - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && arr[i] >= 1) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    arr[i]--;
                    if (arr[i] == 0)
                        cnt++;
                }
            }

            if (arr[0] > 0) {
                robot[0] = true;
                arr[0]--;
                if (arr[0] == 0)
                    cnt++;
            }

            if (k <= cnt)
                break;
            level++;
        }

        sb.append(level);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void rotate() {
        int tmp = arr[2 * n - 1];
        for (int i = 2 * n - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;

        for (int i = n - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = robot[n - 1] = false;
    }
}
