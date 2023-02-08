import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1515 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] ch = br.readLine().toCharArray();

        int num = 1;
        String str = String.valueOf(num);
        for(int i=0; i<ch.length; i++){
             while(true){
                 if(str.contains(ch[i]+"")){
                     int idx = str.indexOf(ch[i]);
                     if(idx + 1 < str.length()) str = str.substring(idx+1);
                     else {
                         if(i == ch.length-1) break;
                         num++;
                         str = String.valueOf(num);
                     }
                     break;
                 }
                 num++;
                 str = String.valueOf(num);
             }
        }


        bw.write((num)+"");
        bw.flush();
        bw.close();
    }
}
