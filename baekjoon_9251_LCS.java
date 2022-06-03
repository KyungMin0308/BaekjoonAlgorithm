import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//LCS(Longest Common Subsequence, 최장 공통 부분 수열)
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		//문자열 입력
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		//DP 배열 선언
		int [][] dp = new int[str1.length()+1][str2.length()+1];
		
		/*
		 * 점화식
		 * i, j는 1부터 시작
		 * if(Xi == Yj): dp[i][j] = dp[i-1][j-1] + 1
		 * if(Xi != Yj): dp[i][j] = max(dp[i-1][j], dp[i][j-1]) 
		 */
		for(int i=1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		
		System.out.println(dp[str1.length()][str2.length()]);
	}
}