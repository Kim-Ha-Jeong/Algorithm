import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class BOJ_5052 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        while(tc-->0){
            int n = Integer.parseInt(br.readLine());
            HashSet<String> set = new HashSet<>();
            String[] arr = new String[n];
            boolean flag = true;

            for(int i=0; i<n; i++){
                String tmp = br.readLine();
                arr[i] = tmp;
                if(set.contains(tmp)){
                    flag = false;
                    break;
                } else {
                    set.add(tmp);
                }
            }

            if(flag) {
                for (int i = 0; i < n; i++) {
                    if(!flag) break;
                    for(int j=1; j<arr[i].length(); j++){
                        String tmp = arr[i].substring(0,j);
                        if(set.contains(tmp)){
                            flag = false;
                            break;
                        }
                    }
                }
            }

            if(!flag) sb.append("NO\n");
            else sb.append("YES\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
