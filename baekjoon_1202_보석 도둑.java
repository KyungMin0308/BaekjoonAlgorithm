import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//보석 클래스
class Jewel implements Comparable<Jewel> {
	private int weight; //보석 무게
	private int price; //보석 가격
	
	public Jewel(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}
	
	public int getWeight() { return this.weight; }
	public int getPrice() { return this.price; }
	
	@Override
	public int compareTo(Jewel other) {
		//보석 무게 기준 오름차순
        return this.weight - other.weight;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
		String [] nk = br.readLine().split(" ");
		
		int N = Integer.parseInt(nk[0]); //보석 갯수
		int K = Integer.parseInt(nk[1]); //가방 갯수
		
		List<Jewel> jewelList = new ArrayList<Jewel>(); //보석 정보 저장
		List<Integer> bags = new ArrayList<Integer>(); //가방 무게 저장
		
		//보석 정보 입력
		for(int i=0; i<N; i++) {
			String [] wp = br.readLine().split(" ");
			int weight = Integer.parseInt(wp[0]);
			int price = Integer.parseInt(wp[1]);
			
			jewelList.add(new Jewel(weight, price));
		}
		
		//가방 무게 입력
		for(int i=0; i<K; i++) {
			bags.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(jewelList); //보석 정렬
		Collections.sort(bags); //가방 오름차순 정렬
		
		//가방에 들어갈 보석 저장 우선순위 큐
		//가격이 비싼 순으로 저장
		PriorityQueue<Integer> jewelInBag = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		long max = 0; //보석 가격의 합의 최대값
		int idx = 0; //보석 리스트 인덱스 -> 이미 확인한 보석은 다시 확인하지 않도록 하기 위해
		for(int i=0; i<bags.size(); i++) {
			//가방에 넣을 수 있는 보석 탐색
			//현재 보석의 무게가 가방 무게보다 작거나 같다면 가방에 넣을 수 있음
			while(idx < N && jewelList.get(idx).getWeight() <= bags.get(i)) {
				jewelInBag.offer(jewelList.get(idx).getPrice());
				idx++;
			}
			
			//가방이 비어있지 않다면 큐의 최상단의 보석이 가방에 넣을 수 있는 가장 비싼 보석이므로 합산
			if(!jewelInBag.isEmpty()) max += jewelInBag.poll();
		}
		
		//결과 출력
		System.out.println(max);
	}
}