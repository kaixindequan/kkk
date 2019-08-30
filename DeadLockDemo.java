
public class DeadLockDemo {
	
	public static void main(String[] args) {
		String lockA = "lockA";
		String lockB = "lockB";
		new Thread(new MyLock(lockA,lockB),"AAAAAA").start();
		new Thread(new MyLock(lockB,lockA),"BBBBBBB").start();
	}
}

class MyLock implements Runnable{
	
	private String lockA;
	private String lockB;
	
	public MyLock(String lockA,String lockB){
		this.lockA=lockA;
		this.lockB=lockB;
	}
	
	@Override
	public void run() {
		synchronized(lockA){
			System.out.println(Thread.currentThread().getName()+"已经获取"+lockA+"正在尝试获取"+lockB);
			synchronized(lockB){
				System.out.println(Thread.currentThread().getName()+"已经获取"+lockB+"正在尝试获取"+lockA);
			}
		}
	}
}
