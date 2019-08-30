import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws Exception {
		//���¼��������� ÿ�μ�1 ������Ϊֹ
		//���߳���Ҫ�ȴ����߳�ȫ����ɲ�ִ��
		//���ļ���ԭ�� AQS AbstractQueuedSynchronized �� java.util.concurrent ���ṩ��һ�ָ�Ч�ҿ���չ��ͬ������
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" �Ѿ�ϴ������");
			countDownLatch.countDown();
		},"AA").start();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" �Ѿ�ϴ������");
			countDownLatch.countDown();
		},"BB").start();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" �Ѿ�ˢ������");
			countDownLatch.countDown();
		},"CC").start();
	 
		countDownLatch.await();
	 
		System.out.println(Thread.currentThread().getName()+"���ԳԷ���");
	}
}
