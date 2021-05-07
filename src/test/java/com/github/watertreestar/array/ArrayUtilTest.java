package com.github.watertreestar.array;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;

public class ArrayUtilTest {

    @Test
    public void isArrayTest(){
        Integer[] nums = new Integer[5];
        boolean isArray = ArrayUtil.isArray(nums);
        Assert.assertTrue(isArray);

        Float f = 2343.4f;
        isArray = ArrayUtil.isArray(f);
        Assert.assertFalse(isArray);
    }

    @Test
    public void toArrayTest(){
        Integer[] nums = new Integer[]{12,32,43,54,43};
        Object[] result = ArrayUtil.toArray(nums);
        Assert.assertEquals(5,result.length);

        Assert.assertTrue(ArrayUtil.isArray(result));
    }

    @Test
    public void toStringTest(){
        String[] names = new String[]{"watertreestar","yaping","charlotte"};
        String result  = ArrayUtil.toString(names);
        Assert.assertEquals("[watertreestar,yaping,charlotte]",result);

        result = ArrayUtil.toString(names,"|");
        Assert.assertEquals("[watertreestar|yaping|charlotte]",result);

        String name = "watertreestar";
        try{
            ArrayUtil.toString(name);
            Assert.fail();
        }catch (Exception e){
            Assert.assertEquals(e.getClass(),IllegalArgumentException.class);
            Assert.assertThat(e.getMessage(), StringContains.containsString("parameter object not array"));
        }

        try{
            ArrayUtil.toString(name,"-");
            Assert.fail();
        }catch (Exception e){
            Assert.assertEquals(e.getClass(),IllegalArgumentException.class);
            Assert.assertThat(e.getMessage(), StringContains.containsString("parameter object not array"));
        }
    }
}
