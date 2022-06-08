import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//접두어 체크 함수(true: 접두어 있음 / false: 접두어 없음)
	public static boolean numCheck(Set<String> numSet) {
		String num = "", sub = "";
		Iterator<String> it = numSet.iterator();
		while(it.hasNext()) {
			num = it.next();
			for(int i=0; i<num.length()-1; i++) {
				sub = num.substring(0, i+1); //접두어 생성
				if(numSet.contains(sub)) return true; //접두어가 번호 목록에 있다면 true 반환
			}
		}
		return false; //위에서 걸리지 않으면 false 리턴
	}
	
	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			Set<String> numSet = new TreeSet<String>(); //오름차순 정렬 저장
			boolean check = false;
			for(int j=0; j<n; j++) {
				String phone_num = br.readLine();
				numSet.add(phone_num);
			}
			
			check = numCheck(numSet); //일관성 체크
		
			if(!check) System.out.println("YES"); //일관성 있음
			if(check) System.out.println("NO"); //일관성 없음
		}
	}
}