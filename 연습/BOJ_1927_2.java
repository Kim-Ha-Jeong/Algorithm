import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_1927_2 {
    static ArrayList<Integer> heap = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        heap.add(0);

        while (n-- > 0) {
            int tmp = Integer.parseInt(br.readLine());

            if (tmp == 0) {
                sb.append(delete()).append("\n");
            } else {
                insert(tmp);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void insert(int num) {
        heap.add(num);
        int current = heap.size() - 1;
        int parent = current / 2;

        while (true) {
            if (parent == 0 || heap.get(parent) <= heap.get(current))
                break;

            swap(parent, current);
            current = parent;
            parent = current / 2;
        }
    }

    static int delete() {
        if (heap.size() == 1)
            return 0;

        int top = heap.get(1);
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int currentIdx = 1;
        while (true) {
            int left = currentIdx * 2;
            int right = currentIdx * 2 + 1;

            if (left >= heap.size())
                break;

            int minValue = heap.get(left);
            int minIdx = left;

            if (right < heap.size() && heap.get(right) < minValue) {
                minValue = heap.get(right);
                minIdx = right;
            }

            if (heap.get(currentIdx) > minValue) {
                swap(currentIdx, minIdx);
                currentIdx = minIdx;
            } else
                break;
        }

        return top;
    }

    static void swap(int a, int b) {
        int tmp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, tmp);
    }

}
