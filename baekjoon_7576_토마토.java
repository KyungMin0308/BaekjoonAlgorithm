import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int [] dx = { -1, 0, 1, 0 };
	private static int [] dy = { 0, 1, 0, -1 };
	
	private static int [][] map;
	
	//모든 사과가 익었는지 확인
	public static boolean isAll() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == 0) return false; //모든 사과가 익지 않음 
			}
		}
		return true; //모든 사과가 익음
	}
	
	public static void main(String[] args) throws IOException {
		
		//M, N 입력
		String [] mn = br.readLine().split(" ");
		int M = Integer.parseInt(mn[0]);
		int N = Integer.parseInt(mn[1]);
		
		map = new int[N][M]; //map 선언
		Queue<int []> queue = new LinkedList<int []>(); //BFS를 위한 큐
		
		//map 정보 입력
		for(int i=0; i<N; i++) {
			String [] m = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				if(Integer.parseInt(m[j]) == 1) queue.offer(new int[] {i, j});
				map[i][j] = Integer.parseInt(m[j]);
			}
		}
		
		if(isAll()) { //처음부터 모든 사과가 익었다면 0 출력
			bw.write(0 + "\n");
		}
		else { //그렇지 않다면
			int day = -1; //모든 토마토가 익는데 걸린 일
			while(!queue.isEmpty()) {
				//큐가 빌때까지 반복 == 하루
				for(int i=queue.size(); i>0; i--) {
					int [] cur = queue.poll(); //현재 위치
					
					for(int j=0; j<dx.length; j++) {
						int nx = cur[0] + dx[j];
						int ny = cur[1] + dy[j];
						
						if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) continue;
						if(map[nx][ny] == 1) continue; //1이면 이미 익은 것
						if(map[nx][ny] == -1) continue; //-1이면 빈칸
						
						map[nx][ny] = 1; //현재 위치로 인해 익는 사과의 위치
						queue.offer(new int[] {nx, ny}); //큐에 추가
					}
				}
				
				day++; //하루 지남
			}
			
			if(isAll()) bw.write(day + "\n"); //모두 익었다면 day 출력
			else bw.write(-1 + "\n"); //모두 익지 않았다면 -1 출력
		}
		
		bw.close();
	}
}