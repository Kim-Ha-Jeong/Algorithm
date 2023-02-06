import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_22233 {
    static int n,m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        HashSet<String> memo = new HashSet<>();

        for(int i=0; i<n; i++){
            memo.add(br.readLine());
        }

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<m; i++){
            String[] keyword = br.readLine().split(",");

            for(int j=0; j<keyword.length; j++){
                if(memo.contains(keyword[j])) memo.remove(keyword[j]);
            }
            sb.append(memo.size()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
