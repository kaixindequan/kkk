import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
			System.out.println(Thread.currentThread().getName()+"��׼�����ˣ����ԳԷ���");
		});

		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"�Ѿ�ϴ������");
			try { cyclicBarrier.await(); } catch (Exception e) { }
		},"AA").start();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"�Ѿ�ϴ������");
			try { cyclicBarrier.await(); } catch (Exception e) { }
		},"BB").start();
		new Thread(()->{
			try { Thread.sleep(5000); } catch (InterruptedException e1) { e1.printStackTrace(); }
			System.out.println(Thread.currentThread().getName()+"�Ѿ�ˢ������");
			try { cyclicBarrier.await(); } catch (Exception e) { }
		},"CC").start();
			
	}

}
