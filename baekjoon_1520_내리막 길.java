import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, H;
	
	static int [][] map; //지도
	static int [][] dp; //DP 배열
	
	static int [] dx = { -1, 0, 1, 0 };
	static int [] dy = { 0, 1, 0, -1 };
	
	//경로를 찾는 DFS 함수
	public static int dfs(int x, int y) {
		if(x == M-1 && y == N-1) return 1; //도착 지점에 도착하면 리턴
		if(dp[x][y] != -1) return dp[x][y]; //-1이 아니라면 이동 가능한 경로임을 의미하므로 리턴
		
		dp[x][y] = 0; //현재 위치 방문
		
		for(int i=0; i<dx.length; i++) {
			//다음 위치 계산
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//범위를 넘어가거나 높이가 높은 지점이라면 continue
			if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
			if(map[x][y] <= map[nx][ny]) continue;
			
			//다음 위치부터 다시 탐색
			dp[x][y] += dfs(nx, ny);
		}
		
		//결과 리턴
		return dp[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		
		//M, N 입력
		String [] nm = br.readLine().split(" ");
		M = Integer.parseInt(nm[0]);
		N = Integer.parseInt(nm[1]);
		
		//배열 초기화
		map = new int[M][N];
		dp = new int[M][N];
		
		for(int i=0; i<M; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(m[j]); //지도 데이터 입력
				dp[i][j] = -1; //DP 배열 초기값 입력
			}
		}
		
		//경로 구하기
		H = dfs(0, 0);
		
		//정답 출력
		System.out.println(H);
	}
}