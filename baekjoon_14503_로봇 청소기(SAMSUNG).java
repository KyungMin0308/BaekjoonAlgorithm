import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//지도 관련 변수
	private static int N, M;
	private static int [][] map;
	
	private static int CLEAN = 0; //청소 횟수
	
	/*
	 *     북(0)
	 * 서(3)    동(1)
	 *     남(2)
	 */
	
	//로봇 청소
	public static void clean(int x, int y, int d) {
		//1. 현재 위치가 빈공간이라면 청소
		if(map[x][y] == 0) map[x][y] = ++CLEAN;
		
		//2. 인접한 칸 탐색
		int cnt = 0; //연속된 탐색 횟수
		int nx = 0, ny = 0; //이동 좌표
		for(int i=0; i<4; i++) {
			nx = x;
			ny = y;
			
			//현재 바라보는 방향의 왼쪽 확인
			if(d == 0) ny -= 1; //y좌표 감소
			else if(d == 1) nx -= 1; //x좌표 감소
			else if(d == 2) ny += 1; //y좌표 증가
			else if(d == 3) nx += 1; //x좌표 증가
			
			//범위를 넘어가면 continue
			if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
				cnt++;
				continue;
			}
			
			//벽이거나 청소한 위치라면
			if(map[nx][ny] != 0) {
				cnt++; //연속 탐색 횟수 카운트
				
				//방향 회전 후 continue
				if(d == 0) d = 3;
				else if(d == 1) d = 0;
				else if(d == 2) d = 1;
				else if(d == 3) d = 2;
				
				continue;
			}
			//청소가 필요한 위치라면 break
			else if(map[nx][ny] == 0) {
				//방향 회전 후 break
				if(d == 0) d = 3;
				else if(d == 1) d = 0;
				else if(d == 2) d = 1;
				else if(d == 3) d = 2;
				
				break;
			}
		}
		
		//2b. 연속으로 4번 탐색했다면
		if(cnt == 4) {
			//현재 위치에서
			nx = x; ny = y;
			
			//후진
			if(d == 0) nx += 1; //x좌표 증가
			else if(d == 1) ny -= 1; //y좌표 감소
			else if(d == 2) nx -= 1; //x좌표 감소
			else if(d == 3) ny += 1; //y좌표 증가
			
			if(map[nx][ny] == -1) return; //후진 후 위치가 벽이라면 리턴
			else clean(nx, ny, d); //벽이 아니라면 후진
		}
		//2a. 연속으로 4번 탐색하지 않았다면 빈 공간으로 전진
		else {
			clean(nx, ny, d);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//N, M입력
		String [] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
        //지도 2차원 배열 선언
		map = new int[N][M];
		
		//로봇 청소기 초기위치와 방향
		String [] xyd = br.readLine().split(" ");
		int x = Integer.parseInt(xyd[0]);
		int y = Integer.parseInt(xyd[1]);
		int d = Integer.parseInt(xyd[2]);
		
		//지도 정보 입력
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				if(Integer.parseInt(m[j]) == 1) map[i][j] = -1; //벽은 -1로 표시
				else map[i][j] = Integer.parseInt(m[j]);
			}
		}
		
		//청소 시작(초기위치, 방향)
		clean(x, y, d);
		
		//결과 출력
		bw.write(CLEAN + "\n");
		
		bw.close();
	}
}