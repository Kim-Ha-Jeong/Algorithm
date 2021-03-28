import java.io.*;

public class BoardCover {
	static int c,w,h;
	static int coverType[][][] = {
			{{0,0},{1,0},{0,1}},
			{{0,0},{0,1},{1,1}},
			{{0,0},{1,0},{1,1}},
			{{0,0},{1,0},{1,-1}}
			};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		c = Integer.parseInt(br.readLine());
		int result[] = new int[c];
		
		for(int i=0; i<c; i++) {
			String s[] = br.readLine().split(" ");
			h = Integer.parseInt(s[0]);
			w = Integer.parseInt(s[1]);
			int board[][] = new int[h][w];
			
			for(int k=0; k<h; k++) {
				String str = br.readLine();
				for(int j=0; j<w; j++) {
					board[i][j] = (str.charAt(j) == '#')? 1:0;
				}
			}
			
			result[i] = cover(board);
		}
		
		for(int i=0; i<c; i++)
			System.out.println(result[i]);
	
	}
	
	public static boolean set(int board[][],int y, int x, int type, int delta) {
		boolean ok = true;
		
		for(int i=0; i<3; i++) {
			int ny = y + coverType[type][i][0];
			int nx = x + coverType[type][i][1];
			
			if(ny<0 || ny>=h || nx<0 || nx>= w)
				ok = false;
			else if((board[ny][nx] += delta) > 1)
				ok = false;
		}
		
		return ok;
	}
	
	public static int cover(int board[][]) {
		int y = -1, x = -1;
		
		for(int i=0; i<h; ++i) {
			for(int j=0; j<w; ++j) {
				if(board[i][j] == 0) {
					y = i;
					x = j;
					break;
				}
			}
			if(y != -1)
				break;
		}
		
		if(y == -1) 
			return 1;
		
		int ret = 0;
		
		for(int type =0; type<4; type++) {
			if(set(board,y,x,type,1))
				ret+=cover(board);
			set(board,y,x,type,-1);
		}
		
		return ret;
	}
}
