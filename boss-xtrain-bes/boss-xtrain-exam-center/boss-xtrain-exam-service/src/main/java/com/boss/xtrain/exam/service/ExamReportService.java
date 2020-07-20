package com.boss.xtrain.exam.service;

import com.boss.xtrain.exam.pojo.dto.ReportDataItemDTO;
import com.boss.xtrain.exam.pojo.dto.query.ExamReportQuery;
import com.boss.xtrain.exam.pojo.dto.ReportDataListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试报表
 *
 * @author ChenTong
 * @version 10
 * @date 2020/7/13 23:10
 * @copyright
 * @modified
 * @see
 * @since
 **/
public interface ExamReportService {
    /**
     * 根据dto查询报表表格信息
     *
     * @param query
     * @return
     */
    List<ReportDataListDTO> queryListByCondition(ExamReportQuery query);

    /**
     * 根据publishId查询对应考试的报表详情
     *
     * @param publishId
     * @return
     */
    List<ReportDataItemDTO> queryDetail(Long publishId);
}
