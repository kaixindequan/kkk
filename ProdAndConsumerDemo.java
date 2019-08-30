import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ��������ʵ��������������
 *
 * @author kaixindequan
 * @date 2019��8��30�� ����3:33:12 
 * @version 1.0.0.1
 */
public class ProdAndConsumerDemo {

	public static void main(String[] args) throws Exception {
		Mydata mydata = new Mydata(new ArrayBlockingQueue<>(3));
		new Thread(()->{
			System.out.println("�����߳�����������");
			try {
				mydata.myProd();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"AA").start();
		new Thread(()->{
			System.out.println("�����߳�����������");
			try {
				mydata.myConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"BB").start();
		Thread.sleep(5000);
		System.out.println();
		System.out.println();

		System.out.println("5���  ֹͣ�����ߺ�������");
		mydata.stop();
	}
}

class Mydata{
	private volatile boolean Flag = true;
	BlockingQueue<String> blockingQueue = null;
	private AtomicInteger atomicInteger = new AtomicInteger();	public Mydata(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	public void myProd() throws Exception{
		String data = null;
		boolean returnValue;
		while(Flag){
			data = atomicInteger.incrementAndGet()+"";//�൱��++i
			returnValue = blockingQueue.offer(data, 2L,TimeUnit.SECONDS);
			if(returnValue){
				System.out.println(Thread.currentThread().getName()+" ������Ϣ�ɹ�"+data);
			}else{
				System.out.println(Thread.currentThread().getName()+" ������Ϣʧ��"+data);
			}
			//һ����2��
			Thread.sleep(500);
		}
		System.out.println("ֹͣ��������");
	}
	public void myConsumer() throws Exception{
		String data = null;
		while(Flag){
			data = blockingQueue.poll(2L, TimeUnit.SECONDS);
			if(data == null || "".equals(data)){
				Flag = false;
				System.out.println(Thread.currentThread().getName()+"����2��û�дӶ���������Ϣ���˳�����");
				System.out.println();
				System.out.println();
				return;
			}
			System.out.println(Thread.currentThread().getName()+" ����"+data+"�ɹ�");
		}
		System.out.println("ֹͣ��������");
	}
	
	public void stop() throws Exception{
		this.Flag=false;
	}
}
