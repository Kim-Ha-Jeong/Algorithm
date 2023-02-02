import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1747 {
    static boolean[] prime;
    static int max = 1100000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        prime = new boolean[max+1];
        Arrays.fill(prime, true);
        prime[1] = false;
        isPrime();

        int ans = 0;
        for(int i=n; i<max+1; i++){
            if(prime[i]){
                if(palindrome(i)){
                    ans = i;
                    break;
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static boolean palindrome(int num){
        char[] c = String.valueOf(num).toCharArray();

        int len = c.length;
        for(int i=0; i<len; i++){
            if(c[i] != c[len - 1 - i]) return false;
        }

        return true;
    }

    static void isPrime(){
        for(int i=2; i<Math.sqrt(max)+1; i++){
            for(int j=i*i; j<max+1; j+=i){
                prime[j] = false;
            }
        }
    }
}
