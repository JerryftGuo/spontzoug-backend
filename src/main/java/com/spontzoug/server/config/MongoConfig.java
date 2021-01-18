package com.spontzoug.server.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Log4j2
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.spontzoug.server.repository")
public class MongoConfig extends AbstractReactiveMongoConfiguration {
   @Value("${mongodb.url}")
   private String url;

    @Value("${mongodb.user}")
    private String user;
    @Value("${mongodb.password}")
    private String password;

    @Value("${mongodb.dbname}")
    private String dbName;

    @Autowired
    private MongoConverter mongoConverter;

    @Override
    public MongoClient reactiveMongoClient(){
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName(){
        return dbName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(){
        return new ReactiveMongoTemplate(reactiveMongoClient(),getDatabaseName());
    }
/*
    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup(){
        log.info("Mongo InitIndicesAfterStartup init");
        var init = System.currentTimeMillis();

        var mappingContext = this.mongoConverter.getMappingContext();
        if( mappingContext instanceof MongoMappingContext){
            MongoMappingContext mongoMappingContext = (MongoMappingContext) mappingContext;
            for(BasicMongoPersistentEntity<?> persistentEntity : mongoMappingContext.getPersistentEntities()){
                var clazz = persistentEntity.getType();
                if (clazz.isAnnotationPresent(Document.class)){
                    var resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
                    var indexOps = reactiveMongoTemplate().indexOps(clazz);
                    resolver.resolveIndexFor(clazz).forEach(indexOps::ensureIndex);
                }
            }

        }
        log.info("Mongo InitIndicesAfterStartup take: {}", (System.currentTimeMillis() - init));

    }

 */
}
