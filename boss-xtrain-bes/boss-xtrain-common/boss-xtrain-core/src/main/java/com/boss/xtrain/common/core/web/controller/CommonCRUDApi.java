package com.boss.xtrain.common.core.web.controller;


import com.boss.xtrain.common.core.http.CommonRequest;

import com.boss.xtrain.common.core.http.CommonRequest;

import com.boss.xtrain.common.core.http.CommonResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * @class CommonCRUDApi
 * @classdesc
 * @author Administrator
 * @date 2020-6-26  22:09
 * @version 1.0.0
 * @see
 * @since
 */
public interface CommonCRUDApi<D,Q,V>  {


    CommonResponse<Integer> create(@RequestBody @Valid CommonRequest<D> request);

    /**
     * 添加新的数据
     * @author ChenTong
     * @param request 请求报文对象，传递dto
     * @return com.boss.xtrain.common.core.http.CommonResponse<java.lang.Integer>
     * @date 2020/7/7 22:09
     */
    @PostMapping("/insert")
    CommonResponse<Integer> insert(@RequestBody @Valid CommonRequest<D> request);


    /**
     * 查询数据 返回数据vo列表
     * @author ChenTong
     * @param request 请求报文对象，传递query（本质也为dto）
     * @return com.boss.xtrain.common.core.http.CommonResponse<java.util.List<V>>
     * @date 2020/7/7 22:09
     */
    @PostMapping("/selectList")
    CommonResponse<List<V>> selectList(@RequestBody @Valid CommonRequest<Q> request);

    /**
     * 查找指定查询条件的数据
     * @author ChenTong
     * @param request 请求报文对象，传递query（本质也为dto）
     * @return com.boss.xtrain.common.core.http.CommonRequest<V>
     * @date 2020/7/7 22:09
     */
    @PostMapping("/select")
    CommonResponse<V> select(@RequestBody @Valid CommonRequest<Q> request);

    /**
     * 指定删除某个数据数据
     * @author ChenTong
     * @param request 请求报文对象，传递query（本质也为dto）
     * @return com.boss.xtrain.common.core.http.CommonResponse<java.lang.Integer>
     * @date 2020/7/7 22:09
     */
    @DeleteMapping("/delete")
    CommonResponse<Integer> delete(@RequestBody @Valid CommonRequest<D> request);

    CommonResponse<Integer> create(@RequestBody @Valid D dtoParam);

    CommonResponse<List<V>> query(@Valid Q queryParam);

    /**
     * 批量删除数据
     * @author ChenTong
     * @param request 请求报文对象，传递query（本质也为dto）
     * @return com.boss.xtrain.common.core.http.CommonResponse<java.lang.Integer>
     * @date 2020/7/7 22:09
     */
    @DeleteMapping("/deleteBatch")
    CommonResponse<Integer> deleteBatch(@RequestBody @Valid CommonRequest<List<D>> request);

    CommonResponse<Integer> delete(@NotNull Long id);

    /**
     * 更新数据
     * @author ChenTong
     * @param request
     * @return com.boss.xtrain.common.core.http.CommonResponse<java.lang.Integer>
     * @date 2020/7/7 22:10
     */
    @PutMapping("update")
    CommonResponse<Integer> update(@RequestBody @Valid CommonRequest<D> request);

}
