package AbstractWay.BaseClasses.Core;

public class KafkaConfig {
	String topic;
	String groupId;
	
	public String getTopics() {
		return topic;
	}
	public void setTopics(String topics) {
		this.topic = topics;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
