import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	//좌표 클래스
	static class Point implements Comparable<Point> {
		private int x;
		private int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() { //출력 함수
			return x + " " + y + "\n";
		}

		//정렬 함수
		@Override
		public int compareTo(Point p) {
			if(this.x < p.x) {
				return -1;
			}
			else if(this.x == p.x) {
				if(this.y < p.y) {
					return -1;
				}
				else if(this.y > p.y) {
					return 1;
				}
			}
			else {
				return 1;
			}
			return 0;
		}
	}
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {

		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		//우선순위 큐 생성
		PriorityQueue<Point> pt = new PriorityQueue<Point>(100000);
		
		//좌표 입력
		for(int i=0; i<N; i++) {
			String xy = br.readLine();
			StringTokenizer st = new StringTokenizer(xy);
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			pt.add(new Point(x, y));
		}
		
		//결과 출력
		while(!pt.isEmpty()) {
			Point p = pt.poll();
			bw.write(p.toString());
		}
		
		bw.close();
	}
}