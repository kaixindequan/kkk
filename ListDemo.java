import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * �����̰߳�ȫ��List�ķ�ʽ
 *1.Vector
 *2.Collections.synchronizedList
 *3.java.util.concurrent.CopyOnWriteArrayList;
  */
public class ListDemo {

	public static void main(String[] args) {
		//List<String> list = new Vector<>();
		//List<String> list = Collections.synchronizedList(new ArrayList<>());
		List<String> list = new CopyOnWriteArrayList<>();
		for(int i=0; i<30;i++){
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			}).start();
		}
		//java.util.ConcurrentModificationException
		//�����޸��쳣
	}
}
