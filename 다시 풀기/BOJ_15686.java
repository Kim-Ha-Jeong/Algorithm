import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_15686 {
    static int n, m;
    static int[][] arr;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Node> chicken = new ArrayList<>();
    static ArrayList<Node> choice = new ArrayList<>();
    static ArrayList<Node> home = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2)
                    chicken.add(new Node(i, j));
                else if (arr[i][j] == 1)
                    home.add(new Node(i, j));
            }
        }

        dfs(0, 0);

        bw.write(sb.append(ans).toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int idx) {
        if (depth == m) {
            cal();
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {
            choice.add(chicken.get(i));
            dfs(depth + 1, i + 1);
            choice.remove(choice.size() - 1);
        }
    }

    static void cal() {
        int sum = 0;

        for (Node homeNode : home) {
            int min = Integer.MAX_VALUE;
            for (Node chicken : choice) {
                int tmp = Math.abs(chicken.x - homeNode.x) + Math.abs(chicken.y - homeNode.y);

                min = Math.min(tmp, min);
            }
            sum += min;
        }

        ans = Math.min(ans, sum);
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
