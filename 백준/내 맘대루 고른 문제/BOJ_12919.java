import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_12919 {
    static String s,t;
    static int n;
    static int flag = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        s = br.readLine();
        t = br.readLine();

        StringBuffer sb = new StringBuffer();
        sb.append(t);
        dfs(sb, sb.length());


        bw.write("0");
        bw.flush();
        bw.close();
    }

    static void dfs(StringBuffer sb, int size){
        if(size == s.length()){
            if(s.equals(sb.toString())) {
                System.out.print("1");
                System.exit(0);
            }
            return;
        }

        if(sb.charAt(0) == 'B'){
            sb.deleteCharAt(0);
            sb.reverse();
            dfs(sb, size-1);
            sb.reverse();
            sb.insert(0,"B");
        }

        if(sb.charAt(size-1) == 'A'){
            sb.deleteCharAt(size-1);
            dfs(sb, size-1);
            sb.append("A");
        }
    }
}
