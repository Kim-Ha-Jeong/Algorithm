import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_10162 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = {300,60,10};

        int[] cnt = new int[3];

        for(int i=0; i<3; i++){
            cnt[i] = n / arr[i];
            n %= arr[i];
        }

        String ans;
        if(n != 0) ans = "-1";
        else ans = cnt[0]+" "+cnt[1]+" "+cnt[2];
        bw.write(ans);
        bw.flush();
        bw.close();
    }
}
