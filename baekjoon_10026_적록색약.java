import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int N = 0;
	static String [][] map;
	
	static int [] area1 = new int[3]; //일반인 기준 R, G, B 영역 수 저장
	static int [] area2 = new int[3]; //적록색약 기준 R, G, B 영역 수 저장
	
	static int [] dx = { -1, 0, 1, 0 };
	static int [] dy = { 0, 1, 0, -1 };
	
	public static void dfs(int x, int y, String rgb, boolean col, boolean [][] visited) {		
		if(visited[x][y]) return; //이미 방문한 점이라면 리턴
		
		visited[x][y] = true; //현재 위치 방문 처리
		
		//상하좌우를 탐색
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue; //영역을 넘어가면 continue
			if(!col && !map[nx][ny].equals(rgb)) continue; //일반인인 경우 현재 색과 다르다면 continue
			if(col) { //적록색약인 경우 R과 G는 구분하지 않고 탐색
				if(rgb.equals("R") && map[nx][ny].equals("B")) continue; //현재 색이 R인데 인접한 색이 B라면 continue
				if(rgb.equals("G") && map[nx][ny].equals("B")) continue; //현재 색이 G인데 인접한 색이 B라면 continue
				if(rgb.equals("B") && !map[nx][ny].equals(rgb)) continue; //현재 색이 B이고 인접한 색이 다르다면 continue
			}
			
			//탐색
			dfs(nx, ny, rgb, col, visited);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		map = new String[N][N];
		boolean [][] visited1 = new boolean[N][N]; //일반인 방문 배열
		boolean [][] visited2 = new boolean[N][N]; //적록색약 방문 배열
		
		//map 데이터 입력
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = m[j];
			}
		}
		
		//탐색
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//일반인인 경우
				if(!visited1[i][j]) {
					dfs(i, j, map[i][j], false, visited1);
					
					if(map[i][j].equals("R")) area1[0]++;
					else if(map[i][j].equals("G")) area1[1]++;
					else if(map[i][j].equals("B")) area1[2]++;
					
				}
				//적록색약인 경우
				if(!visited2[i][j]) {
					dfs(i, j, map[i][j], true, visited2);
					
					if(map[i][j].equals("R")) area2[0]++;
					else if(map[i][j].equals("G")) area2[1]++;
					else if(map[i][j].equals("B")) area2[2]++;
				}
			}
		}
		
		int ans1 = area1[0] + area1[1] + area1[2]; //일반인
		int ans2 = area2[0] + area2[1] + area2[2]; //적록색약
		
        //결과 출력
		bw.write(ans1 + " " + ans2 + "\n");
		
		bw.close();
	}
}