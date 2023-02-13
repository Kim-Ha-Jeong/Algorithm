import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2885 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        int num = 1;

        while(true){
            if(num >= k) break;
            num *= 2;
        }

        StringBuffer sb = new StringBuffer();
        if(num == k){
            sb.append(k).append(" 0");
        } else {
            String str = Long.toBinaryString(k);
            int len = str.length();
            int ans = len;
            for(int i=len-1; i>=0; i--){
                if(str.charAt(i) == '1') break;
                ans--;
            }

            sb.append(num).append(" ").append(ans);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
