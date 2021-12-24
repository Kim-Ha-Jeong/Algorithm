import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.LinkedList;

public class BOJ_10845 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        LinkedList<Integer> queue = new LinkedList<>();

        while (n-- > 0) {
            String str = br.readLine();
            if (str.contains("push")) {
                queue.add(Integer.parseInt(str.split(" ")[1]));
            } else if (str.equals("size")) {
                sb.append(queue.size()).append("\n");
            } else if (str.equals("empty")) {
                if (queue.size() == 0)
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            } else if (queue.size() == 0) {
                sb.append(-1).append("\n");
            } else if (str.equals("pop") || str.equals("front")) {
                sb.append(queue.get(0)).append("\n");
                if (str.equals("pop"))
                    queue.removeFirst();
            } else if (str.equals("back")) {
                sb.append(queue.get(queue.size() - 1)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
