import java.util.Arrays;
class Graph_14 {
    public int solution(int n, int[][] results) {
        int INF = 100001; int answer = 0; int[][] fw = new int[n+1][n+1]; 
        for(int[] arr : fw) { 
            Arrays.fill(arr, INF); 
        } 
        
        for(int[] arr : results) { 
            fw[arr[0]][arr[1]] = 1; 
        } 
        
        for(int k = 1; k < fw.length; k++) { 
            for(int i = 1; i < fw.length; i++) {
                for(int j = 1; j < fw.length; j++){ 
                    if(fw[i][j] > fw[i][k] + fw[k][j]) { 
                        fw[i][j] = fw[i][k] + fw[k][j]; 
                    } 
                } 
            } 
        } 
        
        for(int i = 1; i < fw.length; i++) { 
            boolean flag = true; 
            for(int j = 1; j < fw[i].length; j++){ 
                if(i == j) 
                    continue; 
                if(fw[i][j] == INF && fw[j][i] == INF) { 
                    flag = false; break; } 
            } 
            if(flag) answer++;  
        } 
        return answer;

    }
}