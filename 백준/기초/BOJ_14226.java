import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ_14226 {
    static boolean[][] visited = new boolean[2001][2001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        bfs(n);
    }

    static void bfs(int s) {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(1, 0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            int screen = now.screen;
            int clip = now.clipboard;
            int time = now.time;

            if (now.screen == s) {
                System.out.println(time);
                return;
            }

            if (screen > 0 && screen < 2000) {
                if (!visited[screen][screen]) {
                    visited[screen][screen] = true;
                    q.add(new Node(screen, screen, time + 1));
                }

                if (!visited[screen - 1][clip]) {
                    visited[screen - 1][clip] = true;
                    q.add(new Node(screen - 1, clip, time + 1));
                }
            }

            if (clip > 0 && clip + screen < 2000) {
                if (!visited[clip + screen][clip]) {
                    visited[clip + screen][clip] = true;
                    q.add(new Node(clip + screen, clip, time + 1));
                }
            }

        }

    }

    static class Node {
        int screen;
        int clipboard;
        int time;

        Node(int screen, int clipboard, int time) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.time = time;
        }

    }

}
