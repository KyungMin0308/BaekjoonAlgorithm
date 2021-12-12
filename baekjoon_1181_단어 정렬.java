import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {

		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		//N개 단어 입력
		String [] words = new String[N];
		for(int i=0; i<words.length; i++) {
			words[i] = br.readLine();
		}
		
		//정렬 기준 설정
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String s1, String s2) {
				//단어의 길이가 같다면 사전 순 정렬
				if(s1.length() == s2.length()) {
					return s1.compareTo(s2);
				}
				//단어의 길이가 다르다면 길이가 짧은 순으로 정렬
				else {
					return s1.length() - s2.length();
				}
			}
		};
		
		//정렬
		Arrays.sort(words, comparator);
		
		//출력(같은 단어가 있다면 한번만 출력)
		bw.write(words[0] + "\n");
		for(int i=1; i<words.length; i++) {
			if(words[i].equals(words[i-1])) continue;
			bw.write(words[i] + "\n");
		}

		bw.close();
	}
}