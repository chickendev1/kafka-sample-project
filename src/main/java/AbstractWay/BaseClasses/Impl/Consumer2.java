package AbstractWay.BaseClasses.Impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import AbstractWay.BaseClasses.Core.BaseKafkaListener;

public class Consumer2 extends BaseKafkaListener{

	@Override
	public void onMessage(ConsumerRecord<String, String> paramConsumerRecord) {
		System.out.println("Consumer 2: " + paramConsumerRecord.value());
	}
}
