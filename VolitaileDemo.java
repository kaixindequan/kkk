
public class VolitaileDemo {

	public static void main(String[] args) {
		
		Ticket t = new Ticket();
		for(int i=0;i<10;i++){
			new Thread(()->{
				if(t.getNumbers()>0){
					System.out.println(Thread.currentThread().getName()+"线程正在售票,还有余票:"+t.getNumbers());
					t.sileTickets();
					System.out.println(Thread.currentThread().getName()+"线程已经售票成功,还有余票:"+t.getNumbers());
				}else{
					System.out.println(Thread.currentThread().getName()+"不好意思已经售馨。。。");
				}
			},String.valueOf(i)).start();
		}
	}
		
		
	
}

class Ticket{
	
	private volatile int numbers=5;
	
	public void sileTickets(){
		numbers--;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	
}
