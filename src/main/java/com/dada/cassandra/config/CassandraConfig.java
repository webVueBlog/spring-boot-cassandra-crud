package com.dada.cassandra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name: simple_crud}")
    private String keyspace;// 设置keyspace名称

    @Value("${spring.data.cassandra.contact-points: localhost}")
    private String contactPoint;

    @Value("${spring.data.cassandra.port: 9042}")
    private int port;


    @Override
    public String getContactPoints() {// 设置集群地址
        return contactPoint;// 127.0.0.1
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;// 设置表的创建策略
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {// 设置keyspace的创建策略
        return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(keyspace)
                .ifNotExists()// 设置keyspace的创建策略
                .with(KeyspaceOption.DURABLE_WRITES, true)// 设置keyspace的创建策略
                .withSimpleReplication(3L));// 设置副本策略
    }

    @Override
    protected String getLocalDataCenter() {// 设置本地数据中心名称
        return "datacenter1";
    }

    @Override
    protected String getKeyspaceName() {// 设置keyspace名称
        return keyspace;
    }

    @Override
    public String[] getEntityBasePackages() {// 设置实体类的包路径
        return new String[] {"com.dada.cassandra.model"};
    }

}