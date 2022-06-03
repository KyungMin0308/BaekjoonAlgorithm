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
		String str3 = br.readLine();
		
		//문자열 길이
		int n = str1.length();
		int m = str2.length();
		int h = str3.length();
		
		//DP 배열 선언
		//3개의 문자열을 동시에 비교하기 위해 3차원 배열 사용
		int [][][] dp = new int[n+1][m+1][h+1];
		
		/*
		 * 점화식
		 * i, j, k는 1부터 시작
		 * if(Xi == Yj == Zk): dp[i][j][k] = dp[i-1][j-1][k-1] + 1
		 * if(Xi != Yj != Zk): dp[i][j][k] = max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1]) 
		 */
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				for(int k=1; k<=h; k++)
				if(str1.charAt(i-1) == str2.charAt(j-1) && str2.charAt(j-1) == str3.charAt(k-1)) {
					dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
				}
				else {
					dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
					
				}
			}
		}
		
		System.out.println(dp[n][m][h]);
	}
}