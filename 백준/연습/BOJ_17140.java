import java.util.*;
import java.io.*;

public class BOJ_17140 {
    static int r, c, k;
    static int rCnt = 3, cCnt = 3;
    static int ans = 0;
    static int tmpR, tmpC;
    static int[][] arr = new int[100][100];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < rCnt; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cCnt; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (true) {
            if (arr[r][c] == k || cnt > 100)
                break;

            if (rCnt >= cCnt)
                calR();
            else
                calC();
            cnt++;
        }

        cnt = cnt > 100 ? -1 : cnt;
        bw.write(sb.append(cnt).toString());
        bw.flush();
        bw.close();

    }

    static int[] part(int[] row, int flag) {
        HashMap<Integer, Node> map = new HashMap<>();
        Arrays.sort(row);

        for (int i = 99; i >= 0; i--) {
            if (row[i] == 0)
                break;
            if (map.containsKey(row[i])) {
                map.put(row[i], new Node(row[i], map.get(row[i]).cnt + 1));
            } else {
                map.put(row[i], new Node(row[i], 1));
            }
        }

        List<Node> value = new ArrayList<>(map.values());
        Collections.sort(value);

        int idx = 0;
        int[] tmp = new int[100];
        for (Node now : value) {
            if (idx >= 100)
                break;
            tmp[idx] = now.num;
            tmp[idx + 1] = now.cnt;
            idx += 2;
        }

        if (flag == 0) {
            tmpC = Math.max(tmpC, value.size() * 2);
        } else {
            tmpR = Math.max(tmpR, value.size() * 2);
        }
        return tmp;
    }

    static void calR() {
        tmpC = 0;
        int levelCnt = rCnt;
        for (int i = 0; i < levelCnt; i++) {
            arr[i] = part(arr[i], 0);
        }
        cCnt = tmpC;
    }

    static void calC() {
        tmpR = 0;
        int[][] arr2 = new int[100][100];
        for (int i = 0; i < cCnt; i++) {
            int[] tmp = new int[100];
            for (int j = 0; j < rCnt; j++) {
                tmp[j] = arr[j][i];
            }
            tmp = part(tmp, 1);
            int j = 0;
            while (true) {
                if (tmp[j] == 0 || j >= 100)
                    break;

                arr2[j][i] = tmp[j];
                j++;
            }
        }
        arr = arr2;
        rCnt = tmpR;
    }

    static class Node implements Comparable<Node> {
        int num;
        int cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node n) {
            if (this.cnt == n.cnt)
                return this.num - n.num;
            return this.cnt - n.cnt;
        }

    }

}
