import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_1765 {
    static int n,m;
    static int[] parent;
    static ArrayList<Integer>[] con;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        con = new ArrayList[n+1];

        for(int i=1; i<n+1; i++){
            parent[i] = i;
            con[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(c == 'F'){
                join(a, b);
            } else {
                if(con[a].size() > 0){
                    for(int now : con[a]){
                        join(b, now);
                    }
                }
                if(con[b].size() > 0){
                    for(int now : con[b]){
                        join(a, now);
                    }
                }

                con[a].add(b);
                con[b].add(a);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i=1; i<n+1; i++){
            set.add(parent[i]);
        }

        bw.write(set.size()+"");
        bw.flush();
        bw.close();
    }

    static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void join(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;

        if(rootA > rootB){
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }

        parent[rootA] = rootB;
    }
}
