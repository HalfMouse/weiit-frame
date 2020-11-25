package com.weiit.task.admin.util;

import com.xxl.job.admin.core.util.I18nUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author：半个鼠标
 * @date：2018年4月29日
 * @version 1.0
 * @company http://www.wei-it.com 微邦互联
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class I18nUtilTest {

    @Test
    public void test(){
        System.out.println(I18nUtil.getString("admin_name"));
        System.out.println(I18nUtil.getMultString("admin_name", "admin_name_full"));
        System.out.println(I18nUtil.getMultString());
    }

}
