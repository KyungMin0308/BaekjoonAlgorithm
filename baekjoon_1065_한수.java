import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		int count = 0; //한수 개수 카운트
		
		//한수 개수 세기
		for(int i=1; i<=N; i++) {
			if(1 <= i && i <= 99) //100미만의 수는 모두 한수
				count++;
			if(i >= 100) { //100보다 큰 경우
				boolean isHanNum = true; //한수여부 판단, 초기값 True
				String num = String.valueOf(i); //각 자릿수가 등차수열인지 확인하기 위해 String으로 변환
				for(int j=0; j<num.length()-2; j++) { //숫자 길이만큼 반복
					int n1 = num.charAt(j) - '0';
					int n2 = num.charAt(j+1) - '0';
					int n3 = num.charAt(j+2) - '0';
					if(n2 - n1 != n3 - n2) { //각 자리의 숫자 차이가 다르다면 등차수열이 아님
						isHanNum = false; //한수 아님
						break; //반복문 탈출
					}
				}
				if(isHanNum) //for문을 돌고 나온 후 True라면
					count++; //개수 증가
			}
		}
		
        //결과 출력
		bw.write(count + "\n");
		
		bw.close();
	}
}