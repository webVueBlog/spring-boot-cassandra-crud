package com.dada.cassandra.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data// 使用Lombok的@Data注解
@Builder// 使用Lombok的Builder注解
@Table("super_hero")// 定义表名
public class SuperHero implements Serializable {

    @PrimaryKey// 定义主键
    private Long id;// 定义主键

    private String name;// 定义其他字段

    @Column("super_name")// @Column注解用于指定字段在Cassandra中的列名
    private String superName;

    private String profession;// profession字段没有在@Column注解中指定列名，因此默认使用字段名作为列名

    private int age;

    @Column("super_powers")
    private SuperPowers superPowers;

}