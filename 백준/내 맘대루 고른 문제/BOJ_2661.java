import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class BOJ_2661 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        backtracking("");

    }

    static void backtracking(String result){
        if(result.length() == n){
            System.out.println(result);
            System.exit(0);
        }

        for(int i=1; i<4; i++){
            if(isGood(result + i)){
                backtracking(result + i);
            }
        }
    }

    static boolean isGood(String s){
        int len = s.length()/2;

        for(int i=1; i < len+1; i++){
            String a = s.substring(s.length() - i);
            String b = s.substring(s.length() - 2*i, s.length()-i);

            if(a.equals(b)) return false;
        }

        return true;
    }
}
