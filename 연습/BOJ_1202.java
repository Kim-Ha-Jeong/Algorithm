import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_1202 {
    static int[] backpack;
    static Jewelry[] jew;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Jewelry> pq = new PriorityQueue<>(Collections.reverseOrder());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        jew = new Jewelry[n];
        backpack = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jew[i] = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < k; i++) {
            backpack[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(backpack);
        Arrays.sort(jew, new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry j1, Jewelry j2) {
                return j1.weight - j2.weight;
            }
        });

        int p1 = 0;
        int p2 = 0;
        long ans = 0;

        while (true) {
            if (p1 >= k || (pq.size() == 0 && p2 >= n))
                break;

            int bag = backpack[p1++];

            for (; p2 < n; p2++) {
                if (jew[p2].weight <= bag) {
                    pq.add(jew[p2]);
                } else
                    break;
            }

            if (!pq.isEmpty()) {
                ans += pq.poll().value;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static class Jewelry implements Comparable<Jewelry> {
        int weight;
        int value;

        Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry j) {
            return this.value - j.value;
        }
    }

}
