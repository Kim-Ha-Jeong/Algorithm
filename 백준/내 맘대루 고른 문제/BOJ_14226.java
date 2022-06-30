import java.util.*;
import java.io.*;

public class BOJ_14226 {
    static boolean[][] visited = new boolean[2001][2001];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int s = Integer.parseInt(br.readLine());
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(1, 0, 0));
        visited[1][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            int screen = now.screen;
            int clip = now.clip;
            int time = now.time;

            if (screen == s) {
                sb.append(time);
                break;
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

            if (clip > 0 && screen + clip < 2000) {
                if (!visited[screen + clip][clip]) {
                    visited[screen + clip][clip] = true;
                    q.add(new Node(screen + clip, clip, time + 1));
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static class Node {
        int screen;
        int clip;
        int time;

        Node(int screen, int clip, int time) {
            this.screen = screen;
            this.clip = clip;
            this.time = time;
        }
    }
}
