import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_21921 {
    static int n,x;
    static int[] visitor;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        visitor = new int[n];

        st = new StringTokenizer(br.readLine());
        int[] sum = new int[n];

        for(int i=0; i<n; i++){
            visitor[i] = Integer.parseInt(st.nextToken());
            if(i == 0) sum[i] = visitor[i];
            else sum[i] = sum[i-1] + visitor[i];
        }

        int max = sum[x-1];
        int cnt = 1;

        for(int i=x; i<n; i++){
            int tmp = sum[i] - sum[i-x];
            if(max < tmp){
                max = tmp;
                cnt = 1;
            } else if(max == tmp){
                cnt++;
            }
        }

        StringBuffer sb = new StringBuffer();
        if(max == 0){
            sb.append("SAD");
        } else {
            sb.append(max).append("\n").append(cnt);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
