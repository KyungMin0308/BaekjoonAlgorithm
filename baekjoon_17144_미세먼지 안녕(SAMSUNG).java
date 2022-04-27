import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int R, C, T;
	private static int [][] map;
	private static int DUST = 0; //남아있는 미세먼지
	
	private static int [] dx = { -1, 0, 1, 0 };
	private static int [] dy = { 0, 1, 0, -1 };
	
	//미세먼지 전파
	public static void spread(Queue<int []> queue) {
		//미세먼지 전파 배열
		int [][] dustSpread = new int[map.length][map[0].length];
		while(!queue.isEmpty()) {
			int [] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];
			int dust = cur[2];
			
			int div = dust / 5; //나눠지는 미세먼지
			int num = 0; //퍼지는 개수
			
			for(int i=0; i<dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
				if(map[nx][ny] == -1) continue; //공기 청정기 쪽으로는 확산되지 않음
				
				dustSpread[nx][ny] += div;
				num++;
			}
			
			map[x][y] -= (num * div);
			if(map[x][y] <= 0) map[x][y] = 0;
		}
		
		//미세먼지 전파 후 맵으로 갱신
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] += dustSpread[i][j];
			}
		}
	}
	
	//남은 미세먼지 총합 계산
	public static void sum() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 0 || map[i][j] == -1) continue;
				DUST += map[i][j];
			}
		}
	}
	
	//반시계
	public static void rotate1(int [] arr) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int x = arr[0];
		int y = arr[1]; //0으로 고정
		
		for(int i=y; i<map[0].length; i++) { //아래
			if(map[x][i] == -1) list.add(0);
			else list.add(map[x][i]);
		}
		for(int i=x-1; i>=0; i--) { //오른쪽
			list.add(map[i][map[0].length-1]);
		}
		for(int i=map[0].length-2; i>=0; i--) { //위
			list.add(map[0][i]);
		}
		for(int i=1; i<x; i++) { //왼쪽
			list.add(map[i][y]);
		}
		
		//회전
		int idx = 0;
		for(int i=y+1; i<map[0].length; i++) { //아래
			map[x][i] = list.get(idx++);
		}
		for(int i=x-1; i>=0; i--) { //오른쪽
			map[i][map[0].length-1] = list.get(idx++);
		}
		for(int i=map[0].length-2; i>=0; i--) { //위
			map[0][i] = list.get(idx++);
		}
		for(int i=1; i<x; i++) { //왼쪽
			map[i][y] = list.get(idx++);
		}
	}
	
	//시계
	public static void rotate2(int [] arr) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int x = arr[0];
		int y = arr[1]; //0으로 고정
		
		for(int i=y; i<map[0].length; i++) { //위
			if(map[x][i] == -1) list.add(0);
			else list.add(map[x][i]);
		}
		for(int i=x+1; i<map.length; i++) { //오른쪽
			list.add(map[i][map[0].length-1]);
		}
		for(int i=map[0].length-2; i>=0; i--) { //아래
			list.add(map[map.length-1][i]);
		}
		for(int i=map.length-2; i>=x+1; i--) { //왼쪽
			list.add(map[i][y]);
		}
		
		//회전
		int idx = 0;
		for(int i=y+1; i<map[0].length; i++) { //위
			map[x][i] = list.get(idx++);
		}
		for(int i=x+1; i<map.length; i++) { //오른쪽
			map[i][map[0].length-1] = list.get(idx++);
		}
		for(int i=map[0].length-2; i>=0; i--) { //아래
			map[map.length-1][i] = list.get(idx++);
		}
		for(int i=map.length-2; i>=x+1; i--) { //왼쪽
			map[i][y] = list.get(idx++);
		}
	}
	
	public static void main(String [] args) throws IOException {
		
		String [] rct = br.readLine().split(" ");
		R = Integer.parseInt(rct[0]);
		C = Integer.parseInt(rct[1]);
		T = Integer.parseInt(rct[2]);
		
		map = new int[R][C]; //미세먼지 맵 초기화
		Queue<int []> queue = new LinkedList<int []>(); //미세먼지 위치 저장
		ArrayList<int []> air = new ArrayList<int []>(); //공기청정기 위치 저장
		
		//map 데이터 입력
		for(int i=0; i<R; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(m[j]);
				if(map[i][j] == -1) air.add(new int[] {i, j}); //공기청정기 위치
				if(map[i][j] != -1 && map[i][j] != 0) queue.offer(new int[] {i, j, map[i][j]}); //미세먼지 위치
			}
		}
		
		while(T > 0) {
			//미세먼지 전파
			spread(queue);
			
			//공기청정기 실행
			rotate1(air.get(0)); //위쪽, 반시계 방향
			rotate2(air.get(1)); //아래쪽, 시계 방향
			
			//새로운 미세먼지 위치 저장
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j] != -1 && map[i][j] != 0) queue.offer(new int[] {i, j, map[i][j]});
				}
			}
			
			T--; //시간 경과
		}
		
		//남아있는 미세먼지 양 계산
		sum();
		
		//결과 출력
		bw.write(DUST + "\n");
		
		bw.close();
	}
}