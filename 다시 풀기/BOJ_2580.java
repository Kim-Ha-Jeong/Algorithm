import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.ArrayList;

public class BOJ_2580 {
    static int[][] map;
    static ArrayList<Node> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    list.add(new Node(i, j));
            }
        }

        dfs(0);
    }

    static void dfs(int depth) {
        if (depth == list.size()) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }

            System.exit(0);
        }

        for (int i = 1; i < 10; i++) {
            if (check(depth, i)) {
                Node now = list.get(depth);
                map[now.x][now.y] = i;
                dfs(depth + 1);
                map[now.x][now.y] = 0;
            }
        }
    }

    static boolean check(int idx, int num) {
        Node now = list.get(idx);

        for (int i = 0; i < 9; i++) {
            if (map[now.x][i] == num || map[i][now.y] == num)
                return false;
        }

        int x1 = (now.x / 3) * 3;
        int x2 = x1 + 3;
        int y1 = (now.y / 3) * 3;
        int y2 = y1 + 3;

        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
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
