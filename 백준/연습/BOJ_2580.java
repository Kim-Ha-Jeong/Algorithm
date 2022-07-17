import java.util.*;
import java.io.*;

public class BOJ_2580 {
    static ArrayList<Node> list = new ArrayList<>();
    static int[][] map = new int[9][9];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
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
            return;
        }

        Node tmp = list.get(depth);
        for (int i = 1; i < 10; i++) {
            if (!check(tmp.x, tmp.y, i))
                continue;
            map[tmp.x][tmp.y] = i;
            dfs(depth + 1);
            map[tmp.x][tmp.y] = 0;
        }
    }

    static boolean check(int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[x][i] == num || map[i][y] == num)
                return false;
        }

        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;

        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
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
