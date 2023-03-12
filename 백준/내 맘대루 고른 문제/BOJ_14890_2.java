import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14890_2 {
    static int n,l;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int i=0; i<n; i++){
            int[] tmp = new int[n];
            for(int j=0; j<n ;j++){
                tmp[j] = map[j][i];
            }
            if(solve(tmp)) ans++;
            if(solve(map[i])) ans++;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static boolean solve(int[] arr){
        boolean[] v = new boolean[n];

        for(int i=0; i<n-1; i++){
            if(arr[i] != arr[i+1]){
                if(Math.abs(arr[i] - arr[i+1]) > 1) return false;
                if(arr[i] < arr[i+1]){
                    if(i-l+1 < 0) return false;
                    for(int j=i; j>=i-l+1; j--){
                        if(arr[i] != arr[j] || v[j]) return false;
                        v[j] = true;
                    }
                } else {
                    if(i+l >= n) return false;
                    for(int j=i+1; j<=i+l; j++){
                        if(arr[i+1] != arr[j] || v[j]) return false;
                        v[j] = true;
                    }
                }
            }
        }

        return true;
    }
}
