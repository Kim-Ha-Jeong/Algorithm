import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class BOJ_2164 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> card = new ArrayDeque<>();

        for(int i=1; i<n+1; i++){
            card.add(i);
        }

        while(card.size() > 1){
            card.removeFirst();
            int last = card.removeFirst();
            card.addLast(last);
        }

        bw.write(card.pop()+"");
        bw.flush();
        bw.close();
    }
}
