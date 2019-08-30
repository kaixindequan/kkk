import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:������
 * ���Ի�ȡ�����̲߳�����������������ͨ��ѭ���ķ�ʽ���Ի�ȡ�����������ĺô��ǲ���Ҫ�����л��߳������ģ�ȱ����ѭ����ռ��ϵͳ��Դ
 * 
 * CAS
 * �̲߳�����Դ�� 
 * �̲߳�����
 * һֱѭ��ռ��cpu��Դ
 * @author LiuYaKai
 * @date 2019��8��22�� ����10:40:35 
 * @version 1.0.0.1
 */
public class SpinLockDemo {
	
	//ԭ��������
	private AtomicReference<Thread> atomicThread = new AtomicReference<>();
	
	//CAS
	public void myLock(){
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName()+"�߳̽����ˣ�invoked mylock");
		while(!atomicThread.compareAndSet(null, thread)){
			
		}
	}
	
	public void myUnlock(){
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName()+"�߳̽����ˣ�invoked myUnlock");
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

