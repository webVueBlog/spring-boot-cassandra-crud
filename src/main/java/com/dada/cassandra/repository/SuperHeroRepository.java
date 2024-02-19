package com.dada.cassandra.repository;

import com.dada.cassandra.model.SuperHero;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository// 标记该接口为数据访问层 Repository是Spring Data Commons中的一个接口，用于标记一个类为数据访问层组件
public interface SuperHeroRepository extends CassandraRepository<SuperHero, Long> {// 实现类由 Spring Data 自动生成
    // 可以添加自定义的查询方法
    // ...
}
