import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		//문자열 입력
		String str = br.readLine(); //원본 문자열
		String bomb = br.readLine(); //문자열 폭탄
		
		int bombSize = bomb.length(); //문자열 폭탄 길이
		
		Stack<Character> stack = new Stack<Character>(); //문자열 폭탄 검사 스택
		
		for(int i=0; i<str.length(); i++) {
			stack.push(str.charAt(i)); //스택에 문자 푸시

			//스택의 크기가 문자열 폭탄 길이보다 크거나 같다면 폭탄이 존재하는지 확인
			if(stack.size() >= bombSize) {
				boolean check = true; //폭탄 존재 여부
				
				for(int j=0; j<bombSize; j++) {
					//폭탄 문자와 다른 경우
					if(stack.get(stack.size()-bombSize + j) != bomb.charAt(j)) {
						check = false; //폭탄이 존재하지 않음
						break;
					}
				}
				
				//폭탄이 존재한다면
				if(check) {
					//폭탄 문자열 길이만큼 pop
					for(int j=0; j<bombSize; j++) {
						stack.pop();
					}
				}
			}
		}
		
		//정답 문자열 생성
		StringBuffer ans = new StringBuffer();
		while(!stack.isEmpty()) {
			ans.append(stack.pop());
		}
		
		//결과 출력
		if(ans.length() == 0) bw.write("FRULA\n");
		else bw.write(ans.reverse().toString() + "\n");
		
		bw.close();
	}
}