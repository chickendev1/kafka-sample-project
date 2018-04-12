package AbstractWay.BaseClasses.Base;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import AbstractWay.BaseClasses.Core.KafkaConfig;

/**
 * Created by Thanh Mai
 */
public class BaseProducer {
	private KafkaConfig _config;

	public BaseProducer(KafkaConfig config) {
		_config = config;

	}

	public void sendMessage(String message) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "1");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		producer.send(new ProducerRecord<String, String>(_config.getTopics(), message));
		producer.close();
	}

}
