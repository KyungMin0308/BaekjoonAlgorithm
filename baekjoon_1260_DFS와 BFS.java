import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); // 그래프
	private static boolean[] dfsVisited; // DFS 방문 체크 배열
	private static boolean[] bfsVisited; // BFS 방문 체크 배열

	// 깊이 우선 탐색(DFS) 수행
	public static void DFS(int v) throws IOException {
		dfsVisited[v] = true; // 현재 노드 방문 처리
		bw.write(v + " "); // 방문 노드 출력

		// 현재 노드와 연결된 다른 노드 재귀적 방문
		for (int i = 0; i < graph.get(v).size(); i++) {
			int node = graph.get(v).get(i);
			if (!dfsVisited[node]) {
				DFS(node);
			}
		}
	}

	// 너비 우선 탐색(BFS) 수행
	public static void BFS(int v) throws IOException {
		Queue<Integer> queue = new LinkedList<>();

		// 현재 노드 방문 처리
		queue.offer(v);
		bfsVisited[v] = true;

		// 큐가 빌때까지
		while (!queue.isEmpty()) {
			// 큐에서 하나 뽑아 출력(방문 노드 출력)
			int visitNode = queue.poll();
			bw.write(visitNode + " ");

			// 해당 원소와 연결된, 아직 방문하지 않은 원소 큐에 삽입
			for (int i = 0; i < graph.get(visitNode).size(); i++) {
				int node = graph.get(visitNode).get(i);
				if (!bfsVisited[node]) {
					queue.offer(node);
					bfsVisited[node] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		// 정점 수(N), 간선 수(M), 탐색 시작 번호(V) 입력
		String nmv = br.readLine();
		StringTokenizer st = new StringTokenizer(nmv);

		int N = Integer.parseInt(st.nextToken()); // 정점 수
		int M = Integer.parseInt(st.nextToken()); // 간선 수
		int V = Integer.parseInt(st.nextToken()); // 탐색 시작 번호

		// 방문 배열 초기화
		dfsVisited = new boolean[N + 1];
		bfsVisited = new boolean[N + 1];

		// 그래프 초기화
		for (int i = 0; i < N + 1; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			String pc = br.readLine();
			st = new StringTokenizer(pc);

			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());

			graph.get(parent).add(child);
			graph.get(child).add(parent);
		}

		// 각 정점과 연결된 노드들을 오름차순으로 정렬
		for (int i = 0; i < N + 1; i++)
			Collections.sort(graph.get(i));

		// 탐색 수행
		DFS(V);

		bw.write("\n"); // 줄 바꿈

		BFS(V);

		bw.close();
	}
}