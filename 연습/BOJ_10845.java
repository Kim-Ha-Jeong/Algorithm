import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class BOJ_10845 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        LinkedList<Integer> q = new LinkedList<>();

        while (n-- > 0) {
            String command = br.readLine();

            if (command.contains("push")) {
                int num = Integer.parseInt(command.split(" ")[1]);
                q.add(num);
            } else {
                if (command.equals("size")) {
                    sb.append(q.size());
                } else if (command.equals("empty")) {
                    if (q.size() == 0)
                        sb.append(1);
                    else
                        sb.append(0);
                } else if (q.size() == 0) {
                    sb.append(-1);
                } else if (command.equals("pop") || command.equals("front")) {
                    sb.append(q.peek());
                    if (command.equals("pop"))
                        q.poll();
                } else if (command.equals("back")) {
                    sb.append(q.get(q.size() - 1));
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}
