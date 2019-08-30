import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		//ExecutorService threadPool = Executors.newFixedThreadPool(5);//固定线程数的线程池,长期任务
		//ExecutorService threadPool1 = Executors.newSingleThreadExecutor();//单一线程的线程池如果有异常被关闭，则会重新开始一个新的线程
		//ExecutorService threadPool2 = Executors.newCachedThreadPool();//带缓存的线程池，这些池通常会提高执行许多短期异步任务的程序的性能。
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
