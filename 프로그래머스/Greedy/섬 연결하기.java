import java.util.*;
class Edge implements Comparable<Edge> {
    int start, end, cost;
    
    public Edge(int start, int end, int cost){
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}
class Greedy_15 {
    static int[] parent;
    static PriorityQueue<Edge> pq;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        pq = new PriorityQueue<Edge>();
        
        for(int i=0; i<n; i++)
            parent[i] = i;
        
        for(int i=0; i<costs.length; i++){
            pq.offer(new Edge(costs[i][0],costs[i][1],costs[i][2]));
        }
        
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            
            if(find(e.start) == find(e.end)) continue;
            else {
                union(e.start, e.end);
                answer+=e.cost;
            }
        }
        return answer;
    }
    
    public static int find(int p){
        if(parent[p] == p) return p;
        return parent[p] = find(parent[p]);
    }
    
    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA != rootB) parent[rootB] = rootA;
    }
}