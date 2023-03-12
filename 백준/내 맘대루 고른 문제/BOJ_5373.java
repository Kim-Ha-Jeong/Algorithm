import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5373 {
    static int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static char[][][] cube = new char[6][3][3];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0){
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            init();
            while(n-->0){
                turn(st.nextToken());
            }

            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    sb.append(cube[U][j][2-i]);
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void alter(int f, int u, int l, int d, int r, boolean clk) { // (u,l,d,r 순서는 상관없음, 움직여지는 순서만 맞으면 됨)
        char[][] tmp = new char[3][3];
        char[] tmp2 = new char[3];

        if (clk) {
            for (int i = 0; i < 3; ++i) { // 돌리는 대상 면을 반시계방향으로 돌림
                for (int j = 0; j < 3; ++j) {
                    tmp[i][j] = cube[f][2 - j][i];
                }
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
            for (int i = 0; i < 3; ++i) {// 돌리는 대상 면을 반시계방향으로 돌림
                for (int j = 0; j < 3; ++j) {
                    tmp[i][j] = cube[f][j][2 - i];
                }
            }
            for (int i = 0; i < 3; ++i) // 돌아가는 면 기준 가장 앞에 있는 (u)를 백업한다.
                tmp2[i] = cube[u][i][0];
            for (int i = 0; i < 3; ++i) //
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

    static void init(){
        for(int i=0; i<3; i++){
            Arrays.fill(cube[U][i], 'w');
            Arrays.fill(cube[D][i], 'y');
            Arrays.fill(cube[F][i], 'r');
            Arrays.fill(cube[B][i], 'o');
            Arrays.fill(cube[L][i], 'g');
            Arrays.fill(cube[R][i], 'b');
        }
    }
}
