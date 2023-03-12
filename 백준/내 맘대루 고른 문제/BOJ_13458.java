import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_13458 {
    static int n,b,c;
    static int[] people;
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());

        people = new int[n];
        for(int i=0; i<n; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long ans = 0;
        for(int i=0; i<n; i++){
            people[i] -= b;
            ans++;
            if(people[i] > 0){
                ans += Math.ceil(people[i] / (double)c);
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}

