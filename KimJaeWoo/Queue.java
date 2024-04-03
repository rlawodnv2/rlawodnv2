public class Queue implements WebServiceInfra {
	private int[] arr;		// Queue 요소를 저장
	private int front;		// front은 Queue의 앞 요소
	private int rear;		// rear은 Queue의 마지막 요소
	private int capacity;	// Queue의 최대 용량
	private int count;		// Queue의 현재 크기
	
	// Queue를 초기화하는 생성자
	Queue(int size) {
		arr = new int[size];
		capacity = size;
		front = 0;
		rear = -1;
		count = 0;
	}
	
	// 앞 요소를 큐에서 빼는 유틸
	public int dequeue() {
		//Queue 언더플 확인
		if(isEmpty()) {
			log.info("Underflow\nProgram Terminated");
			System.exit(-1);
		}
		
		int x = arr[front];
		log.info("Removing {}", x);
		
		front = (front + 1) % capacity;
		count--;
		
		return x;
	}
	
	// Queue의 앞 요소를 반환
	public int peek(){
		if(isEmpty()) {
			log.info("Underflow\nProgram Terminated");
			System.exit(-1);
		}
		return arr[front];
	}
	
	// Queue의 크기를 반환
	public int size() {
		return count;
	}
	
	// Queue가 비어 있는지 확인
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	// Queue가 가득 찼는지 확인
	public boolean isFull() {
		return (size() == capacity);
	}
}
