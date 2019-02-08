package com.kstreamsmongodb.kstreamsmongodb.configuration.databases;

public class Database {

    private String connectionDB;

    private String subscriptionsDB;

    public String getSubscriptionsDB() {
        return subscriptionsDB;
    }

    public void setSubscriptionsDB(String subscriptionsDB) {
        this.subscriptionsDB = subscriptionsDB;
    }

    public String getConnectionDB() {
        return connectionDB;
    }

    public void setConnectionDB(String connectionDB) {
        this.connectionDB = connectionDB;
    }
}
