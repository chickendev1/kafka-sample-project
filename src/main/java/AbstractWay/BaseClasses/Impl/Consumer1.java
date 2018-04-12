package AbstractWay.BaseClasses.Impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import AbstractWay.BaseClasses.Core.BaseKafkaListener;

public class Consumer1 extends BaseKafkaListener{

	@Override
	public void onMessage(ConsumerRecord<String, String> paramConsumerRecord) {
		System.out.println("Consumer 1: " + paramConsumerRecord.value());
	}

}
