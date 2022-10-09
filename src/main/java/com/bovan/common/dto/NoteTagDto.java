package com.bovan.common.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class NoteTagDto implements Serializable {
    @NotBlank(message = "标签不能为空")
    private String tag;
    @NotBlank(message = "类型不能为空")
    private String type;
    private int num;
}
