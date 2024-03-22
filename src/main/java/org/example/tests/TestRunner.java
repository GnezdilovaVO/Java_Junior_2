package org.example.tests;


import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class TestRunner {

    public static void run(Class<?> testClass){
        final Object testObj = initTestObj(testClass);
        List<Method> bam = new ArrayList<>();
        List<Method> bem = new ArrayList<>();
        List<Method> aam = new ArrayList<>();
        List<Method> aem = new ArrayList<>();
        List<Method> test = new ArrayList<>();


        for (Method testMethod: testClass.getDeclaredMethods()){
            if (testMethod.accessFlags().contains(AccessFlag.PRIVATE)) {
                continue;
            }
            if (testMethod.getAnnotation(Test.class) != null){
                test.add(testMethod);
//                try {
//                    testMethod.invoke(testObj);
//
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                }
            } else if (testMethod.getAnnotation(BeforeAll.class) != null) {
                bam.add(testMethod);
            } else if (testMethod.getAnnotation(BeforeEach.class) != null) {
                bem.add(testMethod);
            } else if (testMethod.getAnnotation(AfterAll.class) != null) {
                aam.add(testMethod);
            } else if (testMethod.getAnnotation(AfterEach.class) != null) {
                aem.add(testMethod);
            }
        }
        for (Method testMethod: bam) {
            try {
                testMethod.invoke(testObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        for (Method testMethod : test) {
            for (Method method : bem) {
                try {
                    method.invoke(testObj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                testMethod.invoke(testObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            for (Method method: aem) {
                try {
                    method.invoke(testObj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for (Method testMethod: aam) {
            try {
                testMethod.invoke(testObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static Object initTestObj(Class<?> testClass){
        try {
            Constructor<?> noArgsConstructor = testClass.getConstructor();
            return noArgsConstructor.newInstance();
        } catch(NoSuchMethodException e) {
            throw new RuntimeException("Нет конструктора по-умолчанию");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект тест классов");
        }
    }
}
