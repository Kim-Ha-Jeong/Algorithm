import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Stack;
import java.util.ArrayList;

public class BOJ_2435 {
    static Stack<Integer> stack;
    static int MAX = (int) 1e9;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        ArrayList<String> command;

        while (true) {
            command = new ArrayList<>();

            boolean quit = true;
            while (true) {
                String s = br.readLine();

                if (s.equals("QUIT")) {
                    quit = false;
                    break;
                }

                if (s.equals("END"))
                    break;
                command.add(s);
            }

            if (!quit)
                break;

            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(br.readLine());
                stack = new Stack<>();
                stack.push(v);
                boolean flag = true;
                for (String str : command) {
                    if (!solve(str)) {
                        flag = false;
                        break;
                    }
                }

                if (stack.size() != 1 || !flag)
                    sb.append("ERROR");
                else
                    sb.append(stack.pop());
                sb.append("\n");
            }

            sb.append("\n");
            br.readLine();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean solve(String s) {
        if (s.contains("NUM")) {
            stack.push(Integer.parseInt(s.split(" ")[1]));
        } else {
            if (stack.isEmpty())
                return false;
            if (s.equals("POP")) {
                stack.pop();
            } else if (s.equals("INV")) {
                stack.push(stack.pop() * (-1));
            } else if (s.equals("DUP")) {
                stack.push(stack.peek());
            } else {
                if (stack.size() < 2)
                    return false;

                int a = stack.pop();
                int b = stack.pop();

                if (s.equals("SWP")) {
                    stack.push(a);
                    stack.push(b);
                } else if (s.equals("ADD")) {
                    long tmp = (long) a + (long) b;
                    if (Math.abs(tmp) > MAX)
                        return false;
                    stack.push(a + b);
                } else if (s.equals("SUB")) {
                    long tmp = (long) b - (long) a;
                    if (Math.abs(tmp) > MAX)
                        return false;
                    stack.push(b - a);
                } else if (s.equals("MUL")) {
                    long tmp = (long) a * (long) b;
                    if (Math.abs(tmp) > MAX)
                        return false;
                    stack.push(a * b);
                } else {
                    int tmpA = Math.abs(a);
                    int tmpB = Math.abs(b);

                    if (a == 0)
                        return false;

                    if (s.equals("DIV")) {
                        int res = tmpB / tmpA;
                        if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
                            res *= (-1);
                        }
                        stack.push(res);
                    } else if (s.equals("MOD")) {
                        int res = tmpB % tmpA;

                        if (b < 0)
                            res *= (-1);
                        stack.push(res);
                    }
                }
            }
        }

        return true;
    }
}