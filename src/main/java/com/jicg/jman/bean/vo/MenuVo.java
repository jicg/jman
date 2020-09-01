package com.jicg.jman.bean.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/4/14
 */
@ApiModel(description = "菜单")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo  implements Serializable {
    @ApiModelProperty("上级菜单id")
    private Long pid = 0L;

    @ApiModelProperty("菜单id")
    private long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("权限标识")
    private String perm;

    @ApiModelProperty("类型(0:顶级目录,1:菜单,2:其他,)")
    private int actionType;

    @ApiModelProperty("链接")
    private String href;
    @ApiModelProperty("链接打开方式：_self,_blank")
    private String target;

    @ApiModelProperty("排序")
    private int sort;

    @ApiModelProperty("状态(0:禁用,1:启用)")
    private int status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("下级菜单列表")
    private List<MenuVo> child = new ArrayList<>();

    private boolean open = true;
}
