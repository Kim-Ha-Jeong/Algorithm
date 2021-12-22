import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1248 {
    static int[] number = new int[21];
    static boolean[] visited = new boolean[21];
    static int n;
    static int[][] s;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split("");

        s = new int[n][n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp;
                if (str[idx].equals("-")) {
                    tmp = -1;
                } else if (str[idx].equals("+")) {
                    tmp = 1;
                } else {
                    tmp = 0;
                }
                s[i][j] = tmp;
                idx++;
            }
        }

        int[] order = new int[n];
        answer = new int[n];
        Arrays.fill(answer, -100);

        permutation(0, order);
    }

    static void permutation(int depth, int[] order) {
        if (depth == n) {
            for (int i : order) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.exit(0);
        }

        for (int i = -10; i < 11; i++) {
            order[depth] = i;
            if (isRight(depth, order)) {
                permutation(depth + 1, order);
            }
        }
    }

    static boolean isRight(int depth, int[] order) {
        for (int i = 0; i <= depth; i++) {
            int sum = 0;
            for (int j = i; j <= depth; j++) {
                sum += order[j];
                if (s[i][j] != (sum == 0 ? 0 : (sum > 0 ? 1 : -1)))
                    return false;
            }
        }

        return true;
    }

}
