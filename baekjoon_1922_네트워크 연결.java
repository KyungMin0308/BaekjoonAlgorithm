import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//간선 클래스
class Edge implements Comparable<Edge> {
	private int x;
	private int y;
	private int cost; //비용
	
	public Edge(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getCost() { return this.cost; }
	
	@Override
	public int compareTo(Edge e) {
		return this.cost - e.cost; //비용이 작은 순으로 정렬
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N; //컴퓨터 수
	static int M; //연결선 수
	static int RES; //최소 비용
	
	static PriorityQueue<Edge> edges = new PriorityQueue<Edge>(); //간선 정보
	static int [] parent; //부모
	
	//부모 노드 탐색
	public static int findParent(int x) {
		if(x == parent[x]) return x;
		else return parent[x] = findParent(parent[x]);
	}
	
	//부모 노드 합침
	public static void unionParent(int x, int y) {
		int a = findParent(x);
		int b = findParent(y);
		
		if(a > b) parent[a] = b;
		else parent[b] = a;
	}
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		//부모 배열 선언 및 초기화
		parent = new int[N+1];
		for(int i=0; i<=N; i++) parent[i] = i;
		
		//간선 정보 입력
		for(int i=0; i<M; i++) {
			String [] m = br.readLine().split(" ");
			int x = Integer.parseInt(m[0]);
			int y = Integer.parseInt(m[1]);
			int cost = Integer.parseInt(m[2]);
			
			edges.offer(new Edge(x, y, cost));
		}
		
		//최소 비용 계산(크루스칼 알고리즘)
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			int a = edge.getX();
			int b = edge.getY();
			
			//부모가 다른 경우에만 비용 합산
			if(findParent(a) != findParent(b)) {
				unionParent(a, b);
				RES += edge.getCost();
			}
		}
		
        //결과 출력
		System.out.println(RES);
	}
}