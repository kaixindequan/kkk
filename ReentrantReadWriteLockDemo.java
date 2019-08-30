import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		for(int i=0;i<10;i++){
			final int temp= i;
			new Thread(()->{
				myCache.setDate(temp+"", temp+"");
			},String.valueOf(i)).start();
		}
		for(int i=0;i<10;i++){
			final int temp= i;
			new Thread(()->{
				myCache.getDate(temp+"");
			},String.valueOf(i)).start();
		}
	}
}

class MyCache{
	
	private volatile Map<String,Object> map = new HashMap<>();
	//读写锁ReentrantReadWriteLock  可以保证只有一个线程写操作，读的时候可以多个线程一起读
	//写操作 原子+独占  就是写操作整个过程必须是一体，中间不允许被分隔被打断
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	//重入锁ReentratLock 只能一个线程操作
	//private Lock lock = new ReentrantLock();
	//synchronized重锁
	public  void setDate(String key,Object value){
		rwLock.writeLock().lock();
		//lock.lock();
		try{
			System.out.println(Thread.currentThread().getName()+"线程 invoked setDate 正在写入："+value);
			try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"线程 invoked setDate 写入完成");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rwLock.writeLock().unlock();
			//lock.unlock();
		}
	}
	
	public  void getDate(String key){
		rwLock.readLock().lock();
		//lock.lock();
		try{
			System.out.println(Thread.currentThread().getName()+"线程 invoked getDate 正在读取");
			try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName()+"线程 invoked getDate 读取完成"+result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rwLock.readLock().unlock();
			//lock.unlock();
		}
	}
}
