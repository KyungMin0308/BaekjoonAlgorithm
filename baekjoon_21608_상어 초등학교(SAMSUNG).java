import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Main {
	
	//좌석 클래스
	static class Seat {
		private int x; //x좌표
		private int y; //y좌표
		private int count; //인접한 비어있는 좌석 수
		
		public Seat(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
		public String toString() {
			return "(" + this.x + ", " + this.y + ", " + count + ")"; 
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int SCORE = 0; //만족도
	
	private static int [] students; //학생 번호
	private static int [][] seat; //좌석 맵
	
	private static HashMap<Integer, ArrayList<Integer>> studentMap = new HashMap<Integer, ArrayList<Integer>>(); //학생별 좋아하는 학생 정보 해시맵
	
	private static int [] dx = { -1, 0, 1, 0 }; //x축 이동방향
	private static int [] dy = { 0, 1, 0, -1 }; //y축 이동방향
	
	//만족도 계산
	public static void calc(int stdNum, int x, int y) {
		int cur = 0;
		ArrayList<Integer> list = studentMap.get(stdNum);
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= seat.length || ny < 0 || ny >= seat.length) continue;
			
			if(list.contains(seat[nx][ny])) cur++;
		}
		
		if(cur == 0) SCORE += 0;
		if(cur == 1) SCORE += 1;
		if(cur == 2) SCORE += 10;
		if(cur == 3) SCORE += 100;
		if(cur == 4) SCORE += 1000;
	}
	
	//빈좌석이랑 빈좌석 근처에 비어있는 좌석 수
	public static List<Entry<Seat, Integer>> isEmptySeat() {
		HashMap<Seat, Integer> seatMap = new HashMap<Seat, Integer>();
		
		for(int i=0; i<seat.length; i++) {
			for(int j=0; j<seat.length; j++) {
				if(seat[i][j] == 0) {
					int cnt = getNear(i, j);
					seatMap.put(new Seat(i, j, cnt), 0);
				}
			}
		}
		
		//정렬 수행
		List<Entry<Seat, Integer>> entrys = new ArrayList<Entry<Seat, Integer>>(seatMap.entrySet());
		Collections.sort(entrys, new Comparator<Entry<Seat, Integer>>() {
			@Override
			public int compare(Entry<Seat, Integer> e1, Entry<Seat, Integer> e2) {
				if(e1.getKey().count == e2.getKey().count) { //조건3
					if(e1.getKey().x == e2.getKey().x) return e1.getKey().y - e2.getKey().y;
					else return e1.getKey().x - e2.getKey().x;
				}
				else return e2.getKey().count - e1.getKey().count; //조건2
			}
		});
		
		return entrys;
	}
	
	//채워진 좌석이랑 근처 비어있는 좌석 수
	public static List<Entry<Seat, Integer>> isFilledSeat(int stdNum) {
		ArrayList<Integer> liked = studentMap.get(stdNum);
		HashMap<Seat, Integer> seatMap = new HashMap<Seat, Integer>();
		
		for(int i=0; i<seat.length; i++) {
			for(int j=0; j<seat.length; j++) {
				if(seat[i][j] == 0) continue;
				
				if(liked.contains(seat[i][j])) {
					//인접한 빈좌석을 확인
					for(int k=0; k<dx.length; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];
						
						if(ni < 0 || ni >= seat.length || nj < 0 || nj >= seat.length) continue;
						if(seat[ni][nj] != 0) continue;
						
						//빈좌석을 기준으로 주변에 빈칸이 몇 개인지 확인
						int count = getNear(ni, nj); //인접한 빈칸 수
						
						if(seatMap.size() == 0) {
							seatMap.put(new Seat(ni, nj, count), 1);
						}
						else {
							boolean isCheck = false; //이미 존재하는 좌표인지 여부
							for(Seat se: seatMap.keySet()) {
								if(se.x == ni && se.y == nj) { //좌표가 이미 존재한다면
									seatMap.put(se, seatMap.get(se)+1); //해당 좌표 등장횟수 갱신
									isCheck = true; //존재함 표시
									break; //탈출
								}
							}
							//새로운 좌표라면 추가
							if(!isCheck) seatMap.put(new Seat(ni, nj, count), 1);
						}
					}
				}
			}
		}
		
		//정렬 수행
		List<Entry<Seat, Integer>> entrys = new LinkedList<>(seatMap.entrySet());
		entrys.sort(new Comparator<Entry<Seat, Integer>>() {
			@Override
			public int compare(Entry<Seat, Integer> e1, Entry<Seat, Integer> e2) {
				if(e1.getValue() == e2.getValue()) {
					Seat s1 = e1.getKey();
					Seat s2 = e2.getKey();
					if(s1.count == s2.count) { //조건3
						if(s1.x == s2.x) return s1.y - s2.y;
						else return s1.x - s2.x;
					}
					else return s2.count - s1.count; //조건2
				}
				else return e2.getValue() - e1.getValue(); //조건1
			}
		});
		
		return entrys;
	}
	
	//인접한 빈칸 수
	public static int getNear(int x, int y) {
		int count = 0;
		for(int i=0; i<dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= seat.length || ny < 0 || ny >= seat.length) continue;
			if(seat[nx][ny] != 0) continue;
			
			count++;
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		
		//N 입력
		int N = Integer.parseInt(br.readLine());
		
		students = new int[N*N]; //학생 번호 배열
		seat = new int[N][N]; //좌석 배열
		
		//학생 번호, 좋아하는 학생 입력
		for(int i=0; i<N*N; i++) {
			String [] std = br.readLine().split(" ");
			studentMap.put(Integer.parseInt(std[0]), new ArrayList<Integer>());
			for(int j=1; j<std.length; j++) {
				studentMap.get(Integer.parseInt(std[0])).add(Integer.parseInt(std[j]));
			}
			students[i] = Integer.parseInt(std[0]);
		}
		
		//좌석 배치
		int x = 0, y = 0;
		for(int i=0; i<students.length; i++) {
			List<Entry<Seat, Integer>> filled = isFilledSeat(students[i]); //좌석에 좋아하는 학생이 앉아있는 경우
			if(filled.size() != 0) {
				x = filled.get(0).getKey().x;
				y = filled.get(0).getKey().y;
			}
			else {
				List<Entry<Seat, Integer>> empty = isEmptySeat(); //좌석에 좋아하는 학생이 앉아있지 않은 경우
				x = empty.get(0).getKey().x;
				y = empty.get(0).getKey().y;
			}
			
			seat[x][y] = students[i];
		}

		//만족도 계산
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				calc(seat[i][j], i, j);
			}
		}
		
		//결과 출력
		bw.write(SCORE + "\n");
		
		bw.close();
	}
}