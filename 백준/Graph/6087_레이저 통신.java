import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ_6087 {
	static char[][] map;
	static Node[] startEnd = new Node[2];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int h,w;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s = br.readLine().split(" ");
		w = Integer.parseInt(s[0]);
		h = Integer.parseInt(s[1]);
		map = new char[h][w];
		
		int idx = 0;
		for(int i=0; i<h; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<w; j++) {
				if(ch[j] == 'C') {
					startEnd[idx] = new Node(i, j, -1, 0);
					idx++;
				}
				map[i][j] = ch[j];
			}
		}
		
		int ans = dijkstra(startEnd[0]);
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	static int dijkstra(Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][] dist = new int[h][w];
		Node tmp = startEnd[1];
		pq.add(start);
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.x == tmp.x && cur.y == tmp.y) break;
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
				
				if(map[nx][ny] != '*') {
					int cnt = cur.cnt;
					if(cur.d != -1 && cur.d != i) {
						cnt++;
					}
					
					if(dist[nx][ny] == 0 || dist[nx][ny] >= cnt) {
						dist[nx][ny] = cnt;
						pq.add(new Node(nx,ny,i,cnt));
					}
				}
			}
		}
		
		return dist[tmp.x][tmp.y];
	}

	static class Node implements Comparable<Node>{
		int x;
		int y;
		int d;
		int cnt;
		Node(int x, int y, int d, int cnt){
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}
	}

}