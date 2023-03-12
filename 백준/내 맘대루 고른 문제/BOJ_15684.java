import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_15684 {
    static int n,m,h;
    static boolean[][] ladder;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new boolean[h][n-1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            ladder[a][b] = true;
        }

        for(int i=0; i<=3; i++){
            combination(0, 0, i);
        }

        System.out.println(-1);
    }

    static void combination(int depth, int idx, int end){
        if(depth == end){
            boolean flag = true;
            for(int i=0; i<n; i++){
                flag &= cal(i);
                if(!flag) break;
            }
            if(flag){
                System.out.println(end);
                System.exit(0);
            }
            return;
        }

        for(int i=idx; i<(n-1)*h; i++){
            int x = i / (n-1);
            int y = i % (n-1);

            if(ladder[x][y] || (y-1 > 0 && ladder[x][y-1])) continue;
            ladder[x][y] = true;
            combination(depth+1, i+1, end);
            ladder[x][y] = false;
        }
    }

    static boolean cal(int start){
        int now = start;
        for(int i=0; i<h; i++){
            if(now<n-1 && ladder[i][now]){
                now++;
            } else if(now-1 >= 0 && ladder[i][now-1]){
                now--;
            }
        }

        return now == start;
    }
}
