import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * ʵ�ֶ�ʱ����ķ�ʽ������
 * һ��java.util.Timer
 * ������Դ��ĿQuartz
 */
public class TimerAndQuartzDemo {
	public static void main(String[] args) {
		 try {
			 	//һ��java.util���µĶ�ʱ��
				Timer timer = new Timer();
				MyTask task = new MyTask();
				timer.schedule(task, 2000,1000);
				
				//========================================================
				
	            //������Դ��ĿQuartz
				//1. ����һ��JodDetailʵ�� ����ʵ����Hello job class�� (��ʽд��)
	            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class) // ����Job��ΪHelloQuartz�࣬����������ִ���߼�����
	                    .withIdentity("myTrigger", "group1") // ����name/group
	                    .build();
	            // ��ӡ��ǰ��ʱ��
	            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	            Date date = new Date();
	            System.out.println("current time is :" + sf.format(date));
	
	            // 2. 2018����ÿ��11��18��ʼִ�У�ÿ��5sִ��һ��
	            CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
	                    .withIdentity("myTrigger", "group1")// �������ֺ���
	                    .withSchedule(    //����������ȵ�ʱ�����ʹ���
	                            CronScheduleBuilder
	                            .cronSchedule("* * * * * ? *")
	                            )
	                    .build();
	
	            // 3. ����scheduler
	            SchedulerFactory sfact = new StdSchedulerFactory();
	            Scheduler scheduler = sfact.getScheduler();
	
	            // 4. ��trigger��jobdetail�����������
	            scheduler.scheduleJob(jobDetail, trigger);
	
	            // 5. ����scheduler
	            scheduler.start();
	            
	            //schedulerִ��2s�����
	            Thread.sleep(20000);
	            scheduler.shutdown(false);
	            //shutdown(true)��ʾ�ȴ���������ִ�е�����ִ����Ϻ�ر�Scheduler
	            //shutdown(false),��shutdown()��ʾֱ�ӹر�Scheduler
	            System.out.println("scheduler is shutdown?"+scheduler.isShutdown());
	      } catch (Exception e) {
	            e.printStackTrace();
	      }
	}
}
class HelloJob implements Job{
	public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //��ӡ��ǰ��ʱ��
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        System.out.println("current exec time is :"+sf.format(date));
    }
	
}
class MyTask extends TimerTask{
	@Override
	public void run() {
		System.out.println(System.currentTimeMillis()+"do...");
	}
}


 