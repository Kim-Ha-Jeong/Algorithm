import java.io.*;

public class BOJ_11728 {
	static int list[], sorted[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String st[] = br.readLine().split(" ");
		int n = Integer.parseInt(st[0]);
		int m = Integer.parseInt(st[1]);
		list = new int[n+m];
		sorted = new int[n+m];
		
		String s[] = br.readLine().split(" ");
		for(int i=0; i<n; i++)
			list[i] = Integer.parseInt(s[i]);
		
		String str[] = br.readLine().split(" ");
		for(int i=n; i<n+m; i++)
			list[i] = Integer.parseInt(str[i-n]);
		
		mergeSort(0,n+m-1);
			
		for(int i=0; i<n+m; i++)
			bw.write(list[i]+" ");
		
		bw.flush();
		bw.close();
	}
	
	static void merge(int left, int mid, int right) {
		int i,j,k,l;
		i = left;
		j = mid+1;
		k = left;
		
		while(i<=mid && j<=right) {
			if(list[i]<=list[j])
				sorted[k++] = list[i++];
			else
				sorted[k++] = list[j++];
		}
		
		if(i>mid) {
			for(l=j; l<=right; l++)
				sorted[k++] = list[l];
		} else {
			for(l=i; l<=mid; l++)
				sorted[k++] = list[l];
		}
		
		for(l=left; l<=right; l++)
			list[l] = sorted[l];
	}
	
	static void mergeSort(int left, int right) {
		int mid;
		
		if(left<right) {
			mid = (left+right)/2;
			mergeSort(left,mid);
			mergeSort(mid+1,right);
			merge(left,mid,right);
		}
	}

}
