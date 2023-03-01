import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1138 {
    static int n;
    static int[] big;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        big = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++){
            big[i] = Integer.parseInt(st.nextToken());
        }

        v = new boolean[n+1];
        int[] order = new int[n+1];
        dfs(0, order);
    }

    static void dfs(int depth, int[] order){
        if(depth == n){
            for(int i=0; i<n; i++){
                System.out.print(order[i]+" ");
            }
            System.exit(0);
            return;
        }

        for(int i=1; i<n+1; i++){
            if(v[i]) continue;
            if(!isRight(depth, order, i)) continue;
            v[i] = true;
            order[depth] = i;
            dfs(depth+1, order);
            v[i] = false;
        }
    }

    static boolean isRight(int depth, int[] order, int now){
        int cnt = 0;
        for(int i=0; i<depth; i++){
            if(order[i] > now) cnt++;
        }

        return cnt == big[now];
    }
}
