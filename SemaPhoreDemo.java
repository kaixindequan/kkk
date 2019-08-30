import java.util.concurrent.Semaphore;

public class SemaPhoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		for(int i=1;i<7;i++){
			new Thread(()->{
				try {
					semaphore.acquire();//��ռ��Դ
					System.out.println(Thread.currentThread().getName()+"����ͣ����");
					Thread.sleep(3000);
					System.out.println(Thread.currentThread().getName()+"ͣ��3����뿪ͣ����");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					semaphore.release();//�ͷ���Դ
				}
				
			}).start();
			
			
		}
	}
}
