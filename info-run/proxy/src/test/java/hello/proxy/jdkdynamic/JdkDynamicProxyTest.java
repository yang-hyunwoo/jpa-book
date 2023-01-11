package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        Alnterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        Alnterface proxy = (Alnterface) Proxy.newProxyInstance(Alnterface.class.getClassLoader(), new Class[]{Alnterface.class}, handler);
        proxy.call();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());
    }

    @Test
    void dynamicB() {
        Blnterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        Blnterface proxy = (Blnterface) Proxy.newProxyInstance(Blnterface.class.getClassLoader(), new Class[]{Blnterface.class}, handler);
        proxy.call();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());
    }
}
