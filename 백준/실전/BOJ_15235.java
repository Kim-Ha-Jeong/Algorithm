import java.util.*;
import java.io.*;

public class BOJ_15235 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        Queue<Node> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            Node tmp = new Node(i, Integer.parseInt(st.nextToken()));
            q.add(tmp);
        }

        int second = 1;
        int[] ret = new int[n];
        while (!q.isEmpty()) {
            Node now = q.poll();

            now.pizza--;
            if (now.pizza != 0) {
                q.add(now);
            } else {
                ret[now.num] = second;
            }

            second++;
        }

        for (int i = 0; i < n; i++) {
            sb.append(ret[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static class Node {
        int num;
        int pizza;

        Node(int num, int pizza) {
            this.num = num;
            this.pizza = pizza;
        }
    }

}
