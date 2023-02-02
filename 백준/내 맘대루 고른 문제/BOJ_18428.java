import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_18428 {
    static int n;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static char[][] map;
    static ArrayList<Node> ex = new ArrayList<>();
    static ArrayList<Node> teacher = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'X') ex.add(new Node(i,j));
                else if(map[i][j] == 'T') teacher.add(new Node(i,j));
            }
        }

        int[] tmp = new int[3];
        combination(0, 0, tmp);

        System.out.println("NO");
    }

    static void combination(int depth, int idx, int[] order){
        if(depth == 3){
            char[][] tmp = copy(order);
            boolean flag = true;
            for(Node t : teacher){
                flag &= avoid(tmp, t.x, t.y);
                if(!flag) break;
            }
            if(flag){
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        for(int i=0; i<ex.size(); i++){
            order[depth] = i;
            combination(depth+1, i+1, order);
        }
    }

    static char[][] copy(int[] order){
        char[][] tmp = new char[n][n];

        for(int i=0; i<n; i++){
            System.arraycopy(map[i], 0, tmp[i], 0, n);
        }

        for(int i=0; i<3; i++){
            Node now = ex.get(order[i]);
            tmp[now.x][now.y] = 'O';
        }

        return tmp;
    }

    static boolean avoid(char[][] map, int x, int y){
        for(int d=0; d<4; d++){
            for(int dist=1; dist<n; dist++){
                int nx = x + dx[d]*dist;
                int ny = y + dy[d]*dist;

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;
                if(map[nx][ny] == 'O') break;
                if(map[nx][ny] == 'S') return false;
            }
        }
        return true;
    }

    static class Node{
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
