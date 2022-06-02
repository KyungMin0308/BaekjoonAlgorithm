import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, H;
	static int [][][] tomatoes;
	
	//상하좌우
	static int [] dx = { -1, 0, 1, 0 };
	static int [] dy = { 0, 1, 0, -1 };
	
	//위아래
	static int [] dh = { 1, -1 };
	
	//모든 토마토가 익었는지 확인
	public static boolean check() {
		for(int h=0; h<H; h++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					//익지 않음
					if(tomatoes[i][j][h] == 0) return false;
				}
			}
		}
		return true; //모든 토마토가 익음
	}
	
	public static void main(String[] args) throws IOException {
		
		String [] nmh = br.readLine().split(" ");
		M = Integer.parseInt(nmh[0]);
		N = Integer.parseInt(nmh[1]);
		H = Integer.parseInt(nmh[2]);
		
		tomatoes = new int[N][M][H]; //토마토 박스 배열
		Queue<int []> queue = new LinkedList<int []>(); //토마토 위치 저장 큐
		
		//토마토 정보 입력
		for(int h=0; h<H; h++) {
			for(int i=0; i<N; i++) {
				String [] m = br.readLine().split(" ");
				for(int j=0; j<M; j++) {
					tomatoes[i][j][h] = Integer.parseInt(m[j]);
					//최초 토마토 위치 저장
					if(Integer.parseInt(m[j]) == 1) queue.offer(new int [] {i, j, h});
				}
			}
		}
		
		if(check()) System.out.println(0); //처음부터 모두 익은 경우
		else {
			int day = -1;
			while(!queue.isEmpty()) {
				for(int i=queue.size(); i>0; i--) {
					int [] cur = queue.poll(); //현재 토마토 위치
					
					//상하좌우 확인
					for(int j=0; j<dx.length; j++) {
						int nx = cur[0] + dx[j];
						int ny = cur[1] + dy[j];
						
						//박스 범위를 넘어가거나 빈칸, 토마토가 있는 위치라면 continue
						if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
						if(tomatoes[nx][ny][cur[2]] == 1 || tomatoes[nx][ny][cur[2]] == -1) continue;
						
						//현재 위치의 토마토가 익음
						tomatoes[nx][ny][cur[2]] = 1;
						
						//큐에 익은 토마토 위치 추가
						queue.offer(new int [] {nx, ny, cur[2]});
					}
					
					//위아래 확인
					for(int j=0; j<dh.length; j++) {
						int nh = cur[2] + dh[j];
						
						//박스 범위를 넘어가거나 빈칸, 토마토가 있는 위치라면 continue
						if(nh < 0 || nh >= H) continue;
						if(tomatoes[cur[0]][cur[1]][nh] == 1 || tomatoes[cur[0]][cur[1]][nh] == -1) continue;
						
						//현재 위치의 토마토가 익음
						tomatoes[cur[0]][cur[1]][nh] = 1;
						
						//큐에 익은 토마토 위치 추가
						queue.offer(new int [] {cur[0], cur[1], nh});
					}
				}
				day++; //하루 경과
			}
			//정답 출력
			if(check()) System.out.println(day);
			else System.out.println(-1);
		}
	}
}