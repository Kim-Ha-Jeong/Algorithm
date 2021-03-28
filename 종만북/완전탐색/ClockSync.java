import java.util.*;

public class ClockSync {
	static int INF = 9999;
	static int SWITCH = 10;
	static int CLOCK = 16;
	static int linked[][] = {
			{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0},
			{0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1},
			{1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,0,1,0,1,0,0,0},
			{1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1},
			{0,0,0,0,1,1,0,1,0,0,0,0,0,0,1,1},
			{0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,1,0,0,0,1,0,0,0,1,0,0}
	};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int c = sc.nextInt();
		int clocks[] = new int[CLOCK];
		int result[] = new int[c];
		
		for(int i=0; i<c; i++) {
			for(int j=0; j<CLOCK; j++) {
				clocks[j] = sc.nextInt();
			}
			
			result[i] = solve(clocks,0);
			if(result[i] == INF)
				result[i] = -1; 
		}
		
		for(int i=0; i<c; i++)
			System.out.println(result[i]);
	}
	
	public static boolean areAligned(int clocks[]) {
		for(int i=0; i<CLOCK; i++) {
			if(clocks[i] != 12)
				return false;
		}
		
		return true;
	}
	
	public static void push(int clocks[], int swtch) {
		for(int clock=0; clock<CLOCK; clock++) {
			if(linked[swtch][clock] == 1) {
				clocks[clock] += 3;
				if(clocks[clock] == 15)
					clocks[clock] = 3;
			}
		}
	}
	
	public static int solve(int clocks[], int swtch) {
		if(swtch == SWITCH)
			return areAligned(clocks) ? 0:INF;
		
		int ret = INF;
		for(int i=0; i<4; i++) {
			ret = Math.min(ret, i+solve(clocks,swtch+1));
			push(clocks,swtch);
		}
		
		return ret;
	}


}
