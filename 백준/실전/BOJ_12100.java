import java.util.*;
import java.io.*;

public class BOJ_12100 {
    static int n;
    static int[][] map;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] tmp = new int[5];
        dfs(0, tmp);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int[] order) {
        int[][] tmp = copy();
        if (depth == 5) {
            for (int i = 0; i < 5; i++) {
                if (order[i] < 2) {
                    upAndDown(order[i], tmp);
                } else {
                    leftAndRight(order[i], tmp);
                }
            }
            cal(tmp);
            return;
        }

        for (int i = 0; i < 4; i++) {
            order[depth] = i;
            dfs(depth + 1, order);
        }
    }

    static int[][] copy() {
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    static void cal(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, map[i][j]);
            }
        }
    }

    static int[][] leftAndRight(int flag, int[][] arr) {
        for (int i = 0; i < n; i++) {
            if (flag == 2) {
                up(arr[i]);
            } else {
                down(arr[i]);
            }
        }

        return arr;
    }

    static int[][] upAndDown(int flag, int[][] arr) {
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[j] = arr[j][i];
            }
            if (flag == 0)
                up(tmp);
            else
                down(tmp);
            for (int j = 0; j < n; j++) {
                arr[j][i] = tmp[j];
            }
        }

        return arr;
    }

    static void down(int[] arr) {
        boolean[] v = new boolean[n];

        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 0)
                continue;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] != 0) {
                    if (arr[j] == arr[i] && !v[j]) {
                        arr[j] += arr[i];
                        v[j] = true;
                    } else {
                        if (j - 1 == i)
                            break;
                        arr[j - 1] = arr[i];
                    }
                    arr[i] = 0;
                    break;
                }

                if (j == n - 1) {
                    arr[j] = arr[i];
                    arr[i] = 0;
                }
            }
        }
    }

    static void up(int[] arr) {
        boolean[] v = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                continue;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] != 0) {
                    if (arr[j] == arr[i] && !v[j]) {
                        arr[j] += arr[i];
                        v[j] = true;
                    } else {
                        if (j + 1 == i)
                            break;
                        arr[j + 1] = arr[i];
                    }
                    arr[i] = 0;
                    break;
                }

                if (j == 0) {
                    arr[j] = arr[i];
                    arr[i] = 0;
                }
            }
        }
    }
}