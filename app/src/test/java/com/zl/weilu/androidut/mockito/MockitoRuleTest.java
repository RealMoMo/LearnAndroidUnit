package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Banana;
import com.zl.weilu.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @Description: MockitoRule方式Mock
 * @Author: weilu
 * @Time: 2017/11/4 14:43.
 */

public class MockitoRuleTest {

    @Mock 
    Person mPerson;

    @Mock
    Banana mBanana;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

    @Test
    public void testBanana(){
       assertNotNull(mBanana);
    }
   
}
