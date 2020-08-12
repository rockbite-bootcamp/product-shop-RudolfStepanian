package com.rockbite.bootcamp.store.Pool.test;

import com.rockbite.bootcamp.store.Pool.Pool;
import org.junit.Test;

class PoolTest {

    @Test
    void newObjectCreationTest() {
        Pool<TestClass> testPoolObject = new Pool<TestClass>() {
            @Override
            protected TestClass newObject() {
                return new TestClass();
            }
        };
    }

    @Test
    void obtainTest() {
        Pool<TestClass> testPoolObject = new Pool<TestClass>() {
            @Override
            protected TestClass newObject() {
                return new TestClass();
            }
        };
        testPoolObject.obtain();
        System.out.println(testPoolObject.toString());
    }

    @Test
    void freeTest() {
        Pool<TestClass> testPoolObject = new Pool<TestClass>() {
            @Override
            protected TestClass newObject() {
                return new TestClass();
            }
        };
        TestClass testClassInstance = new TestClass();
        testPoolObject.free(testClassInstance);
        System.out.println(testPoolObject.toString());
    }


    @Test
    void obtainFreedObjectTest(){
        Pool<TestClass> testPoolObject = new Pool<TestClass>() {
            @Override
            protected TestClass newObject() {
                return new TestClass();
            }
        };
        TestClass testClassInstance = new TestClass();
        testPoolObject.free(testClassInstance);
        testPoolObject.obtain();
        System.out.println(testPoolObject.toString());
    }

    @Test
    void multipleObtainFreeTest(){
        Pool<TestClass> testPoolObject = new Pool<TestClass>() {
            @Override
            protected TestClass newObject() {
                return new TestClass();
            }
        };
        for (int i = 0; i < 5; i++){
            testPoolObject.free(new TestClass());
        }
        System.out.println(testPoolObject.toString());
        for (int i = 0; i < 5; i++){
            testPoolObject.obtain();
        }
        System.out.println(testPoolObject.toString());
    }

    @Test
    void syncMultipleObtainFreeTest(){
        Pool<TestClass> testPoolObject = new Pool<TestClass>() {
            @Override
            protected TestClass newObject() {
                return new TestClass();
            }
        };
        Thread freeThread = new Thread(() -> {
            for (int i = 0; i < 5; i++){
                testPoolObject.free(new TestClass());
                System.out.println(testPoolObject.toString());
            }
        });
        Thread obtainThread = new Thread(() -> {
            for (int i = 0; i < 5; i++){
                testPoolObject.obtain();
                System.out.println(testPoolObject.toString());
            }
        });
        freeThread.start();
        obtainThread.start();
    }



}