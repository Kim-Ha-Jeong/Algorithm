import java.util.*;
import java.io.*;

public class BOJ_1707 {
    static ArrayList<Integer>[] list;
    static int[] color;
    static int v, e;
    static boolean flag;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            list = new ArrayList[v + 1];
            color = new int[v + 1];
            flag = true;

            for (int i = 0; i < v + 1; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            for (int i = 1; i < v + 1; i++) {
                if (!flag)
                    break;

                if (color[i] == 0)
                    find(i);
            }

            if (flag)
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void find(int x) {
        Queue<Integer> q = new LinkedList<>();
        color[x] = 1;
        q.add(x);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                if (color[next] == 0) {
                    color[next] = color[now] * (-1);
                    q.add(next);
                } else if (color[next] + color[now] != 0) {
                    flag = false;
                    return;
                }
            }
        }
    }
}
