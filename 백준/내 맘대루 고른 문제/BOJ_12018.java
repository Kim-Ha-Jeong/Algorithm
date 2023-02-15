import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_12018 {
    static int n,m;
    static int[] limit;
    static ArrayList<Integer>[] people;
    static Node[] max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        limit = new int[n];
        max = new Node[n];
        people = new ArrayList[n];
        HashSet<Integer> ans = new HashSet<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            people[i] = new ArrayList();
            max[i] = new Node(i, 1);

            int k = Integer.parseInt(st.nextToken());
            limit[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<k; j++){
                people[i].add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(people[i], Collections.reverseOrder());
            if(k >= limit[i]) {
                int a = people[i].get(limit[i]-1);
                max[i] = new Node(i, a);
            }
        }

        Arrays.sort(max);

        for(int i=0; i<n; i++){
            Node now = max[i];
            if(m >= now.mile) {
                m -= now.mile;
                ans.add(now.idx);
            } else {
                break;
            }
        }

        bw.write(ans.size()+"");
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node>{
        int idx;
        int mile;

        Node(int idx, int mile){
            this.idx = idx;
            this.mile = mile;
        }

        @Override
        public int compareTo(Node o) {
            return this.mile - o.mile;
        }
    }
}
