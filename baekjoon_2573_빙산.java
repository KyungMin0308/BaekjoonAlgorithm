import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M; //행, 열
	static int [][] iceberg; //빙산 배열
	static int [][] meltIceberg; //녹아야 하는 빙산 높이
	static boolean [][] visited; //방문 배열
	
	static int [] dx = { -1, 0, 1, 0 };
	static int [] dy = { 0, 1, 0, -1 };
	
	static int YEAR = 0; //빙산 녹는 시간
	
	//빙산 덩어리 확인
	public static void checkLump(int x, int y) {
		if(visited[x][y]) return; //이미 방문한 위치라면 리턴
		
		visited[x][y] = true; //현재 위치 방문처리
		
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
            //범위를 넘어가거나 호수라면 continue
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if(iceberg[nx][ny] == 0) continue;
			
            //연결된 부분 탐색
			checkLump(nx, ny);
		}
	}
	
	//모든 빙하가 녹았는지 확인
	public static boolean allMelt() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(iceberg[i][j] != 0) return false;
			}
		}
		return true;
	}
	
	//녹아야 하는 빙산 높이 확인
	public static void melt(int x, int y) {
		int count = 0; //빙산 주변 호수 갯수
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if(iceberg[nx][ny] != 0) continue;
			
			count++; //호수 갯수 카운트
		}
		
		meltIceberg[x][y] = count; //녹아야 하는 빙산 배열에 값 갱신
	}
	
	public static void main(String[] args) throws IOException {
		//행, 열 입력
		String [] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		iceberg = new int[N][M]; //빙산 배열 초기화
		
        //빙산 데이터 입력
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				iceberg[i][j] = Integer.parseInt(m[j]);
			}
		}
		
		int lump = 0; //빙산 덩어리
		boolean check = false; //모든 빙산이 녹았을 때 빙산 덩어리가 2개 미만이면 true
		while(true) {
			lump = 0; //덩어리 갯수 0으로 초기화
			
			//빙하 덩어리 확인
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(iceberg[i][j] != 0 && !visited[i][j]) {
						lump++; //덩어리 갯수 증가
						checkLump(i, j); //연결된 빙산 탐색
					}
				}
			}
			
			//모든 빙하가 녹았거나 덩어리가 2개 이상이라면 break
			if(allMelt() || lump >= 2) {
				if(lump < 2) { //모든 빙하가 녹는동안 덩어리가 2개이상으로 분리되지 않음
					check = true;
				}
				break;
			}
			
			//줄어드는 빙산 체크
			meltIceberg = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(iceberg[i][j] != 0) melt(i, j);
				}
			}
			
			//빙하가 녹음
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(meltIceberg[i][j] > iceberg[i][j]) iceberg[i][j] = 0; //줄어들어야 하는 높이보다 현재 빙산의 높이가 낮으면 0
					else iceberg[i][j] -= meltIceberg[i][j]; //줄어들어야 하는 높이만큼 빙산의 높이 감소
				}
			}
			
			YEAR++; //1년 경과
		}
		
		if(check) System.out.println(0); //전체 빙산이 녹는 동안 덩어리가 2개이상으로 분리된 적이 없다면 0
		else System.out.println(YEAR); //덩어리가 2개 이상이 된 년도 출력
	}
}