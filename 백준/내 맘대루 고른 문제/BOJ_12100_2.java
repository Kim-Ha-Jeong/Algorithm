import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_12100_2 {
    static int n;
    static int ans = 0;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] order = new int[5];
        dfs(0, order);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int[] order){
        if(depth == 5){
            int[][] tmp = copy(map);
            for(int i=0; i<5; i++){
                if(order[i] < 2){
                    tmp = upAndDown(order[i], tmp);
                } else {
                    tmp = leftAndRight(order[i], tmp);
                }
            }
            int max = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    max = Math.max(tmp[i][j], max);
                }
            }
            ans = Math.max(ans, max);
            return;
        }

        for(int i=0; i<4; i++){
            order[depth] = i;
            dfs(depth+1, order);
        }
    }

    static int[][] leftAndRight(int dir, int[][] map){
        for(int i=0; i<n; i++){
            if(dir == 2) map[i] = up(map[i]);
            else map[i] = down(map[i]);
        }

        return map;
    }

    static int[][] upAndDown(int dir, int[][] map){
        int[] tmp = new int[n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                tmp[j] = map[j][i];
            }
            if(dir == 0) tmp = up(tmp);
            else tmp = down(tmp);
            for(int j=0; j<n; j++){
                map[j][i] = tmp[j];
            }
        }

        return map;
    }

    static int[] up(int[] arr){
        boolean[] v = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                continue;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] != 0) {
                    if (arr[j] == arr[i] && !v[j]) {
                        arr[j] += arr[i];
                        v[j] = true;
                    } else {
                        if (j + 1 == i)
                            break;
                        arr[j + 1] = arr[i];
                    }
                    arr[i] = 0;
                    break;
                }

                if (j == 0) {
                    arr[j] = arr[i];
                    arr[i] = 0;
                }
            }
        }

        return arr;
    }

    static int[] down(int[] arr){
        boolean[] v = new boolean[n];

        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 0)
                continue;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] != 0) {
                    if (arr[j] == arr[i] && !v[j]) {
                        arr[j] += arr[i];
                        v[j] = true;
                    } else {
                        if (j - 1 == i)
                            break;
                        arr[j - 1] = arr[i];
                    }
                    arr[i] = 0;
                    break;
                }

                if (j == n - 1) {
                    arr[j] = arr[i];
                    arr[i] = 0;
                }
            }
        }

        return arr;
    }

    static int[][] copy(int[][] origin){
        int[][] tmp = new int[n][n];

        for(int i=0; i<n; i++){
            System.arraycopy(origin[i],0,tmp[i],0,n);
        }

        return tmp;
    }
}
