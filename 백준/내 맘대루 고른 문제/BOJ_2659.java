import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2659 {
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        int num = Integer.MAX_VALUE;

        for(int i=0; i<4; i++){
            StringBuffer tmp = new StringBuffer();
            tmp.append(str[i]).append(str[(i+1)%4]).append(str[(i+2)%4]).append(str[(i+3)%4]);
            num = Math.min(num,Integer.parseInt(tmp.toString()));
        }

        solve(num);
        ans++;

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void solve(int end){
        int start = 1111;
        for(int i=start; i<end; i++){
            String str = String.valueOf(i);
            if(str.contains("0")) continue;
            boolean flag = makeNum(i);
            if(flag) ans++;
        }
    }

    static boolean makeNum(int origin){
        String number = String.valueOf(origin);
        String[] num = number.split("");

        for(int i=1; i<4; i++){
            StringBuffer tmp = new StringBuffer();
            tmp.append(num[i]).append(num[(i+1)%4]).append(num[(i+2)%4]).append(num[(i+3)%4]);
            int now = Integer.parseInt(tmp.toString());
            if(now < origin) return false;
        }

        return true;
    }
}
