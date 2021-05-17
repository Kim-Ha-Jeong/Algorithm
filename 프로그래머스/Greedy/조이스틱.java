class Greedy_14 {
	public int solution(String name) {
		int answer = 0;
		int length = name.length();
		int min = length - 1;

		for (int i = 0; i < length; i++) {
			char alpha = name.charAt(i);
			answer += Math.min(alpha - 'A', 'Z' - alpha + 1);

			int next = i + 1;
			while (next < length && name.charAt(next) == 'A')
				next++;

			min = Math.min(min, length - next + i * 2);
		}

		int left = 0, right = 0;
		for (int i = 1; i < length; i++) {
			if (name.charAt(i) == 'A')
				left++;
			else
				break;
		}

		for (int i = length - 1; i >= 0; i--) {
			if (name.charAt(i) == 'A')
				right++;
			else
				break;
		}

		int max = Math.max(right, left);

		min = Math.min(min, length - 1 - Math.max(right, left));

		answer += min;
		return answer;
	}
}