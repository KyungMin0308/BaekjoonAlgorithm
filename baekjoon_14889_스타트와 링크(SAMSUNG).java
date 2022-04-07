import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N; //인원수
	private static int MinDif = Integer.MAX_VALUE; //최소 능력치 차이
	private static int [][] S; //능력치 배열
	private static boolean [] visited; //조합을 위한 방문 배열
	
	//두 팀간 능력치 차이 계산
	public static void dif() {
		int start = 0; //스타트팀
		int link = 0; //링크팀
		
		for(int i=0; i<visited.length-1; i++) {
			for(int j=i+1; j<visited.length; j++) {
				if(visited[i] && visited[j]) { //방문표시가 되어 있다면 스타트팀
					start += S[i][j];
					start += S[j][i];
				}
				if(!visited[i] && !visited[j]) { //방문표시가 되어 있지 않다면 링크팀
					link += S[i][j];
					link += S[j][i];
				}
			}
		}
		
		int tmp = Math.abs(start - link); //능력치 차이 절대값
		
		MinDif = Math.min(MinDif, tmp); //최소값 갱신
	}
	
	//조합
	public static void comb(int idx, int depth) {
		if(depth == N/2) {
			//두 팀간 점수차이 구하고 리턴
			dif();
			return;
		}
		
		for(int i=idx; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(i+1, depth+1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//N 입력
		N = Integer.parseInt(br.readLine());
		
		//배열 선언
		S = new int[N][N];
		visited = new boolean[N];
		
		//S 배열 데이터 입력
		for(int i=0; i<N; i++) {
			String [] str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				S[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		comb(0, 0); //조합
		
		//결과 출력
		bw.write(MinDif + "\n");
		
		bw.close();
	}
}