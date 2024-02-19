package com.dada.cassandra.service;


import com.dada.cassandra.model.SuperHero;

import java.util.List;

public interface SuperHeroService {

    List<SuperHero> save();// 批量保存

    List<SuperHero> findAll();// 查询所有

    SuperHero findById(Long id);// 根据id查询

    SuperHero save(SuperHero superHero);// 保存

    SuperHero update(SuperHero superHero);// 更新

    void delete(Long id);// 根据id删除

}