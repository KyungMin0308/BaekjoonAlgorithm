import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//숨은 위치와 시간을 저장하는 클래스
class Hide implements Comparable<Hide> {
	private int x; //현재 위치
	private int time; //시간
	
	public Hide(int x, int time) {
		this.x = x;
		this.time = time;
	}
	
	public int getX() { return this.x; }
	public int getTime() { return this.time; }
	
	public int compareTo(Hide other) {
		return this.time - other.time; //시간 기준 오름차순 정렬
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, K;
	static boolean [] visited = new boolean[100001]; //방문 배열
	
	static int [] dx = { -1, 1, 2 };
	
	public static void main(String[] args) throws IOException {
		
		String [] nk = br.readLine().split(" ");
		
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		
		PriorityQueue<Hide> pq = new PriorityQueue<Hide>(); //시간 순으로 오름차순 정렬을 위한 우선순위 큐
		
		int nx = 0; //이동 위치
		int time = 0; //이동 시간
		boolean check = false; //순간이동 여부
		
		pq.offer(new Hide(N, time)); //큐에 시작점 추가
		
		while(!pq.isEmpty()) {
			Hide cur = pq.poll();
			nx = cur.getX(); //cur[0]; //현재 위치
			time = cur.getTime(); //cur[1]; //현재 이동시간
			
            //현재 위치 방문 처리
            //큐에 추가함과 동시에 방문처리를 하게 되면
            //이미 방문한 위치에 대해 더 빠른 시간에 갈 수 있음에도 추가되지 않는 상황이 발생함
			visited[nx] = true;
			
			//동생을 찾았다면 break
			if(nx == K) break;
			
			//이동 가능한 방법대로 이동
			for(int i=0; i<dx.length; i++) {
				if(i < 2) { //걷기
					nx = cur.getX() + dx[i]; //다음 위치 계산
					check = false;
				}
				else { //순간이동
					nx = cur.getX() * dx[i]; //다음 위치 계산
					check = true;
				}
				
				//nx가 0보다 작을 수 없음
				//nx가 100000보다 클 수 없음
				if(nx < 0 || nx > 100000) continue;
				
				//아직 방문하지 않았다면 큐에 추가
				if(!visited[nx]) {
					if(!check) pq.offer(new Hide(nx, time+1)); //걸은 경우 시간이 1만큼 지남
					else pq.offer(new Hide(nx, time)); //순간이동한 경우 시간이 소요되지 않음
				}
			}
		}
		
		//정답 출력
		System.out.println(time);
	}
}