import java.io.*;

public class QuadTree {
	static String s;
	static int head = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int C = Integer.parseInt(br.readLine());
		
		for(int i=0; i<C; i++) {
			head = 0;
			s = br.readLine();
			
			System.out.println(reverse());
		}
		
	}
	
	static String reverse() {
		
		if(s.charAt(head)!='x'){
			head++;
			return s.charAt(head-1)+"";
		} 
		
		head++;
		String str[] = new String[4];
		str[0] = reverse();
		str[1] = reverse();
		str[2] = reverse();
		str[3] = reverse();
		
		return "x"+str[2]+str[3]+str[0]+str[1];
	}

}
