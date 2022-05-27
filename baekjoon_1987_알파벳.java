import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int R, C; //세로, 가로
	static char [][] map; //알파벳 배열
	static List<Character> path; //지나온 알파벳 리스트
	static boolean [][] visited; //방문 배열
	static int RES = 1; //정답
	
	//이동 방향
	static int [] dx = { -1, 0, 1, 0 };
	static int [] dy = { 0, 1, 0, -1 };
	
	//탐색
	static void dfs(int x, int y, List<Character> list, boolean [][] visited) {
		//방문한 위치의 알파벳이 이미 리스트에 있다면
		if(list.contains(map[x][y])) {
			RES = Math.max(RES, list.size()); //정답 갱신
			return; //리턴
		}
		
		list.add(map[x][y]); //리스트에 추가
		visited[x][y] = true; //방문 처리
		
		//상하좌우 확인하면서 범위를 넘어가지 않으면 방문
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
            
			dfs(nx, ny, list, visited);
		}
        
        list.remove(list.size()-1);
        visited[x][y] = false;
	}
	
	public static void main(String[] args) throws IOException {
		
		//R, C 입력
		String [] rc = br.readLine().split(" ");
		R = Integer.parseInt(rc[0]);
		C = Integer.parseInt(rc[1]);
		
		//배열, 리스트 초기화
		map = new char[R][C];
		visited = new boolean[R][C];
		path = new ArrayList<Character>();
		
		//알파벳 입력
		for(int i=0; i<R; i++) {
			String alpha = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = alpha.charAt(j);
			}
		}
		
		//탐색 수행
		dfs(0, 0, path, visited);
		
		//정답 출력
		bw.write(RES + "\n");
		
		bw.close();
	}
}