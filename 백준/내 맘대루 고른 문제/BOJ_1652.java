import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1652 {
    static int n;
    static String[] row, col;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        row = new String[n];
        col = new String[n];
        Arrays.fill(col, "");

        for(int i=0; i<n; i++){
            row[i] = br.readLine();
            char[] ch = row[i].toCharArray();
            for(int j=0; j<n; j++){
                col[j] += (ch[j]+"");
            }
        }

        int x = 0, y =0;
        for(int i=0; i<n; i++){
            String[] tmp = row[i].split("X");
            for(int j=0; j<tmp.length; j++){
                if(tmp[j].contains("..")) x++;
            }
            tmp = col[i].split("X");
            for(int j=0; j<tmp.length; j++){
                if(tmp[j].contains("..")) y++;
            }
        }

        bw.write(x+" "+y);
        bw.flush();
        bw.close();
    }
}
