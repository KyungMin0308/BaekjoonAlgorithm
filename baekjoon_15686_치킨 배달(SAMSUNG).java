import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	
	//좌표 클래스
	static class Point {
		private int x; //x좌표
		private int y; //y좌표
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;			
		}
		
		public String toString() {
			return "(" + this.x + ", " + this.y + ")"; 
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static ArrayList<Point> cities = new ArrayList<Point>(); //도시 위치
	private static ArrayList<Point> chickens = new ArrayList<Point>(); //치킨 매장 위치
	
	private static boolean [] visited; //방문 체크 배열
	private static int [] init; //chickens 배열 인덱스값
	private static int [] com; //조합 결과 저장
	
	private static int MIN = Integer.MAX_VALUE; //도시 치킨 거리 최소값
	
	//init 배열 초기화
	public static void make(int n) {
		init = new int[n];
		for(int i=0; i<n; i++) init[i] = i;
	}
	
	//치킨 거리 계산 함수
	public static int distance(Point p1, Point p2) {
		return (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));
	}
	
	//도시 치킨 거리 계산 함수
	public static void cityChickenDistance() {
		int [] res = new int[cities.size()];
		for(int i=0; i<com.length; i++) {
			Point chick = chickens.get(com[i]); //현재 치킨 매장 위치
			for(int j=0; j<cities.size(); j++) {
				if(i == 0) res[j] = distance(chick, cities.get(j));
				else res[j] = Math.min(res[j], distance(chick, cities.get(j)));
			}
		}
		
		//도시 치킨 거리 계산
		int sum = 0;
		for(int i: res) {
			sum += i;
		}
		
		MIN = Math.min(sum, MIN); //도시 치킨 거리 최소값 갱신
	}
	
	//치킨 매장 조합
	public static void comb(int n, int r, int idx, int depth, boolean [] visited) {
		if(depth == r) {
			cityChickenDistance(); //도시 치킨 거리 계산
			return;
		}
		
		for(int i=idx; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				com[depth] = init[i];
				comb(n, r, i+1, depth+1, visited);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//N, M 입력
		String [] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		for(int i=0; i<N; i++) {
			String [] str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(str[j]) == 0) continue;
				if(Integer.parseInt(str[j]) == 1) cities.add(new Point(i, j));
				if(Integer.parseInt(str[j]) == 2) chickens.add(new Point(i, j));
			}
		}
		
		make(chickens.size()); //init 배열 초기화
		visited = new boolean[chickens.size()]; //방문 배열 초기화
		for(int i=1; i<=M; i++) {
			com = new int[i];
			comb(chickens.size(), i, 0, 0, visited);
		}
		
        //결과 출력
		bw.write(MIN + "\n");
		
		bw.close();
	}
}