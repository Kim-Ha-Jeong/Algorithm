import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_19941 {
    static int n,k;
    static char[] ch;
    static boolean[] v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        v = new boolean[n];

        ch = br.readLine().toCharArray();

        int ans = 0;

        for(int i=0; i<n; i++){
            if(ch[i] == 'H') continue;
            int start = Math.max(i-k,0);
            int end = Math.min(i+k,n-1);
            for(int j=start; j<=end; j++){
                if(ch[j] == 'H' && !v[j]){
                    v[j] = true;
                    ans++;
                    break;
                }
            }

        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
