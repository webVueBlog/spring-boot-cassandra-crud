package com.dada.cassandra.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serializable;

@Data
@Builder
@UserDefinedType("super_powers")// 定义用户定义类型（UDT）
public class SuperPowers implements Serializable {// 实现序列化接口
    private String strength;// 力量
    private String durability;// 持久力
    private boolean canFly;// 是否能够飞行
}
