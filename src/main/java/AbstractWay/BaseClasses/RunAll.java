package AbstractWay.BaseClasses;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import AbstractWay.BaseClasses.Base.BaseConsumer;
import AbstractWay.BaseClasses.Base.BaseProducer;
import AbstractWay.BaseClasses.Core.BaseKafkaListener;
import AbstractWay.BaseClasses.Core.KafkaConfig;
import AbstractWay.BaseClasses.Impl.Consumer1;
import AbstractWay.BaseClasses.Impl.Consumer2;

/**
 * Created by Thanh Mai
 */
public class RunAll {

	public static void main(String[] args) {
		KafkaConfig config = new KafkaConfig();
		config.setTopics("TEST_KAFKA_TOPIC");
		config.setGroupId("consumer_group");

		KafkaConfig config2 = new KafkaConfig();
		config2.setTopics("TEST_KAFKA_TOPIC");
		config2.setGroupId("consumer_group_2");

		BaseKafkaListener baseKafkaListener1 = new Consumer1();
		BaseKafkaListener baseKafkaListener2 = new Consumer2();

		BaseProducer producer = new BaseProducer(config);
		for (int i = 0; i < 10; i++) {
			producer.sendMessage("Message " + i);
		}

		ExecutorService threadPool = Executors.newCachedThreadPool();
		Runnable myRunnable = new Runnable() {
			public void run() {
				BaseConsumer consumer1 = new BaseConsumer(baseKafkaListener1, config);
				consumer1.run();
			}
		};

		Runnable myRunnable2 = new Runnable() {
			public void run() {
				BaseConsumer consumer2 = new BaseConsumer(baseKafkaListener2, config2);
				consumer2.run();
			}
		};

		threadPool.execute(myRunnable2);
		threadPool.execute(myRunnable);
	}
}
