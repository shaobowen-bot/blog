package com.bovan.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_note_tag")
public class NoteTag {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("type")
    private String type;

    @TableField("num")
    private Integer num;

    @TableField("version")
    private Integer version;


}
