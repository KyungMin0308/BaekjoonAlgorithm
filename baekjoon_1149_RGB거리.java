import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {

		//집의 수(N) 입력
		int N = Integer.parseInt(br.readLine());
		
		//RGB 배열
		int [][] rgb = new int[N][3];

		//RGB 입력
		for(int i=0; i<rgb.length; i++) {
			String col = br.readLine();
			StringTokenizer st = new StringTokenizer(col);
			
			for(int j=0; j<rgb[i].length; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cost = 0; //비용 저장
		
		//최솟값 구하기
		for(int i=1; i<rgb.length; i++) { 
			rgb[i][0] = Math.min(rgb[i][0] + rgb[i-1][1], rgb[i][0] + rgb[i-1][2]); //0 -> 1, 2번째와 더한 값 중 작은 값 저장
			rgb[i][1] = Math.min(rgb[i][1] + rgb[i-1][0], rgb[i][1] + rgb[i-1][2]); //1 -> 0, 2번째와 더한 값 중 작은 값 저장
			rgb[i][2] = Math.min(rgb[i][2] + rgb[i-1][0], rgb[i][2] + rgb[i-1][1]); //2 -> 0, 1번째와 더한 값 중 작은 값 저장
			
			//비용의 최솟값 저장
			cost = Math.min(Math.min(rgb[i][0], rgb[i][1]), rgb[i][2]);
		}
		
		//결과 출력
		bw.write(cost + "\n");
		
		bw.close();
	}
}