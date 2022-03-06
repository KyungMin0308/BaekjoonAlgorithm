import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		int [][] tri = new int[N][N];
		
		//정수 삼각형 입력
		for(int i=0; i<N; i++) {
			String [] str = br.readLine().split(" ");
			for(int j=0; j<str.length; j++) {
				tri[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		//합이 최대가 되는 경로에 있는 수의 합 구하기
		//아래에서 위로 올라가는 방식
		for(int i=tri.length-2; i>=0; i--) {
			for(int j=0; j<tri[i+1].length-1; j++) {
				//현재 위치값을 기준으로 아랫줄의 좌우 값들을 각각 더해 그 중 큰 값으로 현재 위치값을 갱신
				tri[i][j] = Math.max(tri[i][j]+tri[i+1][j], tri[i][j]+tri[i+1][j+1]);
			}
		}
		
		//결과 출력
		bw.write(tri[0][0] + "\n");
		
		bw.close();
	}
}