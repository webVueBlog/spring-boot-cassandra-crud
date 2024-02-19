package com.dada.cassandra.utils;

import com.dada.cassandra.model.SuperHero;
import com.dada.cassandra.model.SuperPowers;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class HelperUtil {

    private HelperUtil() {//作用是防止该类被实例化
    }

    public static List<SuperHero> getSuperHeroesData() {//获取超级英雄数据
        return superHeroesSupplier.get();//调用Supplier的get方法
    }

    private static final Supplier<List<SuperHero>> superHeroesSupplier = () ->//SuperHero.builder()构造器链
            Arrays.asList(//SuperHero.builder()构造器链
                    SuperHero.builder().id(1L).name("Bruce").superName("Hulk").profession("Doctor").age(50)
                            .superPowers(SuperPowers.builder().strength("Body").durability("Week").canFly(false).build()).build(),

                    SuperHero.builder().id(2L).name("Tony").superName("Iron Man").profession("Business man").age(45)
                            .superPowers(SuperPowers.builder().strength("Suit").durability("Month").canFly(true).build()).build(),

                    SuperHero.builder().id(3L).name("Peter").superName("Spider Man").profession("Student").age(21)
                            .superPowers(SuperPowers.builder().strength("Spider sense").durability("Lifelong").canFly(true).build()).build()
            );
}