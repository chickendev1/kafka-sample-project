import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by Thanh Mai
 */
public class Producer {
	public static void main(String[] args) {
		// set up the producer
		// http://kafka.apache.org/08/documentation/#configuration
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		// 0: send, don't care an acknowledgement from the broker
		// 1: send, wait to receive an acknowledgement from the leader replica
		// 2: send, wait to receive an acknowledgement from the all replica --> message
		// will never lost
		properties.put("request.required.acks", "1");
		// properties.put("request.timeout.ms", "50000"); --> default = 10000
		// properties.put("producer.type", "1"); --> 1: async, 2: sync --> default: 2
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
		for (int i = 0; i < 10; i++) {
            // send lots of messages without caring the previous messages has been sent successfully
            producer.send(new ProducerRecord<String, String>(
                    "KAFKA-SAMPLE-PROJECT-TOPIC-1",
                    "Message " + i + " at " + Double.toString(System.nanoTime() * 1e-9)));
            
            // flush make sure that all previous has been sent successfully base on the acks config
		}
		producer.close();
	}

}
