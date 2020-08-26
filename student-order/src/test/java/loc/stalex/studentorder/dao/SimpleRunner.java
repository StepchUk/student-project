package loc.stalex.studentorder.dao;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRunner {

    public static void main(String[] args) {
        SimpleRunner simpleRunner = new SimpleRunner();

        simpleRunner.renTest();
    }

    private void renTest() {
        try {
            Class<?> aClass = Class.forName("loc.stalex.studentorder.dao.DictionaryDaoImplTest");

            Constructor<?> constructor = aClass.getConstructor();
            Object entity = constructor.newInstance();

            Method[] methods = aClass.getMethods();

            for (Method method : methods) {
                Test annotation = method.getAnnotation(Test.class);

                if (annotation != null) {
                    method.invoke(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
