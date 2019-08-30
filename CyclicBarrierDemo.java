import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
			System.out.println(Thread.currentThread().getName()+"都准备好了，可以吃饭了");
		});

		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"已经洗好手了");
			try { cyclicBarrier.await(); } catch (Exception e) { }
		},"AA").start();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"已经洗好脸了");
			try { cyclicBarrier.await(); } catch (Exception e) { }
		},"BB").start();
		new Thread(()->{
			try { Thread.sleep(5000); } catch (InterruptedException e1) { e1.printStackTrace(); }
			System.out.println(Thread.currentThread().getName()+"已经刷好牙了");
			try { cyclicBarrier.await(); } catch (Exception e) { }
		},"CC").start();
			
	}

}
