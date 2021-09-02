import java.io.*;
import java.util.*;
public class BOJ_3190 {
	static int n, k;
	static int[][] map;
	static Rotate[] r;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		
		for(int i=0; i<k; i++) {
			String[] s = br.readLine().split(" ");
			map[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = 1;
		}
		
		r = new Rotate[Integer.parseInt(br.readLine())];
		for(int i=0; i<r.length; i++) {
			String[] s = br.readLine().split(" ");
			r[i] = new Rotate(Integer.parseInt(s[0]), s[1].charAt(0));
		}
		
		dummy();
	}
	
	static void dummy() {
		Queue<Node> snake = new LinkedList<>();
		
		snake.add(new Node(1,1));
		map[1][1] = -1;
		int sec = 1;
		int d = 0;
		int idx = 0;
		int nx = 1, ny = 1;
		
		while(true) {
			if(idx < r.length) {
				if(sec-1 == r[idx].x) {
					if(r[idx].c == 'L') {
						if(d == 0) d = 3;
						else d--;
					} else {
						d = (d+1)%4;
					}
					idx++;
				}
			}
			
			nx = nx + dx[d];
			ny = ny + dy[d];
			
			if(nx < 1 || nx >= n+1 || ny < 1 || ny >= n+1 || map[nx][ny] == -1) break;
			
			int tmp = map[nx][ny];
			map[nx][ny] = -1;
			snake.add(new Node(nx,ny));
			
			if(tmp != 1) {
				Node tmp2 = snake.poll();
				map[tmp2.x][tmp2.y] = 0;
			}
			
			sec++;
		}
		
		System.out.println(sec);
		
	}
	
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Rotate{
		int x;
		char c;
		Rotate(int x, char c){
			this.x = x;
			this.c = c;
		}
	}

}
