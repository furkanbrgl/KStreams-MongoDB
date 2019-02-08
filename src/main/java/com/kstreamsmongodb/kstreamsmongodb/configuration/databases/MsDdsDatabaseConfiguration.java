package com.kstreamsmongodb.kstreamsmongodb.configuration.databases;

import com.kstreamsmongodb.kstreamsmongodb.configuration.AppConfigProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import({DataSourcePoolMetadataProvidersConfiguration.class})
public class MsDdsDatabaseConfiguration {

    @Autowired
    private AppConfigProperties appConf;

    @Bean
    public MongoClient mongoClient() {

        List<ServerAddress> hostList = new ArrayList<>();
        for (String host : appConf.getMongoDB().getHosts()) {
            hostList.add(new ServerAddress(host, appConf.getMongoDB().getPort()));
        }

        if (appConf.getMongoDB().getUsername() != null && appConf.getMongoDB().getPassword() != null) {
            return new MongoClient(hostList,
                    new ArrayList<MongoCredential>() {
                        {
                            add(MongoCredential.createCredential(appConf.getMongoDB().getUsername(),
                                    appConf.getMongoDB().getDatabase().getConnectionDB(),
                                    appConf.getMongoDB().getPassword().toCharArray()));
                        }
                    });
        } else {
            return new MongoClient(hostList);
        }

    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongoClient(), appConf.getMongoDB().getDatabase().getSubscriptionsDB());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }


}