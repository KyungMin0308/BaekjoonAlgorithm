import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {

		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		//거스름돈
		int [] coin = { 2, 5 };
		
		//dp 배열
		int [] dp = new int[N+1];
		Arrays.fill(dp, 100001); //배열 초기화
		
		//거스름돈 계산
		dp[0] = 0;
		for(int i=0; i<coin.length; i++) {
			for(int j=coin[i]; j<=N; j++) {
				if(dp[j-coin[i]] != 100001) {
					dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
				}
			}
		}
		
		//결과 출력
		if(dp[N] == 100001)
			bw.write(String.valueOf(-1) + "\n");
		else
			bw.write(dp[N] + "\n");
		
		bw.close();
	}
}