package com.boss.xtrain.basedata.pojo.dto.subjecttype;

import com.boss.xtrain.common.core.pojo.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SubjectTypeDTO extends BaseEntity {
    private Long id;
    private String name;
    private String attribute;
    private String remark;
}
