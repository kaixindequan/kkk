
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
			System.out.println(Thread.currentThread().getName()+"�Ѿ���ȡ"+lockA+"���ڳ��Ի�ȡ"+lockB);
			synchronized(lockB){
				System.out.println(Thread.currentThread().getName()+"�Ѿ���ȡ"+lockB+"���ڳ��Ի�ȡ"+lockA);
			}
		}
	}
}
