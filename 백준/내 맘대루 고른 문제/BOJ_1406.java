import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_1406 {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] c = br.readLine().toCharArray();
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for(int i=0; i<c.length; i++){
            left.add(c[i]);
        }

        int m = Integer.parseInt(br.readLine());

        while(m-->0){
            String command = br.readLine();

            if(command.equals("L")){
                if(left.size() == 0) continue;
                right.push(left.pop());
            } else if(command.equals("D")){
                if(right.size() == 0) continue;
                left.push(right.pop());
            } else if(command.equals("B")){
                if(left.size() == 0) continue;
                left.pop();
            } else {
                char tmp = command.split(" ")[1].charAt(0);
                left.push(tmp);
            }
        }

        StringBuffer sb = new StringBuffer();
        while(!left.isEmpty()){
            right.push(left.pop());
        }

        while(!right.isEmpty()){
            sb.append(right.pop());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
