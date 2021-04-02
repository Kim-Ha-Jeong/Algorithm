import java.util.Scanner;

public class Fence {
	static int n;
	static int arr[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		
		for(int i=0; i<C; i++) {
			n = sc.nextInt();
			arr = new int[n];
			
			for(int j=0; j<n; j++) {
				arr[j] = sc.nextInt();
			}
			
			System.out.println(solve(0,n-1));
		}
	}
	
	static int solve(int left, int right) {
		if(left == right) return arr[left];
		
		int mid = (left+right)/2;
		
		int ret = Math.max(solve(left,mid), solve(mid+1,right));
		
		int low = mid, high = mid+1;
		int height = Math.min(arr[low], arr[high]);
		
		ret = Math.max(ret, height*2);
		
		while(left < low || high < right) {
			if(high < right && (low == left || arr[low-1] < arr[high+1])) {
				++high;
				height = Math.min(height, arr[high]);
			} else {
				--low;
				height = Math.min(height, arr[low]);
			}
			
			ret = Math.max(ret, height*(high-low+1));
		}
		
		return ret;
	}

}
