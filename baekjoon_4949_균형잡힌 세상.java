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

		Stack<Character> stack = new Stack<Character>();

		while (true) {
			//문자열 입력
			String str = br.readLine();
			if (str.equals("."))
				break; // 종료조건

			for (int i=0; i<str.length(); i++) {
                //문자가 괄호가 아니라면 continue
				if(str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '[' && str.charAt(i) != ']')
					continue;
				
				//스택이 비어있고, ")", "]" 라면 push
				if(stack.isEmpty()) {
					if(str.charAt(i) == ')' || str.charAt(i) == ']') {
						stack.push(str.charAt(i));
					}
				}
				
				//문자가 "(", "[" 라면 스택에 push
				if(str.charAt(i) == '(' || str.charAt(i) == '[') {
					stack.push(str.charAt(i));
				}
				
				//문자가 ")", "]" 라면 확인 후 pop 또는 break
				if(str.charAt(i) == ')') {
					char c = stack.peek();
					if(c == '(')
						stack.pop();
					else break;
				}
				if(str.charAt(i) == ']') {
					char c = stack.peek();
					if(c == '[')
						stack.pop();
					else break;
				}
			}
			
			//결과 출력
			if (stack.size() == 0) //스택 크기가 0이면 올바른 균형잡힌 문자열
				bw.write("yes\n");
			else
				bw.write("no\n");
			
			stack.clear(); //스택 비우기
		}

		bw.close();
	}
}