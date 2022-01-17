import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1759 {
    static char[] arr;
    static int l, c;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("주소이름"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        l = Integer.parseInt(s[1]);
        c = Integer.parseInt(s[0]);

        arr = new char[l];
        s = br.readLine().split(" ");

        for (int i = 0; i < l; i++) {
            arr[i] = s[i].charAt(0);
        }

        Arrays.sort(arr);

        char[] secret = new char[c];
        dfs(0, 0, 0, 0, secret);
    }

    static void dfs(int depth, int idx, int ja, int mo, char[] pwd) {
        if (depth == c) {
            if (mo >= 1 && ja >= 2) {
                for (int i = 0; i < c; i++) {
                    System.out.print(pwd[i]);
                }
                System.out.println();
            }
            return;
        }

        for (int i = idx; i < l; i++) {
            pwd[depth] = arr[i];
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                dfs(depth + 1, i + 1, ja, mo + 1, pwd);
            } else {
                dfs(depth + 1, i + 1, ja + 1, mo, pwd);
            }
        }
    }
}
