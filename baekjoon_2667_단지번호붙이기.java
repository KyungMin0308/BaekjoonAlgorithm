import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [][] map; //지도
	private static boolean [][] visited; //방문 배열
	private static ArrayList<Integer> apartments = new ArrayList<Integer>(); //단지수
	
	private static int [] dx = { -1, 0, 1, 0 }; //x축 이동방향
	private static int [] dy = { 0, 1, 0, -1 }; //y축 이동방향
	
	private static int house = 0; //단지 내 집의 수
	
	//DFS
	public static void dfs(int x, int y) {
		if(visited[x][y]) return; //이미 방문한 위치면 리턴
		
		visited[x][y] = true; //현재 위치 방문 처리
		house++; //해당 단지 내 집의 수
		
		//좌표를 이동하면서
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//이동한 좌표가 범위를 넘어가거나, 해당 위치 값이 0이라면 continue
			if(nx < 0 || nx >= map.length || ny < 0 || ny >= map.length) continue;
			if(map[nx][ny] == 0) continue;
			
			dfs(nx, ny); //탐색
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//N: 지도의 크기
		int N = Integer.parseInt(br.readLine());
		
		//지도
		map = new int[N][N];
		visited = new boolean[N][N];
		
		//지도 초기화
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split("");
			for(int j=0; j<m.length; j++) {
				map[i][j] = Integer.parseInt(m[j]);
			}
		}
		
		//모든 좌표 확인
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//좌표의 값이 0이거나 이미 방문했다면 continue
				if(map[i][j] == 0 || visited[i][j]) continue;
				
				dfs(i, j);
				apartments.add(house); //dfs 종료 후 단지 내 집의 수 저장
				house = 0; //집의 수 0으로 초기화
			}
		}
        
        Collections.sort(apartments); //오름차순 정렬
		
		//결과 출력
		bw.write(apartments.size() + "\n");
		for(int i: apartments) {
			bw.write(i + "\n");
		}
		
		bw.close();
	}
}