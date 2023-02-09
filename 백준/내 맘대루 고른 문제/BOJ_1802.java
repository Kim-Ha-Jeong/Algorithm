import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1802 {
    static boolean flag = true;
    static char[] ch;
    static int len;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        while(tc-->0){
            ch = br.readLine().toCharArray();
            len = ch.length;
            flag = true;

            solve(0,len-1);
            if(flag) sb.append("YES\n");
            else sb.append("NO\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void solve(int start, int end){
        if(!flag || start >= end) return;
        int mid = (start+end)/2;

        solve(start, mid-1);
        solve(mid+1, end);
        flag &= isRight(start, mid, end);
    }

    static boolean isRight(int start, int mid, int end){
        boolean flag = true;
        while(start < mid && end > mid){
            if(ch[start] == ch[end]){
                flag = false;
                break;
            } else {
                start++;
                end--;
            }
        }
        return flag;
    }
}
