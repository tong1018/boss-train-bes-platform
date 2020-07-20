package com.boss.xtrain.basedata.pojo.entity;

import java.io.Serializable;
import com.boss.xtrain.common.core.pojo.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_category")
@Data
public class Category extends BaseEntity implements Serializable {

    /**
     * 类别名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 父类别ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

}