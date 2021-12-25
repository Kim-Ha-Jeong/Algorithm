import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.HashSet;

public class BOJ_11723 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();

        while (n-- > 0) {
            String str = br.readLine();

            if (str.equals("all")) {
                for (int i = 0; i < 21; i++) {
                    set.add(i);
                }
            } else if (str.equals("empty")) {
                set.clear();
            } else {
                int num = Integer.parseInt(str.split(" ")[1]);
                String s = str.split(" ")[0];

                if (s.equals("add")) {
                    set.add(num);
                } else if (s.equals("check")) {
                    if (set.contains(num))
                        sb.append(1);
                    else
                        sb.append(0);
                    sb.append("\n");
                } else if (s.equals("toggle")) {
                    if (set.contains(num))
                        set.remove(num);
                    else
                        set.add(num);
                } else if (s.equals("remove")) {
                    if (set.contains(num))
                        set.remove(num);
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}
