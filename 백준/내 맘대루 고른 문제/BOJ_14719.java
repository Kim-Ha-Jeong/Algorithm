import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14719 {
    static int h,w;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        arr = new int[w];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<w; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for(int i=1; i<w-1; i++){
            int front = 0, back = 0;

            for(int j=0; j<i; j++){
                front = Math.max(front, arr[j]);
            }

            for(int j=i+1; j<w; j++){
                back = Math.max(back, arr[j]);
            }

            if(front > arr[i] && back > arr[i]) ans += (Math.min(front, back) - arr[i]);
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
