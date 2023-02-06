import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_20437 {

    static char[] ch;
    static int len, k;
    static ArrayList<Integer>[] alpha;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        while(tc-- > 0){
            ch = br.readLine().toCharArray();
            len = ch.length;
            k = Integer.parseInt(br.readLine());
            alpha = new ArrayList[26];

            for(int i=0; i<26; i++){
                alpha[i] = new ArrayList<>();
            }

            int[] ans = getLong();
            if(ans[0] == -1){
                sb.append("-1\n");
            } else {
                sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[] getLong(){
        for(int i=0; i<len; i++){
            alpha[ch[i] -'a'].add(i);
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<26; i++){
            if(alpha[i].size() < k) continue;
            for(int j=0; j<alpha[i].size(); j++){
                if(j+k-1 >= alpha[i].size()) break;
                int len = Math.abs(alpha[i].get(j) - alpha[i].get(j + k - 1))+1;
                max = Math.max(len, max);
                min = Math.min(len, min);
            }
        }

        return new int[]{min == Integer.MAX_VALUE ? -1 : min, max == Integer.MIN_VALUE ? -1 : max};
    }
}
