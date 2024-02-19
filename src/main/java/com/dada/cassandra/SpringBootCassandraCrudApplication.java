package com.dada.cassandra;

import com.dada.cassandra.model.SuperHero;
import com.dada.cassandra.repository.SuperHeroRepository;
import com.dada.cassandra.utils.HelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.List;

@SpringBootApplication
@EnableCassandraRepositories
public class SpringBootCassandraCrudApplication {

	private final Logger logger = LoggerFactory.getLogger(getClass());// 作用是输出一些信息

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCassandraCrudApplication.class, args);// 启动springboot项目
	}

	@Autowired
	private SuperHeroRepository superHeroRepository;// 注入superHeroRepository

	@Bean
	CommandLineRunner runner() {// 创建一个CommandLineRunner的bean
		return args -> {// 创建一个CommandLineRunner的bean
			List<SuperHero> superHeroes = superHeroRepository.findAll();// 查询数据库中所有的数据
			if (superHeroes.isEmpty()) {// 如果数据库中没有数据
				logger.info("******* Inserting Super heroes to DB *******");// 输出信息
				superHeroRepository.saveAll(HelperUtil.getSuperHeroesData());// 保存数据到数据库
			} else {
				logger.info("******* Super heroes stored in DB Size :: {}", superHeroes.size());// 输出信息
				logger.info("******* Super heroes stored in DB :: {}", superHeroes);// 输出信息
			}
		};
	}

}