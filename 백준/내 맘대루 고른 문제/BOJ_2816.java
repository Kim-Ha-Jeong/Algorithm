import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_2816 {
    static int n;
    static ArrayList<String> channel = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        int first = -1, second = -1;
        for(int i=0; i<n; i++){
            String str = br.readLine();
            channel.add(str);
            if(str.equals("KBS1")) first = i;
            if(str.equals("KBS2")) second = i;
        }

        if(first > second){
            second++;
        }

        StringBuffer start = new StringBuffer();
        StringBuffer end = new StringBuffer();
        for(int i=0; i<first; i++){
            start.append("1");
            end.append("4");
        }

        StringBuffer ans = new StringBuffer();
        ans.append(start.toString()).append(end.toString());

        if(second != 1) {
            start = new StringBuffer();
            end = new StringBuffer();
            for (int i = 0; i < second; i++) {
                start.append("1");
            }

            while (second-- > 1) {
                end.append("4");
            }
            ans.append(start.toString()).append(end.toString());
        }

        bw.write(ans.toString());
        bw.flush();
        bw.close();

    }
}
