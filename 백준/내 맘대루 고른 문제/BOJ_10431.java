import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10431 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();
        int tc = Integer.parseInt(br.readLine());

        while(tc-->0){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[20];
            int ans = 0;

            for(int i=0; i<20; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<20; i++){
                int idx = -1;
                for(int j=i-1; j>=0; j--){
                    if(arr[i] < arr[j]){
                        idx = j;
                    }
                }

                if(idx != -1){
                    int tmp = arr[i];
                    for(int j=i-1; j>=idx; j--){
                        arr[j+1] = arr[j];
                        ans++;
                    }
                    arr[idx] = tmp;
                }
            }
            sb.append(n).append(" ").append(ans).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
