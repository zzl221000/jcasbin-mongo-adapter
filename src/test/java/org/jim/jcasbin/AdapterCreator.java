package org.jim.jcasbin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Created with IntelliJ IDEA.
 *
 * @author JimZhang
 * @since 2021/3/24
 * Description:
 */
public interface AdapterCreator {
    MongoAdapter create() throws Exception;

    void close();

    class MongoAdapterCreator implements AdapterCreator,AutoCloseable {
        MongoClient mongoClient = MongoClients.create("mongodb://172.16.5.168:20000,172.16.2.80:20000,172.16.6.228:20000/zhangji");
        @Override
        public MongoAdapter create() throws Exception {

            return new MongoAdapter(mongoClient, "zhangji");
        }

        @Override
        public void close() {
            mongoClient.close();
        }
    }
}
