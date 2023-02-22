import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_10875_2 {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static long l;
    static ArrayList<Line> lineGroup = new ArrayList<Line>();
    static long x,y;
    static int d;
    static long ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        l = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        x = y = 0;
        ArrayList<String> command = new ArrayList<>();

        lineGroup.add(new Line(-l-1,l+1,l+1,l+1));
        lineGroup.add(new Line(-l-1,l+1,-l-1,-l-1));
        lineGroup.add(new Line(-l-1,-l-1,l+1,-l-1));
        lineGroup.add(new Line(l+1,-l-1,l+1,l+1));

        for(int i=0; i<n; i++){
            command.add(br.readLine());
        }

        long cnt;
        String dir = "";
        for(int i=0; i<n+1; i++){
            if(i == n){
                cnt = Long.MAX_VALUE;
                dir = "L";
            } else {
                String[] str = command.get(i).split(" ");
                dir = str[1];
                cnt = Long.parseLong(str[0]);
            }

            long tmp = Long.MAX_VALUE;

            for(Line line : lineGroup){
                if(line.dir == 1){
                    if(d == 0){
                        if(y == line.y1 && x < line.x1){
                            tmp = Math.min(tmp, line.x1 - x);
                        }
                    } else if(d == 1) {
                        if(line.x1 <= x && x <= line.x2 && line.y1 < y){
                            tmp = Math.min(tmp, y - line.y1);
                        }
                    } else if(d == 2){
                        if(y == line.y1 && line.x2 < x){
                            tmp = Math.min(tmp, x - line.x2);
                        }
                    } else if(d == 3){
                        if(line.x1 <= x && x <= line.x2 && y < line.y1){
                            tmp = Math.min(tmp, line.y1 - y);
                        }
                    }
                } else {
                    if(d == 0){
                        if(line.y1 <= y && y <= line.y2 && x < line.x1){
                            tmp = Math.min(tmp, line.x1 - x);
                        }
                    } else if(d == 1){
                        if(line.x1 == x && y > line.y2){
                            tmp = Math.min(tmp, y - line.y2);
                        }
                    } else if(d == 2){
                        if(line.y1 <= y && y <= line.y2 && x > line.x1){
                            tmp = Math.min(tmp, x - line.x1);
                        }
                    } else if(d == 3){
                        if(line.x1 == x && y < line.y1){
                            tmp = Math.min(tmp, line.y1 - y);
                        }
                    }
                }
            }

            if(cnt < tmp){
                long nx = x + dx[d]*cnt;
                long ny = y + dy[d]*cnt;
                lineGroup.add(new Line(x,y,nx,ny));

                ans += cnt;
                if(dir.equals("L")){
                    d = d == 0 ? 3 : d-1;
                } else {
                    d = (d+1)%4;
                }
                x = nx;
                y = ny;
            } else {
                ans += tmp;
                break;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
    }

    static class Line {
        long x1,y1,x2,y2;
        int dir;

        Line(long x1, long y1, long x2, long y2){
            this.x1 = Math.min(x1,x2);
            this.y1 = Math.min(y1,y2);
            this.x2 = Math.max(x1,x2);
            this.y2 = Math.max(y1,y2);
            if(x1 == x2) dir = 0;
            else dir = 1;
        }
    }
}
