package com.boss.xtrain.paper.controller;

import com.boss.xtrain.common.core.http.CommonPage;
import com.boss.xtrain.common.core.http.CommonResponse;
import com.boss.xtrain.common.core.http.CommonResponseUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

/**
 * test
 *
 * @author ChenTong
 * @date 2020/7/18 17:06
 * @copyright
 * @modified
 * @see
 * @since
 **/
public abstract class BaseController {
    /**
     *  分页前调用
     */
    protected Page<Object> doBeforePagination(int pageIndex, int pageSize){
        return PageMethod.startPage(pageIndex, pageSize);
    }
    /**
     * 构造分页响应
     * @author ChenTong
     * @param pageInfo
     * @return com.boss.xtrain.common.core.http.CommonResponse<com.boss.xtrain.common.core.http.CommonPage<T>>
     * @date 2020/7/7 17:02
     */
    protected <T> CommonResponse<CommonPage<T>> buildPageResponse(PageInfo<T> pageInfo){
        CommonPage<T> pageResult = new CommonPage<>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return CommonResponseUtil.ok(pageResult);
    }

}
