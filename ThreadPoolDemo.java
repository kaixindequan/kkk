import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		
		ExecutorService threadPool =new  ThreadPoolExecutor(2, 5, 0L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(3));
		
		try {
			for(int i=1;i<=9;i++){
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName()+"Ïß³Ì");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			threadPool.shutdown();
		}
	}
}
