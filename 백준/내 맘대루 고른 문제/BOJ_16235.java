import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_16235 {
    static int n,m,k;
    static int[][] soil;
    static int[][] map;
    static Queue<Tree> tree;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        soil = new int[n][n];
        tree = new LinkedList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = 5;
                soil[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());

            tree.add(new Tree(x,y,z));
        }

        while(k-->0){
            springAndSummer();
            fall();
            winter();
        }

        bw.write(tree.size()+"");
        bw.flush();
        bw.close();
    }

    static void winter(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] += soil[i][j];
            }
        }
    }

    static void fall(){
        ArrayList<Tree> add = new ArrayList<>();
        int size = tree.size();

        while(size-- > 0){
            Tree t = tree.poll();
            if(t.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = t.x + dx[i];
                    int ny = t.y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    tree.add(new Tree(nx, ny, 1));
                }
            }

            add.add(t);
        }

        for(Tree t : add){
            tree.add(t);
        }
    }

    static void springAndSummer(){
        ArrayList<Tree> remove = new ArrayList<>();
        int size = tree.size();

        while(size-- > 0){
            Tree cur = tree.poll();

            if(map[cur.x][cur.y] < cur.age){
                remove.add(cur);
            } else {
                map[cur.x][cur.y] -= cur.age;
                tree.add(new Tree(cur.x, cur.y, cur.age+1));
            }
        }

        for(Tree t : remove){
            map[t.x][t.y] += (t.age/2);
        }
    }

    static class Tree {
        int age;
        int x;
        int y;

        Tree(int x, int y, int age){
            this.x =x;
            this.y = y;
            this.age =age;
        }
    }
}
