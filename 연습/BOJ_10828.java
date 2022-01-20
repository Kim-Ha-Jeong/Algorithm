import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_10828 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        while (n-- > 0) {
            String command = br.readLine();

            if (command.contains("push")) {
                int num = Integer.parseInt(command.split(" ")[1]);
                stack.push(num);
            } else {
                if (command.equals("size")) {
                    sb.append(stack.size());
                } else if (command.equals("pop") || command.equals("top")) {
                    if (stack.size() == 0)
                        sb.append(-1);
                    else {
                        sb.append(stack.peek());
                        if (command.equals("pop"))
                            stack.pop();
                    }
                } else if (command.equals("empty")) {
                    if (stack.size() == 0)
                        sb.append(1);
                    else
                        sb.append(0);
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
