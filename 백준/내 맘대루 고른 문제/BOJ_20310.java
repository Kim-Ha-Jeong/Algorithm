import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_20310 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();

        int zero = 0, one = 0;
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i) == '0') zero++;
            else one++;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(word);

        zero/=2;
        one/=2;

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == '1'){
                list.add(i);
                one--;
            }
            if(one == 0) break;
        }

        for(int i=sb.length()-1; i>=0; i--){
            if(sb.charAt(i) == '0'){
                list.add(i);
                zero--;
            }
            if(zero == 0) break;
        }

        Collections.sort(list, Collections.reverseOrder());

        for(int idx : list){
            sb.deleteCharAt(idx);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
