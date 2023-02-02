import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_3649 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        StringBuffer sb = new StringBuffer();
        while((line = br.readLine()) != null) {
            boolean flag = false;
            int x = Integer.parseInt(line) * 10000000;
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            int front = 0, back = n - 1;
            while (front < back) {
                int sum = arr[front] + arr[back];
                if (sum == x) {
                    flag = true;
                    sb.append("yes ").append(arr[front]).append(" ").append(arr[back]).append("\n");
                    break;
                } else if (sum < x) {
                    front++;
                } else {
                    back--;
                }
            }

            if (!flag) {
                sb.append("danger\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
