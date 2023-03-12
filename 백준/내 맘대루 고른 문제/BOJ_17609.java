import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_17609 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        while(tc-- > 0){
           char[] ch = br.readLine().toCharArray();

           int start = 0;
           int end = ch.length-1;
           int ans = 0;

           while(start < end){
               if(ch[start] == ch[end]){
                   start++;
                   end--;
               } else {
                   if(ch[start+1] == ch[end] || ch[start] == ch[end-1]){
                       boolean flag = false;
                       if(ch[start+1] == ch[end]){
                           int sIdx = start+2;
                           int eIdx = end-1;
                           boolean none = false;
                           while(sIdx < eIdx){
                               if(ch[sIdx] != ch[eIdx]){
                                   none = true;
                                   break;
                               }
                               sIdx++;
                               eIdx--;
                           }
                           if(!none) flag = true;
                       }
                       if(ch[start] == ch[end-1]){
                           int sIdx = start+1;
                           int eIdx = end-2;
                           boolean none = false;
                           while(sIdx < eIdx){
                               if(ch[sIdx] != ch[eIdx]){
                                   none = true;
                                   break;
                               }
                               sIdx++;
                               eIdx--;
                           }
                           if(!none) flag = true;
                       }

                       if(flag){
                           ans = 1;
                       } else {
                           ans = 2;
                       }
                       break;
                   } else {
                       ans = 2;
                       break;
                   }
               }
           }

           sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
