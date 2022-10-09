package com.bovan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author bowen
 * @since 2022-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TNote implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)  //将该属性设置为主键，value属性用于指定主键的字段
    private Integer id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    private String title;

    private String avator;

    private String content;

    private LocalDateTime publishTime;

    private String type;


}
