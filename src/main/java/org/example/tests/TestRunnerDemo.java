package org.example.tests;

public class TestRunnerDemo {
    public static void main(String[] args) {
        TestRunner.run(TestRunnerDemo.class);
    }

    @Test
    void test1(){
        System.out.println("test1");
    }
    @Test
    void test2(){
        System.out.println("test2");
    }
    @AfterAll
    void test3(){
        System.out.println("test3");
    }
    @BeforeAll
    void beforeAll() {
        System.out.println("beforeAll");
    }
    @BeforeEach
    void beforeEach(){
        System.out.println("beforeEach");
    }
    @AfterAll
    void afterAll(){
        System.out.println("afterAll");
    }
    @AfterEach
    void afterEach(){
        System.out.println("afterEach");
    }
}
