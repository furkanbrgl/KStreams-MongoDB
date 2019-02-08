package com.kstreamsmongodb.kstreamsmongodb.configuration;

import com.kstreamsmongodb.kstreamsmongodb.util.PrettyUtil;
import com.sun.istack.internal.NotNull;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaStreamsConfig {

    @Autowired
    private PrettyUtil prettyUtil;

    @NotNull
    private String topic;

    private KStreamsConfig kstreamsConfig = new KStreamsConfig();

    private KConsumerConfig kconsumerConfig = new KConsumerConfig();

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public KStreamsConfig getKstreamsConfig() {
        return kstreamsConfig;
    }

    public void setKstreamsConfig(KStreamsConfig kstreamsConfig) {
        this.kstreamsConfig = kstreamsConfig;
    }

    public KConsumerConfig getKconsumerConfig() {
        return kconsumerConfig;
    }

    public void setKconsumerConfig(KConsumerConfig kconsumerConfig) {
        this.kconsumerConfig = kconsumerConfig;
    }

    public Properties getStreamProp() {

        Properties props = new Properties();

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, kstreamsConfig.getApplicationId());
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kstreamsConfig.getBootstrapServerConfig());
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, "1");
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, kstreamsConfig.getReplicationFactor());
        props.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, kstreamsConfig.getCommitInterval());

        props.put(ConsumerConfig.GROUP_ID_CONFIG, prettyUtil.getGrupName());
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, prettyUtil.getClientId());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kconsumerConfig.getOffsetReset());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kconsumerConfig.getCommitInterval());
        props.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, kconsumerConfig.getMaxAgeConfig());

        return props;
    }

    public static class KStreamsConfig {

        public int commitInterval;
        private String bootstrapServerConfig;
        private int replicationFactor;
        private String applicationId;

        public String getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }

        public int getCommitInterval() {
            return commitInterval;
        }

        public void setCommitInterval(int commitInterval) {
            this.commitInterval = commitInterval;
        }


        public String getBootstrapServerConfig() {
            return bootstrapServerConfig;
        }

        public void setBootstrapServerConfig(String bootstrapServerConfig) {
            this.bootstrapServerConfig = bootstrapServerConfig;
        }

        public int getReplicationFactor() {
            return replicationFactor;
        }

        public void setReplicationFactor(int replicationFactor) {
            this.replicationFactor = replicationFactor;
        }
    }

    public static class KConsumerConfig {
        private int commitInterval;
        private String offsetReset;
        private String valueDeserializerClass;
        private String keyDeserializerClass;
        private int maxAgeConfig;

        public int getCommitInterval() {
            return commitInterval;
        }

        public void setCommitInterval(int commitInterval) {
            this.commitInterval = commitInterval;
        }

        public String getOffsetReset() {
            return offsetReset;
        }

        public void setOffsetReset(String offsetReset) {
            this.offsetReset = offsetReset;
        }

        public String getValueDeserializerClass() {
            return valueDeserializerClass;
        }

        public void setValueDeserializerClass(String valueDeserializerClass) {
            this.valueDeserializerClass = valueDeserializerClass;
        }

        public String getKeyDeserializerClass() {
            return keyDeserializerClass;
        }

        public void setKeyDeserializerClass(String keyDeserializerClass) {
            this.keyDeserializerClass = keyDeserializerClass;
        }

        public int getMaxAgeConfig() {
            return maxAgeConfig;
        }

        public void setMaxAgeConfig(int maxAgeConfig) {
            this.maxAgeConfig = maxAgeConfig;
        }
    }

}
