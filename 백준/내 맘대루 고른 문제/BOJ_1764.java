import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1764 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();

        for(int i=0; i<n; i++){
            set.add(br.readLine());
        }

        ArrayList<String> ans = new ArrayList<>();
        for(int i=0; i<m; i++){
            String str = br.readLine();
            if(set.contains(str)){
                ans.add(str);
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(ans.size()).append("\n");

        Collections.sort(ans);
        for(int i=0; i<ans.size(); i++){
            sb.append(ans.get(i)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
