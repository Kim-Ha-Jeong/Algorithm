import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuffer sb = new StringBuffer();
        Stack<Top> stack = new Stack<>();

        for(int i=1; i<n+1; i++){
            int now = Integer.parseInt(st.nextToken());

            if(stack.isEmpty()){
                sb.append("0 ");
                stack.push(new Top(i, now));
            } else {
                while(true){
                    if(stack.isEmpty()){
                        sb.append("0 ");
                        stack.push(new Top(i, now));
                        break;
                    }

                    Top top = stack.peek();

                    if(top.height > now){
                        sb.append(top.idx).append(" ");
                        stack.push(new Top(i, now));
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Top {
        int idx;
        int height;

        Top(int idx, int height){
            this.idx = idx;
            this.height = height;
        }
    }
}
