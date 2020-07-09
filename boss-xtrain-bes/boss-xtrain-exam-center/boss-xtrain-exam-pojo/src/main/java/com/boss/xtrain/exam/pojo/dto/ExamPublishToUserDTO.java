package com.boss.xtrain.exam.pojo.dto;

/**
 * 阅卷关系dto
 *
 * @author ChenTong
 * @version 1.0
 * @date 2020/7/9 11:28
 * @copyright
 * @modified
 * @see
 * @since
 **/
public class ExamPublishToUserDTO {
    /**
     * 阅卷官的姓名
     */
    private String name;

    /**
     * 阅卷官id
     */
    private Long markPeople;

    public ExamPublishToUserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMarkPeople() {
        return markPeople;
    }

    public void setMarkPeople(Long markPeople) {
        this.markPeople = markPeople;
    }
}
