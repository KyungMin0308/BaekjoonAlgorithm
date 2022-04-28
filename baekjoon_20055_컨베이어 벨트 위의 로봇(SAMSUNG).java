import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N; //컨베이어 벨트 길이
	private static int K; //종료 내구도 제한
	private static int STEP = 1; //단계
	
	private static int [] belt; //컨베이어 벨트 내구도
	private static boolean [] robot; //컨베이어 벨트 위 로봇 존재 유무
	
	//컨베이어 벨트 종료 함수
	public static boolean fin() {
		int count = 0;
		for(int i=0; i<belt.length; i++) {
			if(belt[i] == 0) count++;
			if(count >= K) return true;
		}
		return false;
	}
	
	//벨트 회전
	public static void rotate() {
		int [] belt2 = new int[belt.length];
		boolean [] robot2 = new boolean[robot.length];
		
		belt2[0] = belt[belt.length-1];
		robot2[0] = robot[robot.length-1];
		for(int i=0; i<belt.length-1; i++) {
			//로봇 회전
			if(robot[i] && i+1 == N-1) robot2[i+1] = false; //다음칸이 내리는 위치라면 내림
			else robot2[i+1] = robot[i];
			
			//내구도 회전
			belt2[i+1] = belt[i];
		}

		//회전한 결과로 갱신
		belt = belt2;
		robot = robot2;
	}
	
	//로봇 이동
	public static void moveRobot() {
		//가장 먼저 벨트에 올라간 로봇부터 이동하기 때문에 역순으로 탐색
		for(int i=robot.length-1; i>=0; i--) {
			if(!robot[i]) continue; //해당 칸에 로봇이 없으면 continue
			
			//로봇이 있고, 다음칸으로 이동 가능한 경우
			if(robot[i] && !robot[i+1] && belt[i+1] > 0) {
				if(i+1 == N-1) robot[i+1] = false; //다음칸이 내리는 칸인 경우
				else robot[i+1] = robot[i]; //다음칸이 내리는 칸이 아닌 경우
				robot[i] = false; //이동했으므로 현재 칸은 false;
				
				belt[i+1] -= 1; //내구도 감소
			}
		}
	}
	
	//올리는 칸에 로봇을 올림
	public static void putOn() {
		if(belt[0] > 0 && !robot[0]) {
			robot[0] = true; //로봇을 올리고
			if(belt[0] > 0) belt[0] -= 1; //내구도 감소
		}
	}
	
	public static void main(String [] args) throws IOException {
		
		//N, K 입력
		String [] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		
		//배열 초기화
		belt = new int[N*2];
		robot = new boolean[N*2];
		
		//내구도 입력
		String [] b = br.readLine().split(" ");
		for(int i=0; i<b.length; i++) {
			belt[i] = Integer.parseInt(b[i]);
		}
		
		//컨베이어 벨트 동작
		while(true) {
			//1. 벨트 회전
			rotate();
			
			//2. 벨트 위에 로봇들 한칸씩 이동
			moveRobot();
			
			//3. 올리는 위치에서 로봇을 올림
			putOn();
			
			//4. 종료
			if(fin()) break;
			
			STEP++; //단계 증가
		}
		
		//결과 출력
		bw.write(STEP + "\n");
		
		bw.close();
	}
}