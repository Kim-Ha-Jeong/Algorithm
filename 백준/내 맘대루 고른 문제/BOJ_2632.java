import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2632 {
    static int total;
    static int[] A, B;
    static int n,m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        total = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new int[n];
        B = new int[m];

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        int sum = 0;
        int ans = 0;
        for(int i=0; i<n; i++){
            int tmp = Integer.parseInt(br.readLine());
            sum += tmp;
            A[i] = tmp;
            if(sum >= total) {
                if(sum == total) ans++;
                continue;
            }
            a.add(sum);
        }

        sum = 0;
        for(int i=0; i<m; i++){
            int tmp = Integer.parseInt(br.readLine());
            sum += tmp;
            B[i] = tmp;
            if(sum >= total) {
                if(sum == total) ans++;
                continue;
            }
            b.add(sum);
        }

        for(int i=1; i<n; i++){
            int tmp = 0;
            for(int j=0; j<n-1; j++){
                tmp += A[(i+j) % n];
                if(tmp >= total) {
                    if(tmp == total) ans++;
                    break;
                }
                a.add(tmp);
            }
        }

        for(int i=1; i<m; i++){
            int tmp = 0;
            for(int j=0; j<m-1; j++){
                tmp += B[(i+j) % m];
                if(tmp >= total) {
                    if(tmp == total) ans++;
                    break;
                }
                b.add(tmp);
            }
        }

        Collections.sort(a);
        Collections.sort(b);
        int aIdx = 0;
        int bIdx = b.size()-1;

        while(aIdx < a.size() && bIdx >= 0){
            int eleA = a.get(aIdx);
            int eleB = b.get(bIdx);

            if(eleA + eleB == total) {
                    int aTmp = 0;
                    int bTmp = 0;
                    while (aIdx < a.size()) {
                        if (a.get(aIdx) == eleA) {
                            aTmp++;
                        } else {
                            break;
                        }
                        aIdx++;
                    }

                    while (bIdx >= 0) {
                        if (b.get(bIdx) == eleB) {
                            bTmp++;
                        } else {
                            break;
                        }
                        bIdx--;
                    }
                    ans += (aTmp * bTmp);

            } else {
                if(eleA + eleB > total){
                    bIdx--;
                } else {
                    aIdx++;
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }
}
