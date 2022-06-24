import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Node { //노드 클래스
	private int node; //자식 노드
	private int weight; //가중치
	
	public Node(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}
	
	public int getNode() { return this.node; }
	public int getWeight() { return this.weight; }
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N = 0;
	static List<List<Node>> graph = new ArrayList<List<Node>>(); //그래프
	static boolean [] visited; //방문 배열
	
	static int DIAMETER = 0; //트리 지름
	
	static int MAX_IDX = 0; //루트에서 가장 멀리 떨어진 노드 번호
	
	public static void dfs(int st, int w) {
		visited[st] = true; //현재 노드 방문처리
		
		//지금까지 계산된 가중치가 현재 트리의 지름보다 크다면
		if(w > DIAMETER) {
			DIAMETER = w; //트리의 지름 갱신
			MAX_IDX = st; //가장 멀리 떨어진 노드 갱신
		}
		
		//연결된 노드들 탐색
		for(int i=0; i<graph.get(st).size(); i++) {
			int next = graph.get(st).get(i).getNode();
			int weight = graph.get(st).get(i).getWeight();
			
			if(!visited[next]) { //아직 방문하지 않았다면 다음 노드로 이동
				dfs(next, w + weight); //현재까지 트리 지름 + 다음 노드의 가중치
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		N = Integer.parseInt(br.readLine()); //노드 갯수
		
		//그래프 초기화
		for(int i=0; i<=N; i++) graph.add(new ArrayList<Node>());
		
		//그래프 데이터 입력
		for(int i=0; i<N-1; i++) {
			String [] s = br.readLine().split(" ");
			int st = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int weight = Integer.parseInt(s[2]);
			
			//양뱡향 연결
			graph.get(st).add(new Node(end, weight));
			graph.get(end).add(new Node(st, weight));
		}
		
		visited = new boolean[N+1];
		dfs(1, 0); //루트 노드에서 가장 멀리 떨어진 노드를 찾음
		
		visited = new boolean[N+1];
		dfs(MAX_IDX, 0); //가장 멀리 떨어진 노드와 가장 멀리 떨어진 노드와 지름을 구함
		
		System.out.println(DIAMETER); //정답 출력
	}
}