import java.io.*;

public class BOJ_11723 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        int s = 0;

        while (n-- > 0) {
            String command = br.readLine();

            if (command.equals("all")) {
                s = (1 << 21) - 1;
            } else if (command.equals("empty")) {
                s = 0;
            } else {
                String[] piece = command.split(" ");
                String com = piece[0];
                int x = Integer.parseInt(piece[1]);

                if (com.equals("add")) {
                    s |= (1 << x);
                } else if (com.equals("remove")) {
                    s &= ~(1 << x);
                } else if (com.equals("check")) {
                    if ((s & (1 << x)) != 0)
                        sb.append(1);
                    else
                        sb.append(0);
                    sb.append("\n");
                } else if (com.equals("toggle")) {
                    s ^= (1 << x);
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
