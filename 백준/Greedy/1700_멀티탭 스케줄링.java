import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Greedy_17 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);

		int[] elec = new int[k];

		s = br.readLine().split(" ");
		for (int i = 0; i < k; i++) {
			elec[i] = Integer.parseInt(s[i]);
		}

		int count = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			int now = elec[i];
			if (list.contains(now)) {
				continue;
			}

			if (list.size() == n) {
				int remove = -1;
				int idx = -1;
				for (int arr : list) {
					boolean flag = false;
					for (int j = i + 1; j < k; j++) {
						if (arr == elec[j]) {
							if(idx < j) {
								idx = j;
								remove = arr;
							}
							flag = true;
							break;
						}
					}

					if (!flag) {
						remove = arr;
						break;
					}
				}

				list.remove(Integer.valueOf(remove));
				count++;
			}
			
			list.add(now);
			
		}

		System.out.println(count);

	}

}
