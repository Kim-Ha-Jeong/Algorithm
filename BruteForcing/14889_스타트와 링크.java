package study;

import java.util.*;

class StartLink {
    static int n;
    static boolean[] visited;
    static int[][] arr;
    static int min = 987654321;
 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
 
        n = sc.nextInt();
        visited = new boolean[n+1];
        arr = new int[n+1][n+1];
 
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
 
        team(1, 0);
        System.out.println(min);
    }
 
    static void team(int start, int depth) {
        if(depth == n/2) {
            min = Math.min(min, power());
        }
 
        for(int i=start; i<n+1; i++) {
            if(visited[i] != true) {
                visited[i] = true;
                team(i+1, depth+1);
                visited[i] = false;
            }
        }
    }
 
    static int power() {
        int sumStart = 0;
        int sumLink = 0;
 
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                // true ¸é ½ºÅ¸Æ®ÆÀ
                if(visited[i] && visited[j])
                    sumStart += arr[i][j];
 
                // false ¸é ¸µÅ©ÆÀ
                if(visited[i] != true && visited[j] != true)
                    sumLink += arr[i][j];
            }
        }
 
        return Math.abs(sumStart - sumLink);
    }
}