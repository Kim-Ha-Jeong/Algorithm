import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1508 {
    static int n,m,k;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[k];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(binarySearch());
        bw.flush();
        bw.close();
    }

    static String binarySearch(){
        String answer = "-1";
        int start = 1;
        int end = n;

        while(start <= end){
            int mid = (start + end)/2;
            String result = setRefree(mid);
            if(result.equals("-1")){
                end = mid - 1;
            } else {
                start = mid + 1;
                answer = result;
            }
        }

        return answer;
    }

    static String setRefree(int dist){
        StringBuffer sb = new StringBuffer();
        int cnt = 1;
        sb.append("1");
        int lastIdx = arr[0];

        for(int i=1; i<k; i++){
            int curIdx = arr[i];
            if(curIdx - lastIdx < dist){
                sb.append("0");
            } else {
                sb.append("1");;
                lastIdx = curIdx;
                cnt++;
            }

            if(cnt == m){
                sb.append("0".repeat(k - i - 1));
                break;
            }
        }

        return cnt == m ? sb.toString() : "-1";
    }

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int sub;
        int dist;

        Node(int start, int end, int dist){
            this.start = start;
            this.end = end;
            this.sub = start - end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n){
            if(this.dist == n.dist) {
                if(n.end == this.end) return n.start - this.start;
                return n.end - this.end;
            }
            return n.dist - this.dist;
        }
    }
}
