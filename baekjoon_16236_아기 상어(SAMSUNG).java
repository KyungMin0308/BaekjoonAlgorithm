import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//물고기 클래스
class Fish implements Comparable<Fish> { //우선순위 큐 정렬을 위해 Comparable 구현
	private int x;
	private int y;
	private int dis; //물고기: 떨어진 거리 / 상어: 크기
	
	public Fish(int x, int y, int dis) {
		this.x = x;
		this.y = y;
		this.dis = dis;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getDistance() { return this.dis; } //물고기가 떨어진 거리 반환
	
	public int getSize() { return this.dis; } //상어 크기 반환
	
	public void setSharkLoc(int x, int y) { //상어 위치 갱신
		this.x = x;
		this.y = y;
	}
	
	public void setSharkSize(int size) { //상어 크기 갱신
		this.dis = size;
	}
	
	@Override
	public int compareTo(Fish other) {
		if(this.dis == other.dis) { //거리가 같다면
			if(this.x == other.x) return this.y - other.y; //가장 위에 있는 물고기가 여러 마리라면 가장 왼쪽에 있는 물고기
			else return this.x - other.x; //가장 위에 있는 물고기
		}
		else return this.dis - other.dis; //거리가 짧은 순서
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//지도 관련 변수
	static int N;
	static int [][] map;
	
	static Fish shark; //상어
	
	//상하좌우 탐색을 위한 배열
	static int [] dx = { -1, 0, 1, 0 };
	static int [] dy = { 0, 1, 0, -1 };
	
	static int TIME = 0; //시간
	
	//상어와 물고기 사이 가장 짧은 경로 구하는 BFS 함수
	//상어가 먹을 수 있는 물고기 위치에서 상어가 있는 위치까지 거리를 구함
	public static int bfs(int fx, int fy) {
		Queue<int []> queue = new LinkedList<int []>();
		boolean [][] visited = new boolean[N][N]; //방문 배열
		
		queue.offer(new int[] {fx, fy, 0}); //현재 위치, 거리 저장
		visited[fx][fy] = true; //현재 위치 방문 처리
		
		int size = shark.getSize(); //현재 상어 크기
		
		//큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			int [] cur = queue.poll(); //현재 물고기의 위치
			int curX = cur[0];
			int curY = cur[1];
			int curDis = cur[2];
			
			//상어가 있는 위치에 도착했다면 거리를 반환
			if(map[curX][curY] == 9) return curDis; 
			
			//상하좌우 탐색
			for(int i=0; i<dx.length; i++) {
				//다음 위치
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				
				//범위를 넘어가거나 상어가 아니면서 상어의 크기보다 큰 물고기라면 이동하지 못함
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if(map[nx][ny] != 9 && map[nx][ny] > size) continue;
				
				//아직 방문하지 않았다면
				if(!visited[nx][ny]) {
					queue.offer(new int[] {nx, ny, curDis+1}); //큐에 저장
					visited[nx][ny] = true; //방문 처리
				}
			}
		}
		
		return -1; //도달할 수 없음
	}
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		//물고기, 상어 정보 입력
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(m[j]);
				
				//상어 객체 생성, 상어의 초기 크기는 2
				if(Integer.parseInt(m[j]) == 9) shark = new Fish(i, j, 2);
			}
		}
		
		int count = 0; //상어가 먹은 물고기 수
		while(true) {
			PriorityQueue<Fish> fishPQ = new PriorityQueue<Fish>(); //상어가 먹을 물고기 저장 우선순위큐
			
			//먹을 수 있는 물고기 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//상어가 아니고, 물고기가 있으면서 상어의 크기보다 작은 물고기라면
					if(map[i][j] != 9 && map[i][j] != 0 && map[i][j] < shark.getSize()) {
						int dis = bfs(i, j); //상어와 떨어진 거리를 구함
						
						//우선순위큐에 저장
						if(dis != -1) fishPQ.offer(new Fish(i, j, dis));
					}
				}
			}
			
			if(fishPQ.size() == 0) break; //먹을 수 있는 물고기가 없음
			
			Fish cur = fishPQ.poll(); //상어가 먹을 물고기
			
			//물고기 위치
			int fx = cur.getX();
			int fy = cur.getY();
			
			//현재 상어의 위치
			int sx = shark.getX();
			int sy = shark.getY();
			
			//상어가 물고기를 먹음
			map[fx][fy] = 9;
			map[sx][sy] = 0;
			
			//상어 위치를 물고기가 있던 위치로 갱신
			shark.setSharkLoc(fx, fy);
			
			count++; //상어가 먹은 물고기 카운트
			
			//상어의 크기와 먹은 물고기 수가 같다면
			if(count == shark.getSize()) {
				count = 0; //먹은 물고기 수 초기화
				shark.setSharkSize(shark.getSize()+1); //상어 크기 증가
			}
			
			//시간 계산
			TIME += cur.getDistance();
		}

		//정답 출력
		System.out.println(TIME);
	}
}