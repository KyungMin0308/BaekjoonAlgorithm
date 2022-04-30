import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int SHARK = 0; //상어가 먹을 수 있는 물고기 최대값 저장

	//방향 배열(0번 인덱스 사용X)
	static int [][] dir = {
		{ }, //0번 인덱스(사용X)
		{ -1, 0 }, //위(1) ↑
		{ -1, -1 }, //왼쪽 위 대각선(2) ↖
		{ 0, -1 }, //왼쪽(3) ←
		{ 1, -1 }, //왼쪽 아래 대각선(4) ↙
		{ 1, 0 }, //아래(5) ↓
		{ 1, 1 }, //오른쪽 아래 대각선(6) ↘
		{ 0, 1 }, //오른쪽(7) →
		{ -1, 1 } //오른쪽 위 대각선(8) ↗
	};
	
	//상어 이동 가능 여부 판단
	public static boolean sharkMove(int x, int y, int sharkDir, int [][] fish) {
		for(int i=1; i<4; i++) {
			int sx = x + (dir[sharkDir][0] * i);
			int sy = y + (dir[sharkDir][1] * i);
			
			//범위를 넘어가면 continue
			if(sx < 0 || sx >=4 || sy < 0 || sy >= 4) continue;
			
			if(fish[sx][sy] != 0) return true; //이동 가능
		}
		//위에서 걸리지 않으면 이동 가능한 방향의 모든 값이 0이므로 이동 불가능
		return false;
	}

	//DFS 시작
	public static void shark(int x, int y, int sum, int sharkDir, int [][] fish, int [][] fishDir, HashMap<Integer, int[]> fishMap) {
		//물고기 이동(번호 순서대로)
		for(int key=1; key<=16; key++) {
			if(!fishMap.containsKey(key)) continue;
			
			int curFish = key; //현재 물고기
			int curX = fishMap.get(key)[0]; //x좌표
			int curY = fishMap.get(key)[1]; //y좌표
			
			for(int i=0; i<8; i++) {
				int fx = curX + dir[fishDir[curX][curY]][0];
				int fy = curY + dir[fishDir[curX][curY]][1];
				
				//범위를 넘어가거나 상어가 있는 칸이라면 이동 불가능
				if(fx < 0 || fx >= 4 || fy < 0 || fy >= 4 || fish[fx][fy] == -1) {
					fishDir[curX][curY] = (fishDir[curX][curY] % 8) + 1; //45도 회전
					continue;
				}
				else {
					//fishMap의 물고기 위치 갱신
					if(fish[fx][fy] == 0) {
						fishMap.put(curFish, new int[] {fx, fy});
					}
					else {
						fishMap.put(curFish, new int[] {fx, fy});
						fishMap.put(fish[fx][fy], new int[] {curX, curY});
					}

					//물고기 번호 및 방향 교환
					int fi = fish[curX][curY];
					fish[curX][curY] = fish[fx][fy];
					fish[fx][fy] = fi;
					
					int di = fishDir[curX][curY];
					fishDir[curX][curY] = fishDir[fx][fy];
					fishDir[fx][fy] = di;
					
					break; //물고기는 한 칸만 이동 가능하므로 break
				}
			}
		}

		//백트래킹을 위한 원본 복사
		int [][] copyFish = copy(fish);
		int [][] copyFishDir = copy(fishDir);
		HashMap<Integer, int []> copyFishMap = copy(fishMap);

		//상어 이동
		if(!sharkMove(x, y, sharkDir, fish)) { //이동 불가한 경우
			SHARK = Math.max(sum, SHARK); //최대값 갱신
			return; //종료
		}
		else {
			//이동 가능한 모든 위치 확인
			for(int i=1; i<4; i++) {
				//위치 이동
				int sx = x + (dir[sharkDir][0] * i);
				int sy = y + (dir[sharkDir][1] * i);

				//이동한 위치가 범위를 넘어가거나 해당 위치에 물고기가 없다면 continue
				if(sx < 0 || sx >= 4 || sy < 0 || sy >= 4) continue;
				if(fish[sx][sy] == 0) continue;
				
				//물고기를 먹을 수 있다면
				int eat = fish[sx][sy]; //먹어야 할 물고기 번호
				fishMap.remove(fish[sx][sy]); //물고기 제거
				fish[sx][sy] = -1; //상어 이동
				fish[x][y] = 0; //기존 상어 위치 0으로 바꿈
				
				//탐색
				shark(sx, sy, sum+eat, fishDir[sx][sy], fish, fishDir, fishMap);
				
				//원본 상태로 복원
				fishMap = copy(copyFishMap);
				fish = copy(copyFish);
				fishDir = copy(copyFishDir);
			}
		}
	}
	
	//깊은 복사를 위한 copy 함수(fish, fishDir 용)
	public static int [][] copy(int [][] arr) {
		int [][] copy = new int[arr.length][arr.length];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}
	
	//깊은 복사를 위한 copy 함수(fishMap 용)
	public static HashMap<Integer, int []> copy(HashMap<Integer, int []> hm) {
		HashMap<Integer, int []> copy = new HashMap<Integer, int []>();
		for(int key: hm.keySet()) {
			copy.put(key, hm.get(key));
		}
		return copy;
	}
	
	public static void main(String[] args) throws IOException {
		
		int [][] fish = new int[4][4]; //물고기 번호
		int [][] fishDir = new int[4][4]; //물고기 방향
		HashMap<Integer, int []> fishMap = new HashMap<Integer, int []>(); //물고기 이동 순서 및 위치
		
		//데이터 입력
		for(int i=0; i<4; i++) {
			String [] fd = br.readLine().split(" ");
			for(int j=0; j<4; j++) {
				fish[i][j] = Integer.parseInt(fd[j*2]);
				fishDir[i][j] = Integer.parseInt(fd[(j*2)+1]);
				
				fishMap.put(fish[i][j], new int[] {i, j});
			}
		}

		//상어는 -1
		SHARK += fish[0][0]; // (0,0)위치의 물고기를 먹고 시작
		fishMap.remove(fish[0][0]); //(0,0)위치 물고기 정보 제거
		int sharkDir = fishDir[0][0]; //상어가 이동할 초기 방향
		fish[0][0] = -1; //상어가 있는 칸
		
		//탐색 시작
		shark(0, 0, SHARK, sharkDir, fish, fishDir, fishMap);
		
		//결과 출력
		bw.write(SHARK + "\n");
		
		bw.close();
	}
}