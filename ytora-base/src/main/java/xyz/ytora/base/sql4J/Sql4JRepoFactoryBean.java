package xyz.ytora.base.sql4J;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.orm.scanner.RepoScanner;

/**
 * 工厂类，Spring 会调用 getObject() 来获取真正的 Repo 代理对象
 */
@Data
public class Sql4JRepoFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private Class<T> repoInterface;
    private String pkgToScan;
    private T proxyInstance;

    @Autowired
    private SQLHelper sqlHelper;

    public Sql4JRepoFactoryBean() {}

    // 提供构造函数给 Registrar 使用，确保类型第一时间确定
    public Sql4JRepoFactoryBean(Class<T> repoInterface) {
        this.repoInterface = repoInterface;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        RepoScanner scanner = new RepoScanner(pkgToScan);
        Class<?> proxyClass = scanner.getOrCreateProxyClass(repoInterface);
        this.proxyInstance = (T) proxyClass.getConstructor().newInstance();
    }

    @Override
    public T getObject() {
        return proxyInstance;
    }

    @Override
    public Class<?> getObjectType() {
        return repoInterface;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
