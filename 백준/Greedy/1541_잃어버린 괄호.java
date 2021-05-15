import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Greedy_12 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sub[] = br.readLine().split("-");
		
		int ret = Integer.MAX_VALUE;
		for(int i=0; i<sub.length; i++) {
			int sum = 0;
			
			String add[] = sub[i].split("\\+");
			
			for(int j=0; j<add.length; j++) 
				sum += Integer.parseInt(add[j]);
			
			if(ret == Integer.MAX_VALUE)
				ret = sum;
			else
				ret -= sum;
		}
		
		System.out.println(ret);
		
	}
}
