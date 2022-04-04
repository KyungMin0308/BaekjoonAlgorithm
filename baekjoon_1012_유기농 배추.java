import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [][] map; //맵
	private static boolean [][] visited; //방문 배열
	
	private static int [] dx = { 0, -1, 0, 1 }; //x축 이동방향
	private static int [] dy = { 1, 0, -1, 0 }; //y축 이동방향
	
	public static void dfs(int x, int y) {
		if(visited[x][y]) return; //탐색 중 이미 방문한 점이라면 리턴
		
		visited[x][y] = true; //현재 위치 방문처리
		
		//상화좌우로 움직이면서 탐색
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//계산된 좌표가 map범위를 넘어가거나 배열값이 0이라면 continue
			if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
			if(map[nx][ny] == 0) continue;
			
			dfs(nx, ny);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//T 입력
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			//M, N, K 입력
			String [] mnk = br.readLine().split(" ");
			int M = Integer.parseInt(mnk[0]);
			int N = Integer.parseInt(mnk[1]);
			int K = Integer.parseInt(mnk[2]);
			
			//배열 선언
			map = new int[N][M];
			visited = new boolean[N][M];
			
			//map 정보 입력
			for(int j=0; j<K; j++) {
				String [] xy = br.readLine().split(" ");
				int x = Integer.parseInt(xy[1]);
				int y = Integer.parseInt(xy[0]);
				
				map[x][y] = 1;
			}
			
			//배추흰지렁이 마리 수 파악
			int count = 0;
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					if(map[n][m] == 0 || visited[n][m]) continue;
					
					dfs(n, m);
					count++; //탐색을 마치면 하나의 영역을 탐색했다는 의미로 마리 수 카운트
				}
			}
			
			//결과 출력
			bw.write(count + "\n");
		}
		
		bw.close();
	}
}