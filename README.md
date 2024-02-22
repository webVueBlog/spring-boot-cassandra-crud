# spring-boot-cassandra-crud

- ![1](https://github.com/webVueBlog/spring-boot-cassandra-crud/assets/59645426/2ec8423e-1bbf-4a18-867a-1673cab1e768)

带有Cassandra DB的Spring boot CRUD（创建，读取，更新，删除）演示应用程序。
在此应用程序中，我们使用spring数据和cassandra DB实现了CRUD（创建，读取，更新，删除）操作。

## Prerequisites 

- Java
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/guides/index.html)
- [Cassandra](https://cassandra.apache.org/)

## Tools

- Eclipse 或 IntelliJ IDEA（或任何首选的 IDE）与嵌入式 Gradle
- Maven（版本 >= 3.6.0）
- Postman（或任何 RESTful API 测试工具）
- CQLSH （Cassandra Query Language shell） - 用于监控存储的数据

###  Build and Run application

_GOTO >_ **~/absolute-path-to-directory/spring-boot-cassandra-crud**  
and try below command in terminal
> **```mvn spring-boot:run```** it will run application as spring boot application

or
> **```mvn clean install```** it will build application and create **jar** file under target directory 

Run jar file from below path with given command
> **```java -jar ~/path-to-spring-boot-cassandra-crud/target/spring-boot-cassandra-crud-0.0.1-SNAPSHOT.jar```**

Or
> run main method from `SpringBootCassandraCrudApplication.java` as spring boot application.  

### For API document using OpenAPI UI 

> **http://localhost:8080/swagger-ui-custom.html**

### Install JDK8
第 1 步：从 JDK 站点下载 JDK8。 [JDK site](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).  

第 2 步：安装下载的可执行文件。

第 3 步：添加JDK8路径作为环境变量。

### 设置 cqlsh （cassandra query language shell） - 用于监控存储的数据
第 1 步：Python2.7 是 cqlsh 处理用户请求的强制性要求。从 Python 站点下载 Python2.7。

第 2 步：安装下载的可执行文件。

第 3 步：添加 Python2.7 路径作为环境变量。

### Setup Cassandra 设置 Cassandra

第 1 步：从 Cassandra 站点下载最新版本的 apache-cassandra-x.xx.x。

第2步： 使用压缩工具将压缩的zip文件解压缩到任何位置。例如：\apache-cassandra-x.xx.x

第 3 步：添加 c：\apache-cassandra-x.xx.x\bin 路径作为环境变量。

#### 启动 Cassandra 和 cqlsh

##### Start Cassandra

确保在环境变量中为 cassandra 设置了 bin 路径。

> `cassandra`

如果控制台上没有错误，则表示 cassandra 已启动并正在运行。

##### Start cqlsh 

确保在环境变量中为 python 设置了路径。

> `cqlsh`

如果控制台上没有错误，则表示 cqlsh 已连接。

### Code Snippets 代码片段

1. #### Maven Dependencies Maven 依赖项

需要在下面添加依赖项以在pom.xml中启用Cassandra。

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-cassandra</artifactId>
</dependency>
    
<!-- For Boilerplate code (Getters/Setters/Constructors) -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

对于使用 swagger 和 OpenApi UI 的 API 文档，请在下面添加依赖项。

```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.4.4</version>
</dependency>
```

2. #### Properties file

在与 cassandra 相关的 application.yml 文件中放置了属性，我们正在 CassandraConfig.java 类中读取这些属性并为 Cassandra 配置 cassandra 连接。

API文档相关的swagger UI路径也放在这里，这将使Swagger API Doc在同一路径上。

src/main/resources/application.yml

 ```
 spring:
   data:
     cassandra:
       contact-points: localhost
       port: 9042
       keyspace-name: simple_crud
       #username: cassandra
       #password: cassandra
       #schema-act: create_if_not_exists
 
 springdoc:
   version: 1.0.0
   swagger-ui:
     path: /swagger-ui-custom.html
 ```

3. #### Model class

下面是我们将存储在 cassandra 中并执行 CRUD 操作的模型类。

model.com.dada.cassandra.SuperHero.java

model.com.dada.cassandra.SuperPowers.java

```
@Data
@Builder
@Table("super_hero")
public class SuperHero implements Serializable {    
    @PrimaryKey
    private Long id;    
    private String name;    
    @Column("super_name")
    private String superName;    
    private String profession;    
    private int age;    
    @Column("super_powers")
    private SuperPowers superPowers;    
}


@Data
@Builder
@UserDefinedType("super_powers")
public class SuperPowers implements Serializable {   
   private String strength;   
   private String durability;   
   private boolean canFly;
}
```


4. #### Cassandra 配置

这是此应用程序中最重要的类，其中放置了所有与 cassandra 相关的配置，并且使用此类，我们将连接到 cassandra 并在启动应用程序时创建 KEYSPACE 和 TABLES。

```
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

   @Value("${spring.data.cassandra.keyspace-name: simple_crud}")
   private String KEYSPACE;

   @Value("${spring.data.cassandra.contact-points: localhost}")
   private String CONTACT_POINT;

   @Value("${spring.data.cassandra.port: 9042}")
   private int PORT;


   @Override
   public String getContactPoints() {
       return CONTACT_POINT;
   }

   @Override
   protected int getPort() {
       return PORT;
   }

   @Override
   public SchemaAction getSchemaAction() {
       return SchemaAction.CREATE_IF_NOT_EXISTS;
   }

   @Override
   protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
       return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(KEYSPACE)
               .ifNotExists()
               .with(KeyspaceOption.DURABLE_WRITES, true)
               .withSimpleReplication(3L));
   }

   @Override
   protected String getLocalDataCenter() {
       return "datacenter1";
   }

   //@Override
   //protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
   //    return Collections.singletonList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
   //}

   @Override
   protected String getKeyspaceName() {
       return KEYSPACE;
   }
  
   @Override
   public String[] getEntityBasePackages() {
       return new String[] {"com.dada.cassandra.model"};
   }   
}
```

   
   
5. #### CRUD operation for Super Heroes

在com.xxx.cassandra.controller.SuperHeroController.java类中，我们公开了5个用于基本CRUD操作的端点。

- GET All Super Heroes
- GET by ID
- POST to store Super Hero in DB
- PUT to update Super Hero
- DELETE by ID

```
@RestController
@RequestMapping("/super-heroes")
public class SuperHeroController {
    
    @GetMapping("/save")
    public ResponseEntity<List<SuperHero>> save();

    @GetMapping
    public ResponseEntity<List<SuperHero>> findAll();

    @GetMapping("/{id}")
    public ResponseEntity<SuperHero> findById(@PathVariable String id);

    @PostMapping
    public ResponseEntity<SuperHero> save(@RequestBody SuperHero superHero);

    @PutMapping
    public ResponseEntity<SuperHero> update(@RequestBody SuperHero superHero);

    @DeleteMapping("/{id}")
    public ResponseEntity<SuperHero> delete(@PathVariable String id);
}
```

```
@Repository
public interface SuperHeroRepository extends CassandraRepository<SuperHero, Long> {
}
```

6. #### Query operation for SuperHero

```
@Autowired
private CassandraOperations cassandraTemplate;
``` 
    
<br/>

### API Endpoints

- #### Super Hero CRUD Operations

> **GET Mapping** http://localhost:8080/super-heroes  - Get all Super Heroes

> **GET Mapping** http://localhost:8080/super-heroes/1  - Get Super Hero by ID
   
> **POST Mapping** http://localhost:8080/super-heroes  - Add new Super Hero in DB  

```
{
   "id": 1,
   "name": "Tony",
   "superName": "Iron Man",
   "profession": "Business",
   "age": 50,            
   "superPowers": {
       "strength": "Suit",
       "durability": "Month",
       "canFly": true
   }
}
```

> **PUT Mapping** http://localhost:8080/super-heroes  - Update existing Super Hero for given ID 

 Request Body    
 ```
 {
     "id": 1,
     "name": "Tony",
     "superName": "Iron Man",
     "profession": "Business",
     "age": 50,         
     "superPowers": {
         "strength": "Only if he is in a suit",
         "durability": "Month",
         "canFly": true
     }
 }
 ```

> **DELETE Mapping** http://localhost:8080/super-heroes/1  - Delete Super Hero by ID


### 输出

cqlsh: select * from super_hero;
