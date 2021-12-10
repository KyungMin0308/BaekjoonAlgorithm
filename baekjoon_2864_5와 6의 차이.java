import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//최소값 구하기
	private static int getMin(int a, int b) {
		StringBuffer minA = new StringBuffer(String.valueOf(a));
		StringBuffer minB = new StringBuffer(String.valueOf(b));
		
		//문자열을 돌면서 6은 모두 5로 치환
		for(int i=0; i<minA.length(); i++) {
			if(minA.charAt(i) == '6') {
				minA.replace(i, i+1, "5");
			}
		}
		for(int i=0; i<minB.length(); i++) {
			if(minB.charAt(i) == '6') {
				minB.replace(i, i+1, "5");
			}
		}
		
		//최소값 계산
		int min = Integer.parseInt(minA.toString()) + Integer.parseInt(minB.toString());
		
		return min;
	}
	
	//최대값 구하기
	private static int getMax(int a, int b) {
		StringBuffer maxA = new StringBuffer(String.valueOf(a));
		StringBuffer maxB = new StringBuffer(String.valueOf(b));
		
		//문자열을 돌면서 5는 모두 6으로 치환
		for(int i=0; i<maxA.length(); i++) {
			if(maxA.charAt(i) == '5') {
				maxA.replace(i, i+1, "6");
			}
		}
		for(int i=0; i<maxB.length(); i++) {
			if(maxB.charAt(i) == '5') {
				maxB.replace(i, i+1, "6");
			}
		}
		
		//최대값 계산
		int max = Integer.parseInt(maxA.toString()) + Integer.parseInt(maxB.toString());
		
		return max;
	}
	
	
	public static void main(String[] args) throws IOException{
		
		//A, B 입력
		String ab = br.readLine();
		StringTokenizer st = new StringTokenizer(ab);
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		//최소값은 5->5로 6->5로 보는 경우
		int min = getMin(A, B);
		
		//최대값은 5->6으로 6->6으로 보는 경우
		int max = getMax(A, B);
		
		//결과 출력
		bw.write(min + " " + max + "\n");
		
		bw.close();
	}
}