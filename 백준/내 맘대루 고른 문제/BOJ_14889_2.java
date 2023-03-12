import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14889_2 {
    static int n, ans = Integer.MAX_VALUE;
    static int[][] s;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        s = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n ;j++){
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] arrA = new int[n/2];
        boolean[] v = new boolean[n];
        combination(0,0,arrA,v);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx, int[] arrA, boolean[] choice){
        if(depth == n/2){
            ans = Math.min(ans,calc(choice, arrA));
            return;
        }

        for(int i=idx; i<n; i++){
            choice[i] = true;
            arrA[depth] = i;
            combination(depth+1, i+1,arrA, choice);
            choice[i] = false;
        }
    }

    static int calc(boolean[] choice, int[] arrA){
        int[] arrB = new int[n/2];

        int idx = 0;
        for(int i=0; i<n; i++){
            if(!choice[i]) arrB[idx++] = i;
        }

        int sumA = 0, sumB = 0;
        for(int i=0; i<n/2; i++){
            int a = arrA[i];
            int b = arrB[i];
            for(int j=i+1; j<n/2; j++){
                int c = arrA[j];
                int d = arrB[j];
                sumA += (s[a][c] + s[c][a]);
                sumB += (s[b][d] + s[d][b]);
            }
        }

        return Math.abs(sumA - sumB);
    }
}
