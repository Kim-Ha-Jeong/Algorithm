import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_2831 {
    static int n;
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        ArrayList<Integer> smallB = new ArrayList<>();
        ArrayList<Integer> smallG = new ArrayList<>();
        ArrayList<Integer> bigB = new ArrayList<>();
        ArrayList<Integer> bigG = new ArrayList<>();

        String[] tmp = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(tmp[i]);
            if(num < 0) bigB.add(num*(-1));
            else smallB.add(num);
        }

        tmp = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(tmp[i]);
            if(num < 0) bigG.add(num*(-1));
            else smallG.add(num);
        }

        Collections.sort(bigB);
        Collections.sort(smallB);
        Collections.sort(bigG);
        Collections.sort(smallG);

        solve(0, 0, smallB, bigG);
        solve(0, 0, smallG, bigB);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void solve(int sIdx, int bIdx, ArrayList<Integer> small, ArrayList<Integer> big){

        while(sIdx < small.size() && bIdx < big.size()){
            int s = small.get(sIdx);
            int b = big.get(bIdx);

            if(b > s){
                ans++;
                sIdx++;
                bIdx++;
            } else {
                bIdx++;
            }
        }
    }
}
