import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10159 {
    static boolean[][] weight;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        weight = new boolean[n+1][n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            weight[a][b] = true;
        }

        boolean[][][] v = new boolean[n+1][n+1][n+1];

        for(int mid=1; mid<n+1; mid++){
            for(int start=1; start<n+1; start++){
                for(int end=1; end<n+1; end++){
                    if(weight[start][mid] && weight[mid][end]){
                        weight[start][end] = true;
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for(int i=1; i<n+1; i++){
            int cnt = 0;
            for(int j=1; j<n+1; j++){
                if(i == j) continue;
                if(!weight[i][j] && !weight[j][i]){
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
