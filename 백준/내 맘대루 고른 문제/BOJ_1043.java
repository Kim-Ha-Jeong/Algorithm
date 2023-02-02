import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1043 {
    static int n,m;
    static int[] parent;
    static HashSet<Integer> know = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];

        for(int i=1; i<n+1; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for(int i=0; i<k; i++){
            int num = Integer.parseInt(st.nextToken());
            know.add(num);
        }

        ArrayList<Integer>[] party = new ArrayList[m];
        int ans = 0;

        for(int i=0; i<m; i++){
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            for(int j=0; j<k; j++){
                int b = Integer.parseInt(st.nextToken());
                party[i].add(b);
            }
        }

        for(int i=0; i<m; i++){
            int a = party[i].get(0);
            for(int j=1; j<party[i].size(); j++){
                int b = party[i].get(j);
                if(know.contains(a)){
                    know.add(b);
                } else if(know.contains(b)){
                    know.add(a);
                }
                union(a,b);
            }
        }

        for(int i=1; i<n+1; i++){
            if(know.contains(find(parent[i]))){
                know.add(i);
            }
        }

        for(int i=0; i<m; i++){
            boolean flag = true;
            for(int p: party[i]){
                if(know.contains(p)){
                    flag = false;
                    break;
                }
            }
            if(flag) ans++;
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(!know.contains(rootA) && know.contains(rootB)){
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        } else if(know.contains(rootA) && !know.contains(rootB)){

        } else {
            if(rootA > rootB){
                int tmp = rootA;
                rootA = rootB;
                rootB = tmp;
            }
        }

        parent[rootB] = rootA;
    }

    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
