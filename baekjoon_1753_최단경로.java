import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//노드 클래스
class Node implements Comparable<Node> {
	private int index; //현재 노드와 연결된 노드 번호
	private int distance; //index 노드와 거리
		
	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}
		
	public int getIndex() {
		return this.index;
	}
		
	public int getDistance() {
		return this.distance;
	}
		
	//거리 기준 오름차순으로 정렬
	@Override
	public int compareTo(Node node) {
		return this.distance - node.distance;
	}
}

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int V, E, START;
	
	private static ArrayList<ArrayList<Node>> graph =  new ArrayList<ArrayList<Node>>();
	private static final int INF = (int) 1e9; //무한
	private static int [] d; //최단 경로 배열
	
	//다익스트라 알고리즘
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		pq.offer(new Node(start, 0)); //시작 노드 큐에 추가
		d[start] = 0; //자기 자신과의 거리는 0
		
		//큐가 빌 때까지 반복
		while(!pq.isEmpty()) {
			Node node = pq.poll(); //현재 노드
			int dis = node.getDistance(); //현재 노드까지의 거리
			int idx = node.getIndex(); //현재 노드 번호
			
			//현재 노드의 최단 거리 비용이 현재 노드까지의 거리보다 작다면
			if(d[idx] < dis) continue; //이미 최단 거리가 구해진 것
			
			//현재 노드와 연결된 다른 노드들 확인
			for(int i=0; i<graph.get(idx).size(); i++) {
				int cost = d[idx] + graph.get(idx).get(i).getDistance();
				//현재 노드를 거쳐서 다른 노드로 가는 경로가 더 짧은 경우
				if(cost < d[graph.get(idx).get(i).getIndex()]) {
					d[graph.get(idx).get(i).getIndex()] = cost; //비용 갱신
					pq.offer(new Node(graph.get(idx).get(i).getIndex(), cost)); //큐에 추가
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		String [] ve = br.readLine().split(" ");
		V = Integer.parseInt(ve[0]); //노드 수
		E = Integer.parseInt(ve[1]); //간선 수
		
		START = Integer.parseInt(br.readLine()); //시작 노드
		
		//그래프 초기화
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i=0; i<E; i++) {
			String [] uvw = br.readLine().split(" ");
			int u = Integer.parseInt(uvw[0]);
			int v = Integer.parseInt(uvw[1]);
			int w = Integer.parseInt(uvw[2]);
			
			graph.get(u).add(new Node(v, w));
		}
		
		//최단 경로 배열 초기화
		d = new int[V+1];
		Arrays.fill(d, INF);
		
		dijkstra(START); //다익스트라 알고리즘
		
		//결과 출력
		for(int i=1; i<=V; i++) {
			if(d[i] == INF) bw.write("INF\n");
			else bw.write(d[i] + "\n");
		}
		
		bw.close();
	}
}