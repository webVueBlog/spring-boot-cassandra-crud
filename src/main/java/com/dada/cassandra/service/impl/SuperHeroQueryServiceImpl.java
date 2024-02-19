package com.dada.cassandra.service.impl;

import com.dada.cassandra.model.SuperHero;
import com.dada.cassandra.repository.SuperHeroQueryRepository;
import com.dada.cassandra.service.SuperHeroQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperHeroQueryServiceImpl implements SuperHeroQueryService {

    @Autowired
    private SuperHeroQueryRepository superHeroQueryRepository;// 注入SuperHeroQueryRepository

    @Override
    public List<SuperHero> save() {// 调用SuperHeroQueryRepository的save方法
        return superHeroQueryRepository.save();// 返回保存的SuperHero列表
    }

    @Override
    public List<SuperHero> getAll() {// 调用SuperHeroQueryRepository的getAll方法
        return superHeroQueryRepository.getAll();// 返回所有的SuperHero列表
    }

    @Override
    public List<SuperHero> getSuperHeroByName(String name) {// 调用SuperHeroQueryRepository的getSuperHeroByName方法
        return superHeroQueryRepository.getSuperHeroByName(name);// 返回名字为name的SuperHero列表
    }

    @Override
    public SuperHero getOneSuperHeroByName(String name) {
        return superHeroQueryRepository.getOneSuperHeroByName(name);// 返回名字为name的第一个SuperHero
    }

    @Override
    public List<SuperHero> getSuperHeroByNameLike(String name) {
        return superHeroQueryRepository.getSuperHeroByNameLike(name);// 返回名字包含name的SuperHero列表
    }

    @Override
    public SuperHero getSingleSuperHeroBySuperName(String superName) {
        return superHeroQueryRepository.getSingleSuperHeroBySuperName(superName);// 返回名字为superName的第一个SuperHero
    }

    @Override
    public List<SuperHero> getSuperHeroByAgeGreaterThan(int age) {
        return superHeroQueryRepository.getSuperHeroByAgeGreaterThan(age);// 返回年龄大于age的SuperHero列表
    }

    @Override
    public List<SuperHero> getSuperHeroWhoCanFly(boolean canFly) {
        return superHeroQueryRepository.getSuperHeroWhoCanFly(canFly);// 返回可以飞行的SuperHero列表
    }
}
