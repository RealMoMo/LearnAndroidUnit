package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Home;
import com.zl.weilu.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @Description: Spy示例
 * @Author: weilu
 * @Time: 2017/11/4 19:12.
 */

public class MockitoSpyTest {

    @Spy
    Person mPerson;
    
    @InjectMocks
    Home mHome;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

    @Test
    public void testPersonSpy(){
        //输出11,若用Mock Person 输出为默认值0
        System.out.println(mPerson.getAge());
    }

    @Test
    public void testHomeInjectMocks(){
        when(mPerson.getName()).thenReturn("weilu");
        System.out.println(mHome.getMaster());
    }
}
