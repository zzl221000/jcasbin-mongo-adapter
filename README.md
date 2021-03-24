jcasbin-mongodb-adapter
===
MongoDB policy storage, implemented as an adapter for [jcasbin](https://github.com/casbin/jcasbin).

## Getting Started

### maven
add `jitpack` repository
```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
add `jcasbin-mongo-adapter` dependency
```xml

<dependency>
    <groupId>com.github.zzl221000</groupId>
    <artifactId>jcasbin-mongo-adapter</artifactId>
    <version>v1.0</version>
</dependency>
```

optional: If your project already has a mongodb driver, please ignore it.
```xml
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>${mongodb.version}</version>
</dependency>
```

```java
import org.casbin.jcasbin.persist.Adapter;
import org.jim.jcasbin.MongoAdapter;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.main.SyncedEnforcer;
import org.casbin.jcasbin.model.Model;
import com.ruiling.system.config.errors.CasbinModelConfigNotFoundException;

public class Demo {
    public static void main(String[] args) {
        Adapter adapter = new MongoAdapter(mongoClient, "casbin");
        Model model = new Model();
        try {
            String modelContext = properties.getModelContext();
            model.loadModelFromText(modelContext);
        } catch (CasbinModelConfigNotFoundException e) {
            // use default model configuration 
            // request definition
            model.addDef("r", "r", "sub, obj, act");
            // policy definition
            model.addDef("p", "p", "sub, obj, act");
            // role definition
            model.addDef("g", "g", "_, _");
            // policy effect
            model.addDef("e", "e", "some(where (p.eft == allow))");
            // matchers
            model.addDef("m", "m", "g(r.sub, p.sub) && r.obj == p.obj && r.act == p.act");
        }
        Enforcerenforcer = new SyncedEnforcer(model, adapter);
        // set auto save policy
        enforcer.enableAutoSave(true);
    }

}
```

## License

[Apache-2.0](./LICENSE)