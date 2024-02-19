package com.dada.cassandra.repository.impl;

import com.dada.cassandra.model.SuperHero;
import com.dada.cassandra.repository.SuperHeroQueryRepository;
import com.dada.cassandra.utils.HelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SuperHeroQueryRepositoryImpl implements SuperHeroQueryRepository {

    @Autowired
    private CassandraOperations cassandraTemplate;// 这个是Spring Data Cassandra提供的模板类，用于执行CQL查询和操作。

    @Override
    public List<SuperHero> save() {// 保存数据到Cassandra数据库
        List<SuperHero> superHeroes = cassandraTemplate.select(Query.empty(), SuperHero.class);// 查询所有的数据
        if (superHeroes.isEmpty()) {// 如果数据为空，则插入数据
            cassandraTemplate.insert(HelperUtil.getSuperHeroesData());// 插入数据
        }

        return cassandraTemplate.select(Query.empty(), SuperHero.class);// 再次查询所有的数据
    }

    @Override
    public List<SuperHero> getAll() {// 查询所有的数据
        return cassandraTemplate.select(Query.empty(), SuperHero.class);// 查询所有的数据
    }

    @Override
    public List<SuperHero> getSuperHeroByName(String name) {// 根据名字查询数据
        return cassandraTemplate.select(Query.query(Criteria.where("name").is(name)).withAllowFiltering(), SuperHero.class);// 根据名字查询数据
    }

    @Override
    public SuperHero getOneSuperHeroByName(String name) {
        return cassandraTemplate.selectOne(Query.query(Criteria.where("name").is(name)).withAllowFiltering(), SuperHero.class);// 根据名字查询一条数据
    }

    @Override
    public List<SuperHero> getSuperHeroByNameLike(String name) {
        return cassandraTemplate.select(Query.query(Criteria.where("name").like(name)).withAllowFiltering(), SuperHero.class);// 根据名字模糊查询数据
    }

    @Override
    public SuperHero getSingleSuperHeroBySuperName(String superName) {
        return cassandraTemplate.selectOne(Query.query(Criteria.where("super_name").is(superName)).withAllowFiltering(), SuperHero.class);
        // 根据超能力名称查询一条数据
    }

    @Override
    public List<SuperHero> getSuperHeroByAgeGreaterThan(int age) {
        return cassandraTemplate.select(Query.query(Criteria.where("age").gt(age)).withAllowFiltering(), SuperHero.class);// 根据年龄大于某个值查询数据
    }

    @Override
    public List<SuperHero> getSuperHeroWhoCanFly(boolean canFly) {// 根据是否会飞查询数据
        List<SuperHero> superHeroList = cassandraTemplate.select(Query.empty(), SuperHero.class);// 查询所有数据
        return superHeroList.stream().filter(superHero -> superHero.getSuperPowers().isCanFly() == canFly).collect(Collectors.toList());// 过滤出会飞的数据
    }
}
