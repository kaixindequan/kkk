import java.util.concurrent.Semaphore;

public class SemaPhoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		for(int i=1;i<7;i++){
			new Thread(()->{
				try {
					semaphore.acquire();//抢占资源
					System.out.println(Thread.currentThread().getName()+"进入停车场");
					Thread.sleep(3000);
					System.out.println(Thread.currentThread().getName()+"停车3秒后离开停车场");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					semaphore.release();//释放资源
				}
				
			}).start();
			
			
		}
	}
}
