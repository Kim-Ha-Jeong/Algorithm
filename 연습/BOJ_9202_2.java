import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_9202_2 {
    static Node root = new Node();
    static char[][] map = new char[4][4];
    static boolean[][] visited = new boolean[4][4];

    static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] score = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };

    static int ans = 0, find = 0, max = 0, len = 0;
    static String longer = "";
    static StringBuffer sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sbResult = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            insert(br.readLine());
        }

        br.readLine();
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            map = new char[4][4];
            visited = new boolean[4][4];
            longer = "";
            ans = 0;
            find = 0;
            sb = new StringBuffer();

            for (int i = 0; i < 4; i++) {
                char[] temp = br.readLine().toCharArray();
                map[i] = temp;
            }
            br.readLine();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (root.hasChild(map[i][j])) {
                        dfs(i, j, root.getChild(map[i][j]), 1);
                    }
                }
            }

            sbResult.append(ans).append(" ").append(longer).append(" ").append(find).append("\n");
            root.clearHit();
        }

        bw.write(sbResult.toString());
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

    static void dfs(int x, int y, Node to, int length) {
        visited[x][y] = true;
        sb.append(map[x][y]);

        if (to.isEnd && !to.isHit) {
            to.isHit = true;
            ans += score[length];
            find++;
            String foundWord = sb.toString();
            if (compare(longer, foundWord) > 0) {
                longer = foundWord;
            }
        }

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                continue;

            if (!visited[nx][ny] && to.hasChild(map[nx][ny])) {
                dfs(nx, ny, to.getChild(map[nx][ny]), length + 1);
            }
        }

        visited[x][y] = false;
        sb.deleteCharAt(length - 1);
    }

    static void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.hasChild(word.charAt(i))) {
                current.child[word.charAt(i) - 'A'] = new Node();
            }
            current = current.getChild(word.charAt(i));
        }
        current.isEnd = true;
    }

    static int compare(String arg0, String arg1) {
        int result = Integer.compare(arg1.length(), arg0.length());
        if (result == 0) {
            return arg0.compareTo(arg1);
        } else {
            return result;
        }
    }

    static class Node {
        Node[] child = new Node[26];
        boolean isHit = false;
        boolean isEnd = false;

        boolean hasChild(char c) {
            return child[c - 'A'] != null;
        }

        Node getChild(char c) {
            return child[c - 'A'];
        }

        void clearHit() {
            this.isHit = false;
            for (int i = 0; i < child.length; i++) {
                Node ch = child[i];
                if (ch != null)
                    ch.clearHit();
            }
        }
    }
}
