package com.kstreamsmongodb.kstreamsmongodb.configuration;

import com.kstreamsmongodb.kstreamsmongodb.configuration.databases.Collection;
import com.kstreamsmongodb.kstreamsmongodb.configuration.databases.Database;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

@RefreshScope
public class AppConfigProperties {

    private MongoDB mongoDB = new MongoDB();

    public MongoDB getMongoDB() {
        return mongoDB;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

    public static class MongoDB {
        private int port;
        private List<String> hosts;
        private String username;
        private String password;

        private Database database = new Database();
        private Collection collection = new Collection();

        public List<String> getHosts() {
            return hosts;
        }

        public void setHosts(List<String> hosts) {
            this.hosts = hosts;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Database getDatabase() {
            return database;
        }

        public void setDatabase(Database database) {
            this.database = database;
        }

        public Collection getCollection() {
            return collection;
        }

        public void setCollection(Collection collection) {
            this.collection = collection;
        }
    }
}