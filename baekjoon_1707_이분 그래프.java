import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); //그래프
	static int [] colors; //정점 색 배열
	
	public static boolean bfs(int v) {
		colors[v] = 1; //현재 정점을 1번 색상으로 칠함
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(v);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			//현재 정점과 인접한 정점 탐색
			for(int i=0; i<graph.get(cur).size(); i++) {
				//인접한 정점이 아직 칠해져 있지 않다면
				if(colors[graph.get(cur).get(i)] == 0) {
					//현재 정점의 색과 다른 색으로 인접한 정점을 칠하고 큐에 정점 추가
					colors[graph.get(cur).get(i)] = colors[cur] * -1;
					queue.offer(graph.get(cur).get(i));
				}
				//인접한 정점의 색과 현재 정점의 색이 같다면 이분 그래프가 될 수 없으므로 false
				else if(colors[cur] == colors[graph.get(cur).get(i)]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		//테스트 케이스 수
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			//정점 및 간선 수 입력
			String [] ve = br.readLine().split(" ");
			int V = Integer.parseInt(ve[0]);
			int E = Integer.parseInt(ve[1]);
			
			//그래프 초기화
			for(int j=0; j<=V; j++) graph.add(new ArrayList<Integer>());
			
			for(int j=0; j<E; j++) {
				String [] e = br.readLine().split(" ");
				int st = Integer.parseInt(e[0]);
				int end = Integer.parseInt(e[1]);
				
				//양방향 연결
				graph.get(st).add(end);
				graph.get(end).add(st);
			}
			
			//판별
			//모든 정점을 돌면서 이분 그래프 체크
			colors = new int[graph.size()];
			boolean check = true;
			for(int j=1; j<=V; j++) {
				if(!check) {
					break; //만약 하나의 정점이라도 false라면 break
				}
				
				if(colors[j] == 0) { //아직 방문하지 않은 정점에 대해 탐색
					check = bfs(j);
				}
			}
			
			if(check) System.out.println("YES");
			else System.out.println("NO");
			
			graph.clear();
		}
	}
}