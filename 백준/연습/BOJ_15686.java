import java.io.*;
import java.util.*;

public class BOJ_15686 {
    static int n, m;
    static int[][] map;
    static ArrayList<Node> house = new ArrayList<>();
    static ArrayList<Node> chicken = new ArrayList<>();
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    house.add(new Node(i, j));
                else if (map[i][j] == 2)
                    chicken.add(new Node(i, j));
            }
        }

        visited = new boolean[chicken.size()];

        combination(0, 0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx) {
        if (depth == m) {
            ans = Math.min(ans, cal());
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            combination(depth + 1, i + 1);
            visited[i] = false;
        }

    }

    static int cal() {
        int sum = 0;
        int[] dist = new int[house.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < chicken.size(); i++) {
            if (!visited[i])
                continue;
            for (int j = 0; j < house.size(); j++) {
                Node a = chicken.get(i);
                Node b = house.get(j);

                int tmp = Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
                dist[j] = Math.min(dist[j], tmp);
            }
        }

        for (int i = 0; i < dist.length; i++) {
            sum += dist[i];
        }

        return sum;
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
