import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, L;
	
	static int [][] map; //지도
	
	static int LOAD = 0; //지날 수 있는 길의 갯수
	
	//행 방향 확인
	public static boolean checkRows(int row) {
		boolean [] bridge = new boolean[N]; //다리 설치 여부
		
		for(int i=0; i<N-1; i++) {
			int dif = map[row][i] - map[row][i+1]; //두 바닥의 높이차
			
			if(dif > 1 || dif < -1) return false; //높이차가 1보다 큼
			else if(dif == -1) { //올라가는 방향
				for(int j=0; j<L; j++) {
					if(i-j < 0 || bridge[i-j]) return false; //이미 다리가 설치됐거나 범위를 넘어감
					if(map[row][i] != map[row][i-j]) return false; //뒤에 있는 다리의 높이와 현재 다리 높이가 다름
					
					bridge[i-j] = true; //다리 설치
				}
			}
			else if(dif == 1) { //내려가는 방향
				for(int j=1; j<=L; j++) {
					if(i+j >= N || bridge[i+j]) return false; //이미 다리가 설치됐거나 범위를 넘어감
					if(map[row][i]-1 != map[row][i+j]) return false; //앞에 있는 다리의 높이와 현재 다리 높이가 다름
					
					bridge[i+j] = true; //다리 설치
				}
			}
		}
		
		return true; //설치 가능
	}
	
	//열 방향 확인
	public static boolean checkColumns(int col) {
		boolean [] bridge = new boolean[N];
		
		for(int i=0; i<N-1; i++) {
			int dif = map[i][col] - map[i+1][col]; //두 바닥의 높이차
			
			if(dif > 1 || dif < -1) return false; //높이차가 1보다 큼
			else if(dif == -1) { //올라가는 방향
				for(int j=0; j<L; j++) {
					if(i-j < 0 || bridge[i-j]) return false;
					if(map[i][col] != map[i-j][col]) return false;
					
					bridge[i-j] = true;
				}
			}
			else if(dif == 1) { //내려가는 방향
				for(int j=1; j<=L; j++) {
					if(i+j >= N || bridge[i+j]) return false;
					if(map[i][col]-1 != map[i+j][col]) return false;
					
					bridge[i+j] = true;
				}
			}
		}
		
		return true; //설치 가능
	}
	
	public static void main(String[] args) throws IOException {
		
		String [] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		L = Integer.parseInt(s[1]);
		
		//지도 입력
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String [] n = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(n[j]);
			}
		}
		
		//지날 수 있는 다리 확인
		for(int i=0; i<N; i++) {
			if(checkRows(i)) LOAD++; //행 방향 확인
			if(checkColumns(i)) LOAD++; //열 방향 확인
		}
		
		//결과 출력
		System.out.println(LOAD);
	}
}