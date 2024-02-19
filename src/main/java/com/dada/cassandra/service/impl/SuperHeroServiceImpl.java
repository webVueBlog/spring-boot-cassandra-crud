package com.dada.cassandra.service.impl;

import com.dada.cassandra.model.SuperHero;
import com.dada.cassandra.repository.SuperHeroRepository;
import com.dada.cassandra.service.SuperHeroService;
import com.dada.cassandra.utils.HelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {// 实现接口

    @Autowired
    private SuperHeroRepository repository;// 作用是自动注入一个bean

    @Override
    public List<SuperHero> save() {// 保存数据

        List<SuperHero> superHeroes = repository.findAll();// 查询所有数据
        if (superHeroes.isEmpty()) {// 判断是否为空
            repository.saveAll(HelperUtil.getSuperHeroesData());// 保存数据
        }

        return repository.findAll();// 返回所有数据
    }

    @Override
    public List<SuperHero> findAll() {// 查询所有数据
        return repository.findAll();// 返回所有数据
    }

    @Override
    public SuperHero findById(Long id) {// 根据id查询数据
        return repository.findById(id).orElse(SuperHero.builder().build());// 返回查询到的数据，如果没有查询到，则返回一个空的SuperHero对象
    }

    @Override
    public SuperHero save(SuperHero superHero) {// 保存数据
        return repository.save(superHero);// 返回保存的数据
    }

    @Override
    public SuperHero update(SuperHero superHero) {// 更新数据
        return repository.save(superHero);// 返回更新后的数据
    }

    @Override
    public void delete(Long id) {// 删除数据
        repository.findById(id).ifPresent(superHero -> repository.delete(superHero));// 判断是否存在该id的数据，如果存在，则删除
    }
}