package com.toolcat.application;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toolcat.application.entity.User;
import com.toolcat.application.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * PageInfo.getList	        结果集
 * PageInfo.getPageSize	    当前页面显示的数据条目
 * PageInfo.getTotal	    数据的总条目数
 * PageInfo.getPages	    总页数
 * PageInfo.getPageNum	    当前页码
 * PageInfo.getPrePage	    上一页页码
 * PageInfo.getNextPage	    下一页页码
 * PageInfo.isFirstPage	    是否为第一页
 * PageInfo.isLastPage	    是否为最后一页
 * PageInfo.hasPreviousPage	是否有上一页
 * PageHelper.hasNextPage	是否有下一页
 */
@SpringBootTest
public class PageHelperTest {

    @Autowired
    private UserService userService;

    /**
     * 分页
     */
    @Test
    void test0() {
        PageHelper.startPage(2, 2);
        List<User> users = userService.allUser().get();// PageInfo.getList()
        System.out.println(users);
        System.out.println(PageInfo.of(users).getEndRow());
    }
}
