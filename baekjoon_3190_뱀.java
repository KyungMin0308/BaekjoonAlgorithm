import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

class Snake { //뱀 클래스
	private int x; //뱀 x좌표
	private int y; //뱀 y좌표
	
	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N; //게임판 크기
	static int K; //사과 갯수
	static int L; //뱀 방향 변환 횟수
	
	static int [][] map; //게임맵
	static Deque<Snake> snake = new LinkedList<Snake>(); //뱀 위치 좌표 저장
	static int snakeDir; //뱀 이동방향: 상(1), 하(2), 좌(3), 우(4)
	
	static int TIME = 0; //게임 시간
	
	//뱀 이동 함수
	public static boolean move(int time) {
		//이동하면서 사과가 있다면 뱀의 길이 증가
		int len = time - TIME; //이동하는 시간
		for(int i=0; i<len; i++) {
			TIME++; //시간 경과
			
			Snake cur = snake.peek(); //뱀의 머리
			int curX = cur.getX();
			int curY = cur.getY();
			
			//이동
			if(snakeDir == 1) curX--; //상
			else if(snakeDir == 2) curX++; //하
			else if(snakeDir == 3) curY--; //좌
			else if(snakeDir == 4) curY++; //우
			
			if(curX < 0 || curX >= N || curY < 0 || curY >= N) return false; //벽에 부딪힘
			if(map[curX][curY] == -1) return false; //뱀이 자신의 몸과 부딪힘
			
			//뱀 위치 정보 갱신
			if(map[curX][curY] == 1) { //사과가 있는 경우
				map[curX][curY] = -1; //뱀 표시
				snake.addFirst(new Snake(curX, curY));
			}
			else { //사과가 없는 경우
				map[curX][curY] = -1; //뱀 표시
				snake.addFirst(new Snake(curX, curY));
				
				cur = snake.pollLast();
				map[cur.getX()][cur.getY()] = 0; //빈칸으로
			}
		}
		
		return true; //이동 가능함
	}
	
	//방향 전환 함수
	public static int changeDir(String dl) {
		if(snakeDir == 1) { //상
			if(dl.equals("D")) return 4; //우
			else if(dl.equals("L")) return 3; //좌
		}
		else if(snakeDir == 2) { //하
			if(dl.equals("D")) return 3; //좌
			else if(dl.equals("L")) return 4; //우
		}
		else if(snakeDir == 3) { //좌
			if(dl.equals("D")) return 1; //상
			else if(dl.equals("L")) return 2; //하
		}
		else if(snakeDir == 4) { //우
			if(dl.equals("D")) return 2; //하
			else if(dl.equals("L")) return 1; //상
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		//뱀 시작 위치
		map[0][0] = -1;
		snake.offerFirst(new Snake(0, 0)); //뱀 시작 위치 저장
		snakeDir = 4; //초기 이동방향: 오른쪽
		
		//사과 위치 입력
		for(int i=0; i<K; i++) {
			String [] k = br.readLine().split(" ");
			int x = Integer.parseInt(k[0]) - 1;
			int y = Integer.parseInt(k[1]) - 1;
			
			map[x][y] = 1; //사과의 위치
		}
		
		L = Integer.parseInt(br.readLine());
		
		boolean isMove = true; //이동 가능 여부
		for(int i=0; i<L; i++) {
			String [] l = br.readLine().split(" ");
			int t = Integer.parseInt(l[0]); //움직이는 시간
			String dir = l[1]; //방향 전환
			
			isMove = move(t); //뱀 이동
			
			if(!isMove) break; //뱀이 이동하다가 종료 조건을 만나게 되면 종료
			
			//방향 전환
			snakeDir = changeDir(dir);
		}
		
		//방향 전환 중 종료되지 않았다면 마지막 방향으로 계속 이동
		if(isMove) {
			while(true) {
				TIME++; //시간 경과
				
				Snake cur = snake.peek(); //뱀의 머리
				int curX = cur.getX();
				int curY = cur.getY();
				
				//이동
				if(snakeDir == 1) curX--; //상
				else if(snakeDir == 2) curX++; //하
				else if(snakeDir == 3) curY--; //좌
				else if(snakeDir == 4) curY++; //우
				
				if(curX < 0 || curX >= N || curY < 0 || curY >= N) break; //벽에 부딪힘
				if(map[curX][curY] == -1) break; //뱀이 자신의 몸과 부딪힘
				
				if(map[curX][curY] == 1) { //사과가 있는 경우
					map[curX][curY] = -1; //뱀 표시
					snake.addFirst(new Snake(curX, curY));
				}
				else { //사과가 없는 경우
					map[curX][curY] = -1; //뱀 표시
					snake.addFirst(new Snake(curX, curY));
					
					cur = snake.pollLast();
					map[cur.getX()][cur.getY()] = 0; //빈칸으로
				}
			}
		}
		
		//정답 출력
		System.out.println(TIME);
	}
}