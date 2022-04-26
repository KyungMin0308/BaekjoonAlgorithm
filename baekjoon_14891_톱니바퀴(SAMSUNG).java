import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [][] gears = new int[4][8]; //톱니바퀴
	private static int [] dir; //회전 방향 저장(-1:반시계, 0: 회전X, 1:시계)
	
	private static int K = 0; //회전 횟수
	private static int SCORE = 0; //점수
	
	//톱니바퀴 회전 함수
	public static void rotate() {
		for(int i=0; i<dir.length; i++) {
			//회전하지 않음
			if(dir[i] == 0) continue;
			
			int [] tmp = new int[8]; //회전을 위한 배열
			
			//시계 방향 회전
			if(dir[i] == 1) {
				tmp[0] = gears[i][gears[i].length-1];
				for(int j=0; j<gears[i].length-1; j++) {
					tmp[j+1] = gears[i][j];
				}
			}
			//반시계 방향 회전
			else if(dir[i] == -1) {
				tmp[tmp.length-1] = gears[i][0];
				for(int j=1; j<gears[i].length; j++) {
					tmp[j-1] = gears[i][j];
				}
			}
			gears[i] = tmp; //회전 결과로 갱신
		}
	}
	
	//첫번째 두번째 맞다은 부분 인덱스: [0][2] & [1][6]
	//두번째 세번째 맞다은 부분 인덱스: [1][2] & [2][6]
	//세번째 네번째 맞다은 부분 인덱스: [2][2] & [3][6]
	public static void initDir(int num, int d) {
		dir = new int[4]; //배열 초기화
		
		int idx = num-1;
		dir[idx] = d;

		//첫번째 톱니바퀴가 회전(idx == 0)
		if(num == 1) {
			//2번부터 4번까지 순차적 확인
			if(gears[idx][2] != gears[idx+1][6]) dir[idx+1] = -dir[idx]; //2번
			if(dir[idx+1] != 0 && gears[idx+1][2] != gears[idx+2][6]) dir[idx+2] = -dir[idx+1]; //3번
			if(dir[idx+2] != 0 && gears[idx+2][2] != gears[idx+3][6]) dir[idx+3] = -dir[idx+2]; //4번
		}
		//두번째 톱니바퀴가 회전(idx == 1)
		else if(num == 2) {
			//1, 3번 확인 후, 3번이 회전하는 경우에만 4번 확인
			if(gears[idx-1][2] != gears[idx][6]) dir[idx-1] = -dir[idx]; //1번
			if(gears[idx][2] != gears[idx+1][6]) dir[idx+1] = -dir[idx]; //3번
			if(dir[idx+1] != 0 && gears[idx+1][2] != gears[idx+2][6]) dir[idx+2] = -dir[idx+1]; //4번 
		}
		//세번째 톱니바퀴가 회전(idx == 2)
		else if(num == 3) {
			//2, 4번 확인 후, 2번이 회전하는 경우에만 1번 확인
			if(gears[idx-1][2] != gears[idx][6]) dir[idx-1] = -dir[idx]; //2번
			if(gears[idx][2] != gears[idx+1][6]) dir[idx+1] = -dir[idx]; //4번
			if(dir[idx-1] != 0 && gears[idx-2][2] != gears[idx-1][6]) dir[idx-2] = -dir[idx-1]; //1번
		}
		//네번째 톱니바퀴가 회전(idx == 3)
		else if(num == 4) {
			//3번부터 1번까지 순차적 확인
			if(gears[idx-1][2] != gears[idx][6]) dir[idx-1] = -dir[idx]; //3번
			if(dir[idx-1] != 0 && gears[idx-2][2] != gears[idx-1][6]) dir[idx-2] = -dir[idx-1]; //2번
			if(dir[idx-2] != 0 && gears[idx-3][2] != gears[idx-2][6]) dir[idx-3] = -dir[idx-2]; //1번
		}
	}
	
	//점수 계산
	public static void calc() {
		if(gears[0][0] == 1) SCORE += 1;
		if(gears[1][0] == 1) SCORE += 2;
		if(gears[2][0] == 1) SCORE += 4;
		if(gears[3][0] == 1) SCORE += 8;
	}
	
	public static void main(String[] args) throws IOException {
		
		//톱니바퀴 초기 상태 입력
		for(int i=0; i<gears.length; i++) {
			String [] g = br.readLine().split("");
			for(int j=0; j<gears[0].length; j++) {
				gears[i][j] = Integer.parseInt(g[j]);
			}
		}
		
		//회전 횟수 및 방법 입력
		K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			String [] cmd = br.readLine().split(" ");

			int num = Integer.parseInt(cmd[0]); //회전할 톱니바퀴 번호
			int d = Integer.parseInt(cmd[1]); //회전할 방향(시계 or 반시계)
			
			initDir(num, d); //회전 배열 생성
			rotate(); //톱니바퀴 회전
		}
		
		calc(); //점수 계산
		
		bw.write(SCORE + "\n"); //결과 출력
		
		bw.close();
	}
}