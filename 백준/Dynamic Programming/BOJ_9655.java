import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BOJ_9655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int ret = n / 3 + n % 3;

        if (ret % 2 == 0)
            sb.append("CY");
        else
            sb.append("SK");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
