import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M;
	static int [][] map;
	static boolean [][][] visited;
	
	static int [] dx = { -1, 0, 1, 0 };
	static int [] dy = { 0, 1, 0, -1 };
	
	static int COUNT = Integer.MAX_VALUE; //이동 칸 수
	
	public static void main(String[] args) throws IOException {
		
		String [] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(m[j]);
			}
		}
		
		//3차원 배열
		//visited[][][0]: 벽을 부수지 않고 이동
		//visited[][][1]: 벽을 부수고 이동
		visited = new boolean[N][M][2];
		
		Queue<int []> queue = new LinkedList<int []>();
		
		//시작위치(0, 0)
		//이동한 칸 수(1)
		//벽 부신 여부(0: 부순적 없음 / 1: 부순적 있음)
		queue.offer(new int[] {0, 0, 1, 0});
		
		while(!queue.isEmpty()) {
			int [] cur = queue.poll();
			
			//도착점에 도착했다면 이동한 칸 수 저장 후 탈출
			if(cur[0] == N-1 && cur[1] == M-1) {
				COUNT = cur[2];
				break;
			}
			
			//상하좌우 확인
			for(int i=0; i<dx.length; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				//범위를 넘어가면 이동 불가
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				//벽이 아닌 경우
				if(map[nx][ny] == 0) {
					//벽을 한번도 부수지 않았다면
					if(cur[3] == 0 && !visited[nx][ny][0]) {
						queue.offer(new int[] {nx, ny, cur[2]+1, 0});
						visited[nx][ny][0] = true;
					}
					//벽을 한번 부쉈다면
					else if(cur[3] == 1 && !visited[nx][ny][1]) {
						queue.offer(new int[] {nx, ny, cur[2]+1, 1});
						visited[nx][ny][1] = true;
					}
				}
				//벽인 경우
				else if(map[nx][ny] == 1) {
					//벽을 한번도 부순적이 없다면 벽을 부숨
					if(cur[3] == 0) {
						queue.offer(new int[] {nx, ny, cur[2]+1, 1});
						visited[nx][ny][1] = true;
					}
				}
			}
		}
		
		if(COUNT == Integer.MAX_VALUE) System.out.println(-1); //도착하지 못함
		else System.out.println(COUNT); //도착함
	}
}