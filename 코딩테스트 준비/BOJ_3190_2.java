import java.util.*;
import java.io.*;

public class BOJ_3190_2 {
    static int n;
    static int[][] map;
    static int APPLE = 9;
    static int SNAKE = 8;
    static int second = 1;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static ArrayList<Change> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = APPLE;
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Change(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }

        bfs();

        bw.write(sb.append(second).toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 1));
        map[1][1] = SNAKE;

        int nx = 1, ny = 1;
        int d = 0;
        while (true) {
            if (list.size() != 0 && second - 1 == list.get(0).second) {
                if (list.get(0).dir == 'D') {
                    d = (d + 1) % 4;
                } else {
                    d = (d + 3) % 4;
                }
                list.remove(0);
            }

            nx += dx[d];
            ny += dy[d];

            if (nx < 1 || nx >= n + 1 || ny < 1 || ny >= n + 1 || map[nx][ny] == SNAKE)
                break;

            int tmp = map[nx][ny];
            map[nx][ny] = SNAKE;
            q.add(new Node(nx, ny));

            if (tmp != APPLE) {
                Node start = q.poll();
                map[start.x][start.y] = 0;
            }

            second++;
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Change {
        int second;
        char dir;

        Change(int second, char dir) {
            this.second = second;
            this.dir = dir;
        }
    }
}
