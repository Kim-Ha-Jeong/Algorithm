import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_5373 {
    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static int T, N, cnt;
    static char[][][] cube;
    static char[] colors = new char[] { 'w', 'y', 'r', 'o', 'g', 'b' };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            init();
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                turn(st.nextToken());
            }

            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cube[U][j][2 - k]);
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void init() {
        cube = new char[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = colors[i];
                }
            }
        }
    }

    static void alter(int f, int u, int l, int d, int r, boolean clk) {
        char[][] tmp = new char[3][3];
        char[] tmp2 = new char[3];

        if (clk) {
            for (int i = 0; i < 3; ++i)
                for (int j = 0; j < 3; ++j) {
                    tmp[i][j] = cube[f][2 - j][i];
                }
            for (int i = 0; i < 3; ++i)
                tmp2[i] = cube[u][i][0];
            for (int i = 0; i < 3; ++i)
                cube[u][i][0] = cube[l][0][2 - i];
            for (int i = 0; i < 3; ++i)
                cube[l][0][2 - i] = cube[d][2][i];
            for (int i = 0; i < 3; ++i)
                cube[d][2][i] = cube[r][2 - i][2];
            for (int i = 0; i < 3; ++i)
                cube[r][2 - i][2] = tmp2[i];
        } else {
            for (int i = 0; i < 3; ++i)
                for (int j = 0; j < 3; ++j) {
                    tmp[i][j] = cube[f][j][2 - i];
                }
            for (int i = 0; i < 3; ++i)
                tmp2[i] = cube[u][i][0];
            for (int i = 0; i < 3; ++i)
                cube[u][i][0] = cube[r][2 - i][2];
            for (int i = 0; i < 3; ++i)
                cube[r][2 - i][2] = cube[d][2][i];
            for (int i = 0; i < 3; ++i)
                cube[d][2][i] = cube[l][0][2 - i];
            for (int i = 0; i < 3; ++i)
                cube[l][0][2 - i] = tmp2[i];
        }
        cube[f] = tmp;
    }

    static void turn(String s) {
        boolean flag = s.charAt(1) == '+';

        switch (s.charAt(0)) {
        case 'U':
            alter(U, L, F, R, B, flag);
            break;
        case 'D':
            alter(D, B, R, F, L, flag);
            break;
        case 'F':
            alter(F, U, L, D, R, flag);
            break;
        case 'B':
            alter(B, R, D, L, U, flag);
            break;
        case 'L':
            alter(L, F, U, B, D, flag);
            break;
        case 'R':
            alter(R, D, B, U, F, flag);
            break;
        }

    }
}