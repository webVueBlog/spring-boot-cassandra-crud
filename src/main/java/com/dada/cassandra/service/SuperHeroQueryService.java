package com.dada.cassandra.service;

import com.dada.cassandra.model.SuperHero;

import java.util.List;

public interface SuperHeroQueryService {

    List<SuperHero> save();// 保存数据

    List<SuperHero> getAll();// 获取所有数据

    List<SuperHero> getSuperHeroByName(String name);// 根据名字获取数据

    SuperHero getOneSuperHeroByName(String name);// 根据名字获取一条数据

    List<SuperHero> getSuperHeroByNameLike(String name);// 根据名字模糊查询

    SuperHero getSingleSuperHeroBySuperName(String superName);// 根据特殊名称获取一条数据

    List<SuperHero> getSuperHeroByAgeGreaterThan(int age);// 根据年龄大于指定值获取数据

    List<SuperHero> getSuperHeroWhoCanFly(boolean canFly);// 根据会飞与否获取数据

}
