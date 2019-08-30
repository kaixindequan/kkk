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
 * 实现定时任务的方式有两种
 * 一、java.util.Timer
 * 二、开源项目Quartz
 */
public class TimerAndQuartzDemo {
	public static void main(String[] args) {
		 try {
			 	//一、java.util包下的定时器
				Timer timer = new Timer();
				MyTask task = new MyTask();
				timer.schedule(task, 2000,1000);
				
				//========================================================
				
	            //二、开源项目Quartz
				//1. 创建一个JodDetail实例 将该实例与Hello job class绑定 (链式写法)
	            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class) // 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
	                    .withIdentity("myTrigger", "group1") // 定义name/group
	                    .build();
	            // 打印当前的时间
	            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	            Date date = new Date();
	            System.out.println("current time is :" + sf.format(date));
	
	            // 2. 2018年内每天11点18开始执行，每隔5s执行一次
	            CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
	                    .withIdentity("myTrigger", "group1")// 定义名字和组
	                    .withSchedule(    //定义任务调度的时间间隔和次数
	                            CronScheduleBuilder
	                            .cronSchedule("* * * * * ? *")
	                            )
	                    .build();
	
	            // 3. 创建scheduler
	            SchedulerFactory sfact = new StdSchedulerFactory();
	            Scheduler scheduler = sfact.getScheduler();
	
	            // 4. 将trigger和jobdetail加入这个调度
	            scheduler.scheduleJob(jobDetail, trigger);
	
	            // 5. 启动scheduler
	            scheduler.start();
	            
	            //scheduler执行2s后挂起
	            Thread.sleep(20000);
	            scheduler.shutdown(false);
	            //shutdown(true)表示等待所有正在执行的任务执行完毕后关闭Scheduler
	            //shutdown(false),即shutdown()表示直接关闭Scheduler
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
        //打印当前的时间
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


 