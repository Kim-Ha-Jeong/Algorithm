import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;

public class BOJ_5052_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int tc = Integer.parseInt(br.readLine());

        while(tc-->0){
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];
            boolean flag = true;

            for(int i=0; i<n; i++){
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);
            for(int i=0; i<n-1; i++){
                if(arr[i+1].startsWith(arr[i])){
                    flag = false;
                    break;
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
