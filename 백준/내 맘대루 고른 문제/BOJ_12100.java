import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_12100 {
    static int n;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(-1,0, map);

        StringBuffer sb = new StringBuffer();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int prev, int depth, int[][] map){

        findMax(map);
        if(depth == 5){
            return;
        }
        for(int i=0; i<4; i++){
            int[][] tmp = copy(map);
            if(i == 0){
                moveRight(tmp);
            } else if(i == 1){
                moveLeft(tmp);
            } else if(i == 2){
                moveDown(tmp);
            } else {
                moveUp(tmp);
            }

//            if(depth == 0 && i == 0){
//                for(int x=0; x<n; x++){
//                    for(int y=0; y<n; y++){
//                        System.out.print(map[x][y]+" ");
//                    }
//                    System.out.println();
//                }
//            }

            dfs(i,depth+1, tmp);
        }
    }

    static void findMax(int[][] map){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(ans < map[i][j]){
                    ans = map[i][j];
                }
            }
        }
    }

    static void moveDown(int[][] map){
        for(int j=0; j<n; j++){
            for(int i=n-1; i>=0; i--){
                if(map[i][j] == 0) continue;
                int tmp = map[i][j];
                for(int k=i+1; k<n; k++){
                    if(map[k][j] != 0 && map[k][j] != tmp){
                        map[k-1][j] = tmp;
                        if(k-1 != i) map[i][j] = 0;
                        break;
                    } else if(map[k][j] == tmp){
                        if(k == n-1){
                            map[k][j] += tmp;
                            map[i][j] = 0;
                        } else {
                            tmp *= 2;
                            map[i][j] = map[k][j] = 0;
                        }
                    } else {
                        if(k == n-1){
                            map[k][j] = tmp;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    static void moveUp(int[][] map){
        for(int j=0; j<n; j++){
            for(int i=0; i<n; i++){
                if(map[i][j] == 0) continue;
                int tmp = map[i][j];
                for(int k=i-1; k>=0; k--){
                    if(map[k][j] != 0 && map[k][j] != tmp){
                        map[k+1][j] = tmp;
                        if(k+1 == i) map[i][j] = 0;
                        break;
                    } else if(map[k][j] == tmp){
                        if(k == 0){
                            map[k][j] += tmp;
                            map[i][j] = 0;
                        } else {
                            tmp *= 2;
                            map[k][j] = map[i][j] = 0;
                        }
                    } else {
                        if(k == 0){
                            map[k][j] = tmp;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    static int[][] copy(int[][] map){
        int[][] tmp = new int[n][n];
        for(int i=0; i<n; i++){
            System.arraycopy(map[i],0,tmp[i],0,n);
        }

        return tmp;
    }

    static void moveLeft(int[][] map){
        for(int i=0; i<n; i++){
               for(int j=0; j<n; j++){
                if(map[i][j] == 0) continue;
                int tmp = map[i][j];
                for(int k=j-1; k>=0; k--){
                    if(map[i][k] != 0 && map[i][k] != tmp){
                        map[i][k+1] = tmp;
                        if(k+1 != j) map[i][j] = 0;
                        break;
                    } else if(map[i][k] == tmp){
                        if(k == 0){
                            map[i][k] += tmp;
                            map[i][j] = 0;
                        } else {
                            tmp *= 2;
                            map[i][j] = map[i][k] = 0;
                        }
                    } else {
                        if(k == 0){
                            map[i][k] = tmp;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    static void moveRight(int[][] map){
        for(int i=0; i<n; i++){
            for(int j=n-1; j>=0; j--){
                if(map[i][j] == 0) continue;
                int tmp = map[i][j];
                for(int k=j+1; k<n; k++){
                    if(map[i][k] != 0 && map[i][k] != tmp){
                        map[i][k-1] = tmp;
                        if(k-1 != j) map[i][j] = 0;
                        break;
                    } else if(map[i][k] == tmp){
                        if(k == n-1){
                            map[i][k] += tmp;
                            map[i][j] = 0;
                        } else {
                            tmp *= 2;
                            map[i][j] = map[i][k] = 0;
                        }
                    } else {
                        if(k == n-1){
                            map[i][k] = tmp;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
    }
}
