import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:自旋锁
 * 尝试获取锁的线程不会立即阻塞，而是通过循环的方式尝试获取锁，这样做的好处是不需要来回切换线程上下文，缺点是循环会占用系统资源
 * 
 * CAS
 * 线程操作资源类 
 * 线程不阻塞
 * 一直循环占用cpu资源
 * @author LiuYaKai
 * @date 2019年8月22日 上午10:40:35 
 * @version 1.0.0.1
 */
public class SpinLockDemo {
	
	//原子引用类
	private AtomicReference<Thread> atomicThread = new AtomicReference<>();
	
	//CAS
	public void myLock(){
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName()+"线程进来了，invoked mylock");
		while(!atomicThread.compareAndSet(null, thread)){
			
		}
	}
	
	public void myUnlock(){
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName()+"线程进来了，invoked myUnlock");
		atomicThread.compareAndSet(thread, null);
	}
	
	public static void main(String[] args) {
		SpinLockDemo demo = new SpinLockDemo();
		
		new Thread(()->{
			demo.myLock();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			demo.myUnlock();
		},"aa").start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(()->{
			demo.myLock();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			demo.myUnlock();
		},"bb").start();
		
	}

}

