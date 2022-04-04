import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [][] map; //맵
	private static boolean [][] visited; //방문 배열
	
	private static int [] dx = { 0, -1, 0, 1 }; //x축 이동방향
	private static int [] dy = { 1, 0, -1, 0 }; //y축 이동방향
	
	public static void main(String[] args) throws IOException {
		
		//N, M 입력
		String [] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		//배열 초기화
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(m[j]);
			}
		}
		
		int answer = map[0][0]; //정답 저장 변수
		Queue<int []> queue = new LinkedList<int []>(); //BFS 수행을 위한 큐
		queue.offer(new int [] {0, 0, answer});
		
		//BFS
		while(!queue.isEmpty()) {
			int [] cur = queue.poll(); //현재 정보
			int x = cur[0];
			int y = cur[1];
			answer = cur[2]; //현재 이동한 칸 수
			
			//도착점에 도착하면 탈출
			if(x == map.length-1 && y == map[0].length-1) {
				break;
			}
			
			//상화좌우로 움직이면서 탐색
			for(int i=0; i<dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				//이동한 좌표가 map의 범위를 넘어가거나 이동한 좌표의 map값이 0이면 continue
				if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
				if(map[nx][ny] == 0) continue;
				
				//아직 방문하지 않은 좌표라면
				if(!visited[nx][ny]) {
					map[nx][ny] += map[x][y]; //이동한 좌표에 이전 좌표값 더함
					answer = map[nx][ny]; //정답 갱신
					queue.offer(new int [] {nx, ny, answer}); //큐에 추가
					visited[nx][ny] = true; //방문처리
				}
			}
		}
		
		//정답 출력
		bw.write(answer + "\n");
		
		bw.close();
	}
}