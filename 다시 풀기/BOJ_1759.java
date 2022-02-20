import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ_1759 {
    static int l, c;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        char[] c = new char[l];
        dfs(0, 0, 0, 0, c);
    }

    static void dfs(int depth, int idx, int cons, int vowel, char[] str) {
        if (depth == l) {
            if (cons >= 2 && vowel >= 1) {
                for (int i = 0; i < l; i++) {
                    System.out.print(str[i]);
                }
                System.out.println();
            }
            return;
        }

        for (int i = idx; i < c; i++) {
            str[depth] = arr[i];

            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                dfs(depth + 1, i + 1, cons, vowel + 1, str);
            } else {
                dfs(depth + 1, i + 1, cons + 1, vowel, str);
            }
        }
    }

}
