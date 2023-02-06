import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1244 {
    static boolean[] switches;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        switches = new boolean[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++){
            if(Integer.parseInt(st.nextToken()) == 0){
                switches[i] = false;
            } else {
                switches[i] = true;
            }
        }

        int m = Integer.parseInt(br.readLine());

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if(gender == 1){
                for(int i=number; i<n+1; i+= number){
                    switches[i] = !switches[i];
                }
            } else {
                int start = number-1;
                int end = number+1;
                while(start >=1 && end < n+1){
                    if(switches[start] == switches[end]){
                        start--;
                        end++;
                    } else {
                        break;
                    }
                }

                start += 1;

                for(int i=start; i<end; i++){
                    switches[i] = !switches[i];
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for(int i=1; i<n+1; i++){
            sb.append(switches[i] ? 1 : 0).append(" ");
            if(i % 20 == 0){
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
