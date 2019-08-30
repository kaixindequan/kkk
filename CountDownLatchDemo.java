import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws Exception {
		//向下技术计数器 每次减1 减到零为止
		//主线程需要等待子线程全部完成才执行
		//核心技术原理 AQS AbstractQueuedSynchronized 是 java.util.concurrent 中提供的一种高效且可扩展的同步机制
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" 已经洗好手了");
			countDownLatch.countDown();
		},"AA").start();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" 已经洗好脸了");
			countDownLatch.countDown();
		},"BB").start();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" 已经刷好牙了");
			countDownLatch.countDown();
		},"CC").start();
	 
		countDownLatch.await();
	 
		System.out.println(Thread.currentThread().getName()+"可以吃饭了");
	}
}
