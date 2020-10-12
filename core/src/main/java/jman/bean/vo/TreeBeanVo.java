package com.jicg.jman.bean.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/7/9
 */
@Data
public class TreeBeanVo {
    private long id;
    private String name;
    private boolean open = false;
    private long pid;
    private List<TreeBeanVo> children = new ArrayList<>();
}
