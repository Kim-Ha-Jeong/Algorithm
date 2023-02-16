import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_19637 {
    static int n,m;
    static HashSet<Integer> set;
    static ArrayList<Level> level = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        set = new HashSet<>();
        int max = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if(set.contains(num)) continue;
            set.add(num);
            level.add(new Level(str, num));
            max = Math.max(max, num);
        }

        StringBuffer sb = new StringBuffer();
        while(m-->0){
            int power = Integer.parseInt(br.readLine());
            int left = 0;
            int right = level.size();
            String str = "";

            while(left <= right){
                int mid = (left + right)/2;

                Level cur = level.get(mid);

                if(power <= cur.num){
                    str = cur.str;
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }

            sb.append(str).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Level {
        String str;
        int num;

        Level(String str, int num){
            this.str = str;
            this.num = num;
        }
    }
}
