import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_7490 {
    static int n;
    static int[] arr;
    static char[] op = {'+', '-', ' '};
    static ArrayList<String> ans = new ArrayList<>();
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int tc = Integer.parseInt(br.readLine());
        
        while(tc-->0){
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            ans = new ArrayList<>();

            for(int i=1; i<n+1; i++){
                arr[i-1] = i;
            }
            
            dfs(0,"");
            Collections.sort(ans);

            for(String tmp : ans){
                sb.append(tmp).append("\n");
            }

            if(tc != 0) sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static void dfs(int depth,String str){
        if(depth == n-1){
            str += arr[n-1];
            int num = calc(str.toCharArray());
            if(num == 0) {
                ans.add(str);
            }
            return;
        }

        for(int i=0; i<3; i++) {
            dfs(depth + 1, str + arr[depth] + op[i]);
        }
    }
    
    static int calc(char[] c){
        int len = c.length;
        int result = 0;
        int idx = 0;

        while(idx < len){
            if(c[idx] >= '0' && c[idx] <= '9'){
                String tmp = "";
                while(idx < len && ((c[idx] >= '0' && c[idx] <= '9') || c[idx] == ' ')){
                    if(c[idx] >= '0' && c[idx] <= '9') tmp += c[idx];
                    idx++;
                }
                result = Integer.parseInt(tmp);
            } else {
                int now = idx;

                idx++;
                String tmp = "";
                while(idx < len && ((c[idx] >= '0' && c[idx] <= '9') || c[idx] == ' ')){
                    if(c[idx] >= '0' && c[idx] <= '9') tmp += c[idx];
                    idx++;
                }
                int number = Integer.parseInt(tmp);

                if(c[now] == '+'){
                    result += number;
                } else if(c[now] == '-'){
                    result -= number;
                }
            }
        }

        return result;
    }
}
