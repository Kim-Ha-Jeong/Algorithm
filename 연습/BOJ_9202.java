import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_9202 {
    static Node trie = new Node();
    static char[][] map = new char[4][4];
    static boolean[][] visited = new boolean[4][4];

    static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] score = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };

    static int ans = 0, find = 0, len = 0;
    static String longer = "";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            Node tmp = trie;
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < c.length; j++) {
                if (tmp.child[c[j] - 'A'] == null) {
                    tmp.child[c[j] - 'A'] = new Node();
                }
                tmp = tmp.child[c[j] - 'A'];
            }
            tmp.isEnd = true;
        }

        br.readLine();
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            map = new char[4][4];
            visited = new boolean[4][4];
            find = 0;
            len = 0;
            ans = 0;
            longer = "";

            for (int i = 0; i < 4; i++) {
                char[] temp = br.readLine().toCharArray();
                map[i] = temp;
            }
            br.readLine();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (trie.child[map[i][j] - 'A'] != null) {
                        dfs(i, j, trie.child[map[i][j] - 'A'], Character.toString(map[i][j]));
                    }
                }
            }

            sb.append(ans).append(" ").append(longer).append(" ").append(find).append("\n");
            clean(trie);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void clean(Node tmp) {
        if (tmp.isHit)
            tmp.isHit = false;

        for (Node n : tmp.child) {
            if (n != null)
                clean(n);
        }
    }

    static void dfs(int x, int y, Node to, String str) {
        visited[x][y] = true; // 틀린 부분 2 - checkin & out

        if (to.isEnd && !to.isHit) {
            ans += score[str.length()];
            find++;
            compare(str);
            to.isHit = true;
        }

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                continue;

            Node next = to.child[map[nx][ny] - 'A'];

            if (!visited[nx][ny] && next != null) {
                dfs(nx, ny, next, str + map[nx][ny]);
            }
        }

        visited[x][y] = false;
    }

    static void compare(String str) { // 틀린 부분 1 - 정렬
        if (str.length() >= len) {
            if (str.length() == len) {
                int num = longer.compareTo(str);
                if (num > 0)
                    longer = str;
            } else {
                len = str.length();
                longer = str;
            }
        }
    }

    static class Node {
        Node[] child = new Node[26];
        boolean isHit = false;
        boolean isEnd = false;

        boolean hasChild(char c) {
            return child[c - 'A'] != null;
        }

    }
}
