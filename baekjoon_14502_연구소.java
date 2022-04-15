import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int SAFEZONE = Integer.MIN_VALUE; //안전 영역 크기
	
	private static boolean [] visited; //조합용 방문 배열
	private static ArrayList<int []> zeros = new ArrayList<int []>(); //0의 위치
	private static ArrayList<int []> coms = new ArrayList<int []>(); //조합 결과 저장
	
	private static int [] dx = { -1, 0, 1, 0 };
	private static int [] dy = { 0, 1, 0, -1 };
	
	//안전 영역 크기
	public static void safeZone(int [][] lab) {
		int cnt = 0;
		for(int i=0; i<lab.length; i++) {
			for(int j=0; j<lab[0].length; j++) {
				if(lab[i][j] == 0) cnt++;
			}
		}
		
		SAFEZONE = Math.max(SAFEZONE, cnt); //최대값 갱신
	}
	
	//벽을 세울 공간 구하는 조합
	public static void comb(int count, int idx, boolean [] visited) {
		if(count == 3) { //벽은 3개 추가 가능
			int [] cur = new int[3];
			int curIdx = 0;
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) {
					cur[curIdx++] = i; //조합 배열 생성
				}
			}
			coms.add(cur); //조합 결과 리스트에 저장
			
			return;
		}
		
		for(int i=idx; i<visited.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(count+1, i+1, visited);
				visited[i] = false;
			}
		}
	}
	
	public static void bfs(int [][] lab, int [] coms) {
		int [][] virusLab = new int[lab.length][lab[0].length]; //바이러스가 퍼진 lab
		Queue<int []> queue = new LinkedList<int []>(); //BFS 큐
		
		//virusLab 초기화
		for(int i=0; i<virusLab.length; i++) {
			for(int j=0; j<virusLab[0].length; j++) {
				virusLab[i][j] = lab[i][j];
				if(lab[i][j] == 2) queue.offer(new int [] {i, j}); //값이 2면 바이러스이므로 큐에 바이러스 위치 저장
			}
		}
		
		//벽 3개 추가
		for(int i=0; i<coms.length; i++) {
			int [] cur = zeros.get(coms[i]);
			virusLab[cur[0]][cur[1]] = 1;
		}
		
		//바이러스 전파
		while(!queue.isEmpty()) {
			int [] cur = queue.poll();
			
			for(int i=0; i<dx.length; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx < 0 || nx >= lab.length || ny < 0 || ny >= lab[0].length) continue;
				if(virusLab[nx][ny] == 1) continue;
				if(virusLab[nx][ny] == 2) continue;
				
				virusLab[nx][ny] = 2;
				queue.offer(new int [] {nx, ny});
			}
		}
		
		//안전 영역 크기 계산
		safeZone(virusLab);
	}
	
	public static void main(String[] args) throws IOException {
		
		//N, M 입력
		String [] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		int [][] lab = new int[N][M];
		
		//연구소 데이터 입력
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				lab[i][j] = Integer.parseInt(m[j]);
				if(Integer.parseInt(m[j]) == 0) zeros.add(new int [] {i,j}); //빈칸 위치 리스트에 추가
			}
		}
		
		//벽을 세울 수 있는 위치 조합 구하기
		visited = new boolean[zeros.size()];
		comb(0, 0, visited);
		
		//조합 리스트를 돌면서 바이러스 전파 후 안전 영역 크기 확인
		for(int i=0; i<coms.size(); i++) {
			bfs(lab, coms.get(i));
		}
		
		//정답 출력
		bw.write(SAFEZONE + "\n");
		
		bw.close();
	}
}