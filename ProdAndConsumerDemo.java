import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列实现生产者消费者
 *
 * @author kaixindequan
 * @date 2019年8月30日 下午3:33:12 
 * @version 1.0.0.1
 */
public class ProdAndConsumerDemo {

	public static void main(String[] args) throws Exception {
		Mydata mydata = new Mydata(new ArrayBlockingQueue<>(3));
		new Thread(()->{
			System.out.println("生产线程启动。。。");
			try {
				mydata.myProd();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"AA").start();
		new Thread(()->{
			System.out.println("消费线程启动。。。");
			try {
				mydata.myConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"BB").start();
		Thread.sleep(5000);
		System.out.println();
		System.out.println();

		System.out.println("5秒后  停止生产者和消费者");
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
			data = atomicInteger.incrementAndGet()+"";//相当于++i
			returnValue = blockingQueue.offer(data, 2L,TimeUnit.SECONDS);
			if(returnValue){
				System.out.println(Thread.currentThread().getName()+" 生产消息成功"+data);
			}else{
				System.out.println(Thread.currentThread().getName()+" 生产消息失败"+data);
			}
			//一秒钟2个
			Thread.sleep(500);
		}
		System.out.println("停止了生产者");
	}
	public void myConsumer() throws Exception{
		String data = null;
		while(Flag){
			data = blockingQueue.poll(2L, TimeUnit.SECONDS);
			if(data == null || "".equals(data)){
				Flag = false;
				System.out.println(Thread.currentThread().getName()+"超过2秒没有从队列里获得消息，退出消费");
				System.out.println();
				System.out.println();
				return;
			}
			System.out.println(Thread.currentThread().getName()+" 消费"+data+"成功");
		}
		System.out.println("停止了消费者");
	}
	
	public void stop() throws Exception{
		this.Flag=false;
	}
}
