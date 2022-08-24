import java.util.*;
import java.io.*;

public class BOJ_16235 {
    static int n, m, k;
    static int[][] a;
    static int[][] map;
    static Queue<Tree> tree;
    static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        a = new int[n + 1][n + 1];
        tree = new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            tree.add(new Tree(x, y, z));

        }

        Collections.sort((List<Tree>) tree);

        for (int i = 0; i < k; i++) {
            springAndSummer();
            fall();
            winter();
        }

        bw.write(sb.append(tree.size()).toString());
        bw.flush();
        bw.close();
    }

    static void springAndSummer() {
        ArrayList<Tree> remove = new ArrayList<>();

        int size = tree.size();
        while (size-- > 0) {
            Tree tmp = tree.poll();

            if (map[tmp.x][tmp.y] < tmp.age) {
                remove.add(tmp);
            } else {
                map[tmp.x][tmp.y] -= tmp.age;
                tree.add(new Tree(tmp.x, tmp.y, tmp.age + 1));
            }
        }

        for (Tree t : remove) {
            map[t.x][t.y] += (t.age / 2);
        }
    }

    static void fall() {
        int size = tree.size();

        ArrayList<Tree> back = new ArrayList<>();

        while (size-- > 0) {
            Tree t = tree.poll();

            if (t.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = t.x + dx[i];
                    int ny = t.y + dy[i];

                    if (nx < 1 || nx >= n + 1 || ny < 1 || ny >= n + 1)
                        continue;

                    tree.add(new Tree(nx, ny, 1));
                }
            }

            back.add(t);
        }

        for (Tree tmp : back) {
            tree.add(tmp);
        }
    }

    static void winter() {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                map[i][j] += a[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int age;

        Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree t) {
            return t.age - this.age;
        }
    }

}
