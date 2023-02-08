import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_5585 {
    static int[] coin = {500,100,50,10,5,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = 1000 - Integer.parseInt(br.readLine());
        int ans = 0;
        for(int i=0; i<coin.length; i++){
            if(n < coin[i]) continue;
            ans += (n / coin[i]);
            n %= coin[i];
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
