import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_25757 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int y = 1, f = 2, o = 3;

        int n = Integer.parseInt(st.nextToken());
        char c = st.nextToken().charAt(0);

        int mod = -1;
        switch (c){
            case 'Y':
                mod = y;
                break;
            case 'F':
                mod = f;
                break;
            case 'O':
                mod = o;
                break;
        }

        HashSet<String> set = new HashSet<>();
        for(int i=0; i<n; i++){
            set.add(br.readLine());
        }

        int ans = set.size() / mod;

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
