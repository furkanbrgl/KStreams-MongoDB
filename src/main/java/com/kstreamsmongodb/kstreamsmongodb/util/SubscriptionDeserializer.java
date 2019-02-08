package com.kstreamsmongodb.kstreamsmongodb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kstreamsmongodb.kstreamsmongodb.configuration.StaticApplicationContext;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SubscriptionDeserializer<T> implements Deserializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Class<T> tClass;

    /**
     * Default constructor needed by Kafka
     */
    public SubscriptionDeserializer() {
    }

    public SubscriptionDeserializer(Class<T> subscriptionClass) {
        this.tClass = subscriptionClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
        tClass = (Class<T>) props.get("serializedClass");
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (bytes == null)
            return null;

        T data;
        try {
            data = objectMapper.readValue(bytes, tClass);
        } catch (Exception e) {
            String UNExpectedData = new String(bytes);
            logger.warn("Noticed UNExpected Data : " + UNExpectedData);
            logger.warn(e.getMessage());
            logger.error(e.getMessage());

            e.printStackTrace();
            data = (T) StaticApplicationContext.getContext().getBean(PrettyUtil.class).getUNExpectedData(UNExpectedData);
            //throw new SerializationException(e);
        }

        return data;
    }

    @Override
    public void close() {

    }
}