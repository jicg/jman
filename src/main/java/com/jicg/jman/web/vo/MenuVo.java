package com.jicg.jman.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author jicg on 2020/4/14
 */
@ApiModel("菜单")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo {
    @ApiModelProperty("上级菜单id")
    private Long pid;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("链接")
    private String href;
    @ApiModelProperty("链接打开方式：_self,_blank")
    private String target;

    @ApiModelProperty("下级菜单列表")
    private List<MenuVo> child;
}
