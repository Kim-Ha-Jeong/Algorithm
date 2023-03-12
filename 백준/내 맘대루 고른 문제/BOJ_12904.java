import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_12904 {
    static String s,t;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        s = br.readLine();
        t = br.readLine();
        StringBuffer sb;

        int ans = 0;
        while(true){
            int idx = t.length() - 1;
            sb = new StringBuffer();
            sb.append(t.substring(0,idx));
            if(t.charAt(idx) == 'A'){
                t = sb.toString();
            } else {
                t = sb.reverse().toString();
            }

            if(t.length() == s.length()){
                if(t.equals(s)){
                    ans = 1;
                }
                break;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
