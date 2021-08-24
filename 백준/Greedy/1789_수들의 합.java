import java.io.*;

public class BOJ_1789 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		
		long sum = 0;
		int ret = 0;
		for(int i=1; ; i++) {
			if(sum > S) break;
			sum += i;
			ret++;
		}
		
		System.out.println(ret-1);
	}

}
