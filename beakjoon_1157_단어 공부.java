import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {

		//문자열 입력
		String str = br.readLine();
		
		//소문자로 변환
		str = str.toLowerCase();
		
		//알파벳 개수만큼 배열 생성
		int [] alpha = new int[26];
		
		//각 알파벳이 몇번 등장했는지 배열에 저장
		for(int i=0; i<str.length(); i++) {
			int idx = str.charAt(i) - 'a';
			alpha[idx]++;
		}
		
		int maxIdx = 0; //가장 많이 나온 알바펫 인덱스
		int max = alpha[maxIdx];
		for(int i=1; i<alpha.length; i++) {
			if(alpha[i] == 0) continue; //0이면 등장하지 않았으므로 넘어감
			if(max < alpha[i]) {
				max = alpha[i];
				maxIdx = i;
			}
		}
		
		//가장 많이 나온 알파벳이 여러 개인지 판별
		boolean isEqual = false;
		for(int i=0; i<alpha.length; i++) {
			if(alpha[i] == 0) continue;
			if(maxIdx != i && max == alpha[i]) {
				isEqual = true;
				break;
			}
		}
		
		//isEqual이 true라면 가장 많이 나온 알파벳이 여러 개라는 의미
		if(isEqual) {
			bw.write("?\n");
		}
		else {
			char c = 'a';
			c += maxIdx;
			String res = Character.toString(c).toUpperCase();
			bw.write(res + "\n");
		}

		bw.close();
	}
}