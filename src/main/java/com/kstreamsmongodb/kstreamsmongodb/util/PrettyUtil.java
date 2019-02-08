package com.kstreamsmongodb.kstreamsmongodb.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.Locale;

@Component
public class PrettyUtil {

    private String groupName;

    @PostConstruct
    public void init() {
        this.groupName = getGrupName();
    }

    public String getClientId() {

        StringBuilder sbContainerId = new StringBuilder();
        sbContainerId.append(groupName).append('-');
        sbContainerId.append(getNodeName());

        String clientId = "Consumer-" + sbContainerId;

        return clientId;

    }

    public Subscription getUNExpectedData(String data) {
        Subscription s = new Subscription();
        s.setInsertionDate(new Date());
        s.setDataDesc("UNExpected Data : " + data);

        return s;
    }

    public String getGrupName() {
        return buildGroupName("test");
    }

    private String buildGroupName(String tag) {
        return tag.toLowerCase(Locale.ENGLISH) + '_' + getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + "1";
    }

    private String getNodeName() {
        String serverName = null;
        try {
            serverName = ManagementFactory.getRuntimeMXBean().getName();
        } catch (Exception e) {
        }
        if (serverName == null) {
            throw new RuntimeException("Can not determine server instance name.");
        }
        serverName = serverName.replace('@', '-');
        return serverName;
    }

    public boolean isDeleted(String ref) {
        if (ref != null) {
            if ("1".equals(ref.trim())) {
                return true;
            }
        }
        return false;
    }

}
