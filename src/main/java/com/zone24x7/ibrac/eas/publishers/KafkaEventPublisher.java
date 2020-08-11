package com.zone24x7.ibrac.eas.publishers;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka event publisher
 */
@Component
public class KafkaEventPublisher implements EventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventPublisher.class);

    @Value("${eas.bootstrap.server.ip}")
    private String bootstrapServeIp;

    /**
     * Publish the given message to the given kafka topic.
     *
     * @param eventInputParams     eventInputParams pojo object
     */
    @Override
    public void publishToTopic(EventInputParams eventInputParams) {

        // Create a new KafkaTemplate object
        KafkaTemplate<String, Object> template = new KafkaTemplate<>(producerFactory());

        //send the message with the event topic and event data
        ListenableFuture<SendResult<String, Object>> future = template.send(eventInputParams.getTopic(), eventInputParams.getEventData());

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // Upon failing to publish the message, the logger will log this message
                LOGGER.error(StringConstants.REQUEST_ID_LOG_MSG_PREFIX + "Failed to publish the message.", eventInputParams.getRequestId() );
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringStringSendResult) {
                // Upon successful publishing of the message, the logger will log this message
                LOGGER.info(StringConstants.REQUEST_ID_LOG_MSG_PREFIX + "Message successfully published.", eventInputParams.getRequestId());
            }
        });
    }

    /**
     * Provides the producer config settings
     *
     * @return Map of producer configs.
     */
    private Map<String, Object> producerConfigs() {
        // Create new HashMap
        Map<String, Object> props = new HashMap<>();
        //Put the server ip to the Map
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServeIp);
        // Put the KEY_SERIALIZER_CLASS_CONFIG
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Put the VALUE_SERIALIZER_CLASS_CONFIG
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Return the hashmap with the producer configs
        return props;
    }

    /**
     * Provides the default producer factory
     *
     * @return producer factory
     */
    private ProducerFactory<String, Object> producerFactory() {
        // Return the default producer factory.
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
}
