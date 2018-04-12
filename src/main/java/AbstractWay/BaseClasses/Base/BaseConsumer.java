package AbstractWay.BaseClasses.Base;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import AbstractWay.BaseClasses.Core.BaseKafkaListener;
import AbstractWay.BaseClasses.Core.KafkaConfig;

/**
 * Created by Thanh Mai
 */
public class BaseConsumer {
	private BaseKafkaListener _listener;
	private KafkaConfig _config;

	public BaseConsumer(BaseKafkaListener baseKafkaListener, KafkaConfig config) {
		_listener = baseKafkaListener;
		_config = config;
	}

	public void run() {
		Properties props = initProperties();
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		
		try {
			consumer.subscribe(Arrays.asList(_config.getTopics()));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(200);
				
				for (ConsumerRecord<String, String> record : records) {
					_listener.onMessage(record);
				}
				
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			consumer.close();
		}
	}

	private Properties initProperties() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", _config.getGroupId());
		props.put("enable.auto.commit", "true");
		// props.put("auto.commit.interval.ms",
		// Integer.valueOf(config.commitIntervalMs));

		// props.put("session.timeout.ms", Integer.valueOf(config.sessionTimoutMs));
		
		// [!!!] important I just intend to start consume latest message for "fresh
		// consumer" (= new consumer, there is no committed offset entry in zookeeper). In other
		// language like Java, Kafka client API supports to set option map, like
		// "auto.offset.reset=latest".
		props.put("auto.offset.reset", "earliest");
		
		
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}

}
