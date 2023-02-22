import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_17779 {
    static int[][] people;
    static int n;
    static int ans = Integer.MAX_VALUE;
    static int total = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        people = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                people[i][j] = Integer.parseInt(st.nextToken());
                total += people[i][j];
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                for(int d1=1; ;d1++){
                    if(i+d1 >= n) break;
                    for(int d2=1; ;d2++){
                        if(i+d1+d2 >= n) break;
                        setapart(i,j,d1,d2);
                    }
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static boolean setapart(int x, int y, int d1, int d2){
        int[][] map = new int[n][n];


        for(int i=0; i<=d1; i++){
            int nx = x + i;
            int ny = y - i;
            int nnx = x + d2 + i;
            int nny = y + d2 - i;

            if(nx < 0 || nx >= n || ny < 0 || ny >= n) return false;
            if(nnx < 0 || nnx >= n || nny < 0 || nny >= n) return false;
            map[nx][ny] = map[nnx][nny] = 5;
        }

        for(int i=0; i<=d2; i++){
            int nx = x + i;
            int ny = y + i;
            int nnx = x + d1 + i;
            int nny = y - d1 + i;

            if(nx < 0 || nx >= n || ny < 0 || ny >= n) return false;
            if(nnx < 0 || nnx >= n || nny < 0 || nny >= n) return false;
            map[nx][ny] = map[nnx][nny] = 5;
        }

        int result = setNumber(x,y,d1,d2,map);
        if(ans > result){
            ans = result;
        }
        return true;
    }

    static int setNumber(int x, int y, int d1, int d2, int[][] map){
        ArrayList<Integer> tmp = new ArrayList<>();
        int copy = total;

        int sum = 0;
        for(int i=0; i<x+d1; i++){
            for(int j=0; j<y+1; j++){
                if(map[i][j] == 5) break;
                sum += people[i][j];
                map[i][j] = 1;
            }
        }
        copy -= sum;
        tmp.add(sum);

        sum = 0;
        int row = 0;
        for(int i=0; i<=x+d2; i++){
            row = 0;
            for(int j=y+1; j<n; j++){
                if(map[i][j] == 5) {
                    row = 0;
                    continue;
                }
                map[i][j] = 2;
                row += people[i][j];
            }
            sum += row;
        }
        copy -= sum;
        tmp.add(sum);

        sum = 0;
        for(int i=x+d1; i<n; i++){
            for(int j=0; j<y-d1+d2; j++){
                if(map[i][j] == 5) break;
                sum += people[i][j];
                map[i][j] = 3;
            }
        }
        copy -= sum;
        tmp.add(sum);

        row = 0; sum = 0;
        for(int i=x+d2+1; i<n; i++){
            row = 0;
            for(int j=y-d1+d2; j<n; j++){
                if(map[i][j] == 5) {
                    row = 0;
                    continue;
                }
                row += people[i][j];
                map[i][j] = 4;
            }
            sum += row;
        }
        copy -= sum;
        tmp.add(sum);

        tmp.add(copy);

        Collections.sort(tmp);

        return Math.abs(tmp.get(0) - tmp.get(tmp.size()-1));
    }
}
