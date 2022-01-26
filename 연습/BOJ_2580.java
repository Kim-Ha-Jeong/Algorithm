import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_2580 {
    static int[][] map = new int[9][9];
    static ArrayList<Node> zero = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    zero.add(new Node(i, j));
            }
        }

        dfs(0);

    }

    static void dfs(int depth) {
        if (depth == zero.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        Node cur = zero.get(depth);
        for (int i = 1; i < 10; i++) {
            if (!isRight(i, cur.x, cur.y))
                continue;
            map[cur.x][cur.y] = i;
            dfs(depth + 1);
            map[cur.x][cur.y] = 0;
        }
    }

    static boolean isRight(int num, int x, int y) {
        if (map[x][y] != 0)
            return false;

        for (int i = 0; i < 9; i++) {
            if (map[i][y] == num || map[x][i] == num)
                return false;
        }

        int nx = (x / 3) * 3;
        int ny = (y / 3) * 3;

        for (int i = nx; i < nx + 3; i++) {
            for (int j = ny; j < ny + 3; j++) {
                if (map[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
