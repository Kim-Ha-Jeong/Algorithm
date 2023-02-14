import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2666 {
    static int n, m;
    static int[] arr;
    static int ans = Integer.MAX_VALUE;
    static int[] open;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st  = new StringTokenizer(br.readLine());
        int openA = Integer.parseInt(st.nextToken());
        int openB = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        arr = new int[m];

        for(int i=0; i<m; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dfs(openA, openB,0, 0);
        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void dfs(int openA, int openB, int cnt, int depth){
        if(depth == m){
            ans = Math.min(ans, cnt);
            return;
        }

        int nextCnt1 = Math.abs(openA - arr[depth]);
        int nextCnt2 = Math.abs(openB - arr[depth]);

        dfs(openB, arr[depth], cnt+nextCnt1, depth + 1);
        dfs(openA, arr[depth], cnt+nextCnt2, depth+1);
    }

}
