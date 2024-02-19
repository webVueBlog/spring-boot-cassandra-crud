package com.dada.cassandra.repository;

import com.dada.cassandra.model.SuperHero;

import java.util.List;

public interface SuperHeroQueryRepository {

    List<SuperHero> save();// 保存数据

    List<SuperHero> getAll();// 获取所有数据

    List<SuperHero> getSuperHeroByName(String name);// 根据名字获取数据

    SuperHero getOneSuperHeroByName(String name);// 根据名字获取单个数据

    List<SuperHero> getSuperHeroByNameLike(String name);// 根据名字模糊查询数据

    SuperHero getSingleSuperHeroBySuperName(String superName);// 根据超能力名称获取单个数据

    List<SuperHero> getSuperHeroByAgeGreaterThan(int age);// 根据年龄大于指定值获取数据

    List<SuperHero> getSuperHeroWhoCanFly(boolean canFly);// 根据是否会飞获取数据

}
