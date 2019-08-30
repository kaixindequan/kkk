import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

	/**
	 * 第三种实现多线程的方式
	 * callBack接口
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				System.out.println("进入了callable");
				return 111;
			}
		};
		FutureTask<Integer> task= new FutureTask<Integer>(callable);
		Thread thread = new Thread(task);
		thread.start();
		System.out.println(task.get());
		
	}
}
