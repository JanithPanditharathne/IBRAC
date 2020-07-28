package com.zone24x7.ibrac.eas.publishers;

import com.zone24x7.ibrac.eas.util.StringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Kafka publisher
 */
public class KafkaEventPublisher implements EventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventPublisher.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    /**
     * Publish the given message to the given kafka topic.
     *
     * @param topicName     name of the topic to be published
     * @param data          message to publish
     * @param requestId     request Id
     * @return Processed data if everything succeeds, original data if not
     */
    @Override
    public void publishToTopic(String topicName, String data, String requestId) {
        ListenableFuture<SendResult<String, String>> future = this.template.send(topicName, data);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error(StringConstants.REQUEST_ID_LOG_MSG_PREFIX + "Failed to publish the message.", requestId );
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                LOGGER.info(StringConstants.REQUEST_ID_LOG_MSG_PREFIX + "Message successfully published.", requestId);
            }
        });
    }
}
