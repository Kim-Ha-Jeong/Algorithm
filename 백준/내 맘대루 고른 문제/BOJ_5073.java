import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5073 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        while(true){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0 && c == 0) break;
            int[] arr = {a,b,c};
            Arrays.sort(arr);

            if(arr[0] + arr[1] <= arr[2]) {
                sb.append("Invalid\n");
                continue;
            }

            if(a == b && b == c){
                sb.append("Equilateral\n");
            } else if(a == b || b == c || a == c){
                sb.append("Isosceles\n");
            } else {
                sb.append("Scalene\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
