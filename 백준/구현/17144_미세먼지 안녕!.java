import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
public class Imple_13 {
	static int[][] map;
	static Queue<Node> q;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int R, C, T;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		T = Integer.parseInt(s[2]);
		
		map = new int[R][C];
		int cleaner = -1;
		
		for(int i=0; i<R; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				
				 if(map[i][j] == -1 && cleaner == -1) {
						cleaner = i;
				}
			}
		}
		
		while(T-->0) {
			q = new LinkedList<>();
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j] != 0 && map[i][j] != -1) {
						q.add(new Node(i,j,map[i][j]));
					}
				}
			}
			
			diffusion();
			wind(cleaner);
		}
		
		int sum = cal();
		System.out.println(sum);
	}
	
	static void diffusion() {
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int dust = cur.d/5;
			int count = 0;
			
			if(dust == 0) 
				continue;
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || nx >= R || ny<0 || ny>=C || map[nx][ny] == -1)
					continue;
				
				count++;
				map[nx][ny] += dust;
			}
			
			map[cur.x][cur.y] -= dust*count;
		}
	}
	
	static void wind(int cleaner) {
		int up = cleaner;
		int down = cleaner+1;
		
		for(int i=up-1; i>0; i--) {
			map[i][0] = map[i-1][0];
		}
		
		for(int i=0; i<C-1; i++) {
			map[0][i] = map[0][i+1]; 
		}
		
		for(int i=0; i<up; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		
		for(int i=C-1; i>1; i--) {
			map[up][i] = map[up][i-1]; 
		}
		
		map[up][1] = 0; // 공기청정기에서 바로 나온 애
		
		for(int i=down+1; i<R-1; i++) {
			map[i][0] = map[i+1][0];
		}
		
		for(int i=0; i<C-1; i++) {
			map[R-1][i] = map[R-1][i+1]; 
		}
		
		for(int i=R-1; i>down; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		
		for(int i=C-1; i>1; i--) {
			map[down][i] = map[down][i-1]; 
		}
		
		map[down][1] = 0;
	}
	
	static int cal() {
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != -1 && map[i][j] != 0)
					sum += map[i][j];
			}
		}
		
		return sum;
	}
	static class Node{
		int x;
		int y;
		int d;
		Node(int x, int y, int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
