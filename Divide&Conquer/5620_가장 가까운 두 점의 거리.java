import java.io.*;
import java.util.*;
public class BOJ_5620 {
	static ArrayList<Point> a;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		a = new ArrayList<Point>();
		
		for(int i=0; i<n; i++) {
			String s[] = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			a.add(new Point(x,y));
		}
		
		Collections.sort(a, (p1,p2) -> p1.x - p2.x);
		
		bw.write(solve(0,n-1)+"");
		bw.flush();
		bw.close();
		
	}
	static int dist(Point a, Point b) {
		return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
	}
	
	static int bruteForce(int start, int end) {
		int min = Integer.MAX_VALUE;
		
		for(int i=start; i<end; i++) {
			for(int j=i+1; j<=end; j++) {
				int k = dist(a.get(i), a.get(j));
				min = Math.min(min, k);
			}
		}
		
		return min;
	}
	
	static int solve(int start, int end) {
		int n = end - start + 1;
		
		if(n <= 3) 
			return bruteForce(start,end);
		
		int mid = (start + end) / 2;
		
		int d = Math.min(solve(start,mid), solve(mid+1,end));
		
		ArrayList<Point> band = new ArrayList<Point>();
		
		for(int i=start; i<= end; i++) {
			int t = a.get(mid).x - a.get(i).x;
			
			if(t*t < d)
				band.add(a.get(i));
		}
		
		Collections.sort(band, (p1,p2)-> p1.y-p2.y);
		
		for(int i=0; i<band.size()-1; i++) {
			for(int j=i+1; j<band.size(); j++) {
				int t = band.get(j).y - band.get(i).y;
				
				if(t*t < d)
					d = Math.min(d, dist(band.get(i), band.get(j)));
				else 
					break;
			}
		}
		
		return d;
	}

	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
