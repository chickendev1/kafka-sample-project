import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Created by Thanh Mai
 */
public class Consumer {
	public static void main(String[] args) {
		// set up the consumer 
		// http://kafka.apache.org/08/documentation/#configuration
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("zk.connect", "127.0.0.1:2181");
		properties.put("group.id", "test-consumer-group");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		//enable.auto.commit
		//auto.offset.reset

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList("KAFKA-SAMPLE-PROJECT-TOPIC-1", "topic-test"));
		try {
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(200);
			for (ConsumerRecord<String, String> record: records) {
				if (record.topic().equals("KAFKA-SAMPLE-PROJECT-TOPIC-1")) {
					System.out.println("KAFKA-SAMPLE-PROJECT-TOPIC-1 " + record.value());
				} else if (record.topic().equals("topic-test")) {
					System.out.println("topic-test " + record.value());
				} else {
					System.out.println("wrong-topic " + record.value());
				}
			}
		}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			consumer.close();
		}
	}

}
