package AbstractWay.BaseClasses.Core;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public abstract class BaseKafkaListener 
{
  public abstract void onMessage(ConsumerRecord<String, String> paramConsumerRecord);
}
