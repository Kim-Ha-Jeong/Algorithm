import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14888 {
    static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] op, arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        op = new int[4];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        int[] copy = new int[4];
        System.arraycopy(op,0,copy,0,4);
        dfs(1,arr[0],copy);

        StringBuffer sb = new StringBuffer();
        sb.append(max).append("\n").append(min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int depth, int result, int[] op){
        if(depth == n){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for(int i=0; i<4; i++){
            if(op[i] == 0) continue;
            int tmp = result;
            if(i == 0){
                tmp += arr[depth];
            } else if(i == 1){
                tmp -= arr[depth];
            } else if(i == 2){
                tmp *= arr[depth];
            } else {
                if(tmp < 0){
                    tmp *= (-1);
                    tmp /= arr[depth];
                    tmp *= (-1);
                } else {
                    tmp /= arr[depth];
                }
            }
            op[i]--;
            dfs(depth+1, tmp, op);
            op[i]++;
        }
    }

}
