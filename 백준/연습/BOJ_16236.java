import java.util.*;
import java.io.*;

public class BOJ_16236 {
    static int eatCnt = 0, sharkX = -1, sharkY = -1, shark = 2, n;
    static int[][] map;
    static int[][] dist;
    static int minD, minX, minY, time = 0;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        solve();

        bw.write(sb.append(time).toString());
        bw.flush();
        bw.close();
    }

    static void solve() {
        while (true) {
            dist = new int[n][n];
            minD = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minX = Integer.MAX_VALUE;

            bfs();

            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                eatCnt++;
                sharkX = minX;
                sharkY = minY;
                map[minX][minY] = 0;
                time += dist[minX][minY];

                if (eatCnt == shark) {
                    eatCnt = 0;
                    shark++;
                }
            } else
                break;
        }
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sharkX, sharkY));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                if (dist[nx][ny] != 0 || map[nx][ny] > shark)
                    continue;

                dist[nx][ny] = dist[now.x][now.y] + 1;
                q.add(new Node(nx, ny));

                if (map[nx][ny] != 0 && map[nx][ny] < shark) {
                    if (minD > dist[nx][ny]) {
                        minD = dist[nx][ny];
                        minX = nx;
                        minY = ny;
                    } else if (minD == dist[nx][ny]) {
                        if (minX > nx) {
                            minX = nx;
                            minY = ny;
                        } else if (minX == nx) {
                            if (minY > ny) {
                                minY = ny;
                            }
                        }
                    }

                }
            }
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

}
