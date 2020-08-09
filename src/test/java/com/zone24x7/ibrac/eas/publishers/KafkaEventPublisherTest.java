package com.zone24x7.ibrac.eas.publishers;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test the Kafka Event Puvlisher.
 */
public class KafkaEventPublisherTest {

    private KafkaEventPublisher kafkaEventPublisher;
    private KafkaTemplate<String, Object> template;
    private DefaultKafkaProducerFactory defaultKafkaProducerFactory;
    ListenableFuture listenableFuture;
    private EventInputParams eventInputParams;

    private String topicName = "rectrack";
    private String eventData = "request";
    private String requestId = "12345";
    private String bootstrapServeIp = "10.101.16.86:9096";

    /**
     * Method to setup the dependencies for the test class
     */
    @BeforeEach
    void setup(){

        kafkaEventPublisher = new KafkaEventPublisher();
        eventInputParams = new EventInputParams(requestId,topicName, eventData, StringConstants.TEXT_PLAIN);

        template = mock(KafkaTemplate.class);
        listenableFuture = mock(ListenableFuture.class);
        defaultKafkaProducerFactory = mock(DefaultKafkaProducerFactory.class);

        ReflectionTestUtils.setField(kafkaEventPublisher, "bootstrapServeIp", bootstrapServeIp);
    }

    /**
     * Should publish the message when valid topic and valid message is passed.
     *
     */
    @Test
    public void should_publish_the_message_when_valid_topic_and_valid_message_passed(){

        when(template.send(topicName,eventData)).thenReturn(listenableFuture);

        long offset = 1;
        int partition = 1;


        SendResult<String, Object> sendResult = mock(SendResult.class);
        ListenableFuture<SendResult<String, Object>> responseFuture = mock(ListenableFuture.class);

        when(template.send(topicName, eventData)).thenReturn(responseFuture);
        doAnswer(invocationOnMock -> {
            ListenableFutureCallback listenableFutureCallback = invocationOnMock.getArgument(0);
            listenableFutureCallback.onSuccess(sendResult);
            assertThat(sendResult.getRecordMetadata().offset(), Matchers.is(offset));
            assertThat(sendResult.getRecordMetadata().partition(), Matchers.is(partition));
            return null;
        }).when(responseFuture).addCallback(any(ListenableFutureCallback.class));

        kafkaEventPublisher.publishToTopic(eventInputParams);
    }

}
