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
	//��д��ReentrantReadWriteLock  ���Ա�ֻ֤��һ���߳�д����������ʱ����Զ���߳�һ���
	//д���� ԭ��+��ռ  ����д�����������̱�����һ�壬�м䲻�����ָ������
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	//������ReentratLock ֻ��һ���̲߳���
	//private Lock lock = new ReentrantLock();
	//synchronized����
	public  void setDate(String key,Object value){
		rwLock.writeLock().lock();
		//lock.lock();
		try{
			System.out.println(Thread.currentThread().getName()+"�߳� invoked setDate ����д�룺"+value);
			try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"�߳� invoked setDate д�����");
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
			System.out.println(Thread.currentThread().getName()+"�߳� invoked getDate ���ڶ�ȡ");
			try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName()+"�߳� invoked getDate ��ȡ���"+result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rwLock.readLock().unlock();
			//lock.unlock();
		}
	}
}
