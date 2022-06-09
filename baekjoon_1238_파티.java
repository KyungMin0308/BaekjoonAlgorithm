import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//노드 클래스
class Node implements Comparable<Node> {
	private int index; //현재 노드와 연결된 다른 노드 번호
	private int distance; //거리
	
	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}
	
	public int getIndex() { return this.index; }
	public int getDistance() { return this.distance; }
	
	//거리가 짧은 순으로 정렬
	@Override
	public int compareTo(Node node) {
		return this.distance - node.distance;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int INF = 1000000;
	
	//다익스트라 알고리즘
	public static void dijkstra(ArrayList<ArrayList<Node>> graph, int [] dis, int v) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		//시작 노드(v)로 가는 최단 경로는 0으로 초기화
		pq.offer(new Node(v, 0));
		dis[v] = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll(); //현재 노드
			int now = cur.getIndex(); //현재 노드 번호
			int dist = cur.getDistance(); //현재 노드까지의 거리
			
			if(dis[now] < dist) continue; //현재 노드가 이미 처리된 경우 continue
			
			//현재 노드와 연결된 다른 노드 확인
			for(int i=0; i<graph.get(now).size(); i++) {
				int cost = dis[now] + graph.get(now).get(i).getDistance();
				//현재 노드를 거쳐 다른 노드로 가는 거리가 더 짧은 경우
				if(cost < dis[graph.get(now).get(i).getIndex()]) {
					dis[graph.get(now).get(i).getIndex()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>(); //원본 배열
		ArrayList<ArrayList<Node>> rev_graph = new ArrayList<ArrayList<Node>>(); //원본 배열을 뒤집은 배열
		
		int distance[]; //최단 거리 저장(graph)
		int rev_distance[]; //최단 겨리 저장(reverse_graph)
		
        //N, M, X 입력
		String [] nmx = br.readLine().split(" ");
		int n = Integer.parseInt(nmx[0]);
		int m = Integer.parseInt(nmx[1]);
		int x = Integer.parseInt(nmx[2]);
		
		//거리 배열 선언
		distance = new int[n+1];
		rev_distance = new int[n+1];
		
		//거리 배열 초기화
		Arrays.fill(distance, INF);
		Arrays.fill(rev_distance, INF);
		
		//그래프 초기화
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<Node>());
			rev_graph.add(new ArrayList<Node>());
		}
		
		//그래프 데이터 입력
		for(int i=0; i<m; i++) {
			String [] s = br.readLine().split(" ");
			int st = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int time = Integer.parseInt(s[2]);
			
			graph.get(st).add(new Node(end, time));
			rev_graph.get(end).add(new Node(st, time));
		}
		
		//최단 거리 구하기
		dijkstra(graph, distance, x); //다른 노드에서 X로 가는 최단 거리
		dijkstra(rev_graph, rev_distance, x); //X에서 다른 노드로 가는 최단 거리
		
		//정답 구하기
		int res = 0;
		for(int i=1; i<=n; i++) {
			if(i == x) continue;
			
			if(res == 0) res = distance[i] + rev_distance[i];
			else res = Math.max(res, distance[i] + rev_distance[i]);
		}
		
		System.out.println(res);
	}
}