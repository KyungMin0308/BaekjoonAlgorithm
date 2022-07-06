import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//노드 클래스
class Node implements Comparable<Node> {
	private int idx; //현재 노드와 연결된 노드 번호
	private int distance; //거리
	
	public Node(int idx, int distance) {
		this.idx = idx;
		this.distance = distance;
	}
	
	public int getIdx() { return this.idx; }
	public int getDistance() { return this.distance; }
	
	@Override
	public int compareTo(Node other) {
		return this.distance - other.distance; //거리가 짧은 순으로 정렬
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, E; //정점, 간선
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>(); //그래프
	
	static int INF = (int) 1e7; //무한대 거리
	
	static int V1, V2; //거쳐야 하는 정점
	
	//다익스트라
	public static void dijkstra(int v, int [] dis) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		//노드 v로 이동하는 최단 경로를 0으로 초기화
		pq.offer(new Node(v, 0));
		dis[v] = 0;
		
		//큐가 빌때까지
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int now = cur.getIdx(); //현재 노드
			int dist = cur.getDistance(); //현재 노드까지의 비용
			
			if(dis[now] < dist) continue; //이미 최단 거리가 계산된 경우 continue
			
			//최단 경로 갱신
			for(int i=0; i<graph.get(now).size(); i++) {
				int cost = dis[now] + graph.get(now).get(i).getDistance();
				if(cost < dis[graph.get(now).get(i).getIdx()]) {
					dis[graph.get(now).get(i).getIdx()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIdx(), cost));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		String [] ne = br.readLine().split(" ");
		N = Integer.parseInt(ne[0]); //정점
		E = Integer.parseInt(ne[1]); //간선
		
		//그래프 초기화
		for(int i=0; i<=N; i++) graph.add(new ArrayList<Node>());
		
		//그래프 데이터 입력
		for(int i=0; i<E; i++) {
			String [] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int c = Integer.parseInt(s[2]);
			
			//무방향 그래프(양방향 연결)
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		//거쳐야 하는 정점 번호 입력
		String [] v = br.readLine().split(" ");
		V1 = Integer.parseInt(v[0]);
		V2 = Integer.parseInt(v[1]);
		
		int [] start = new int[N+1]; //1번 노드에서 다른 정점으로 가는 최단 경로 저장
		int [] distance1 = new int[N+1]; //V1에서 다른 정점으로 가는 최단 경로 저장
		int [] distance2 = new int[N+1]; //V2에서 다른 정점으로 가는 최단 경로 저장
		
		//최단 거리 배열 초기화
		Arrays.fill(start, INF);
		Arrays.fill(distance1, INF);
		Arrays.fill(distance2, INF);
		
		//다익스트라 수행
		dijkstra(1, start);
		dijkstra(V1, distance1);
		dijkstra(V2, distance2);
		
		//최단 경로1: 1 -> V1 -> V2 -> N
		int route1 = start[V1] + distance1[V2] + distance2[N];
		
		//최단 경로2: 1 -> V2 -> V1 -> N
		int route2 = start[V2] + distance2[V1] + distance1[N];
		
		//정답 출력
		if(route1 >= INF && route2 >= INF) System.out.println(-1);
		else System.out.println(Math.min(route1, route2));
	}
}