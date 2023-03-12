import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686 {
    static int n,m, ans = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<Node> chicken = new ArrayList<>();
    static ArrayList<Node> house = new ArrayList<Node>();
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) house.add(new Node(i,j));
                else if(map[i][j] == 2) chicken.add(new Node(i,j));
            }
        }

        int[] arr = new int[m];
        combination(0,0, arr);

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static void combination(int depth, int idx, int[] arr){
        if(depth == m){
            ans = Math.min(ans, cal(arr));
            return;
        }

        for(int i=idx; i<chicken.size(); i++){
            arr[depth] = i;
            combination(depth+1, i+1, arr);
        }
    }

    static int cal(int[] choosen){
        int sum = 0;
        for(Node cur : house){
            int dist = Integer.MAX_VALUE;

            for(int i=0; i<m; i++){
                Node ch = chicken.get(choosen[i]);
                int nDist = Math.abs(ch.x - cur.x) + Math.abs(ch.y - cur.y);
                dist = Math.min(dist, nDist);
            }

            sum += dist;
        }

        return sum;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
