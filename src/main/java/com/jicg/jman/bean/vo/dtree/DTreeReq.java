package com.jicg.jman.bean.vo.dtree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jicg on 2020/9/1
 */
@Data
@NoArgsConstructor
public class DTreeReq implements Serializable {
    @JsonProperty("nodeId")
    private String nodeid;
    @JsonProperty("parentId")
    private String parentid;
    private String context;
    private boolean leaf;
    private String level;
    private boolean spread;
    @JsonProperty("dataType")
    private String datatype;
    private String checked;
    private String initchecked;
}
