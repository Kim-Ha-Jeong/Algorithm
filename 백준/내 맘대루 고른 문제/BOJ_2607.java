import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

public class BOJ_2607 {
    static int n;
    static String first;
    static int fLen;
    static HashMap<Character, Integer> map = new HashMap<>();
    static String[] word;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        first = br.readLine();
        fLen = first.length();

        for(int i=0; i<first.length(); i++){
            char c = first.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            } else {
                map.put(c,1);
            }
        }

        word = new String[n-1];
        for(int i=0; i<n-1; i++){
            word[i] = br.readLine();
        }

        int ans = 0;
        for(int i=0; i<n-1; i++){
            String str = word[i];

            int len = str.length();
            StringBuffer sb = new StringBuffer();
            sb.append(str);

            int cnt = 0;
            for(char ch : map.keySet()){
                int val = map.get(ch);

                for(int j=0; j<val; j++){
                    int idx = sb.indexOf(ch+"");
                    if(idx == -1) cnt++;
                    else {
                        sb.deleteCharAt(idx);
                    }
                }
            }

            if(len > fLen){
                if(sb.length() == 1 && cnt == 0) ans++;
            } else if(len == fLen){
                if(sb.length() == 0 && cnt == 0) ans++;
                if(sb.length() == 1 && cnt == 1) ans++;
            } else {
                if(sb.length() == 0 && cnt == 1) ans++;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
