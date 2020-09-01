package com.jicg.jman.bean.vo.dtree;

import com.jicg.jman.bean.vo.TreeBeanVo;
import com.jicg.jman.orm.entity.SysMenu;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * response返回类
 */
public class DTreeResponse implements Serializable {
    public static final String DTREE_RESP = "DTREE_RESP";
//    /**
//     * 状态码
//     */
//    private int code = 0;
//
//    /**
//     * 信息标识
//     */
//    private String msg = "success";

    /**
     * 状态类
     */
    private Status status = new Status();

    /**
     * 数据
     */
    private Object data;


//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DtreeResponse [ status="//code=" + code + ", msg=" + msg + ",
                + status + ", data=" + data + "]";
    }


    public static DTreeResponse toTree(List<SysMenu> sysMenuList) {
//        Map<Long, SysMenu> maps = new LinkedHashMap<>();
        Map<String, DTree> dTreeMaps = new LinkedHashMap<>();
        sysMenuList.forEach(sysMenu -> {
            if (sysMenu.getStatus() == 1) {
                DTree dTree = new DTree("" + sysMenu.getId(), "" + sysMenu.getPid(), sysMenu.getTitle(), false);
                dTreeMaps.put("" + sysMenu.getId(), dTree);
            }
        });
        dTreeMaps.forEach((k, v) -> {
            if (v.getParentId().compareTo("0") > 0) {
                if (dTreeMaps.containsKey(v.getParentId())) {
                    dTreeMaps.get(v.getParentId()).getChildren().add(v);
                }
            }
        });
        dTreeMaps.forEach((k, v) -> {
            if (v.getChildren() != null && v.getChildren().size() <= 0) {
                v.setChildren(null);
                v.setIsLast(true);
            }
        });
        DTreeResponse resp = new DTreeResponse();
        resp.setData(dTreeMaps.values().stream()
                .filter(x -> x.getParentId().compareTo("0") <= 0)
                .collect(Collectors.toList()));
        return resp;
    }


    public static DTreeResponse toTreeChecked(List<SysMenu> sysMenuList, List<SysMenu> checkMenus) {
//        Map<Long, SysMenu> maps = new LinkedHashMap<>();
        List<Long> checkedIds = checkMenus.stream().map(SysMenu::getId).collect(Collectors.toList());
        Map<String, DTree> dTreeMaps = new LinkedHashMap<>();
        sysMenuList.forEach(sysMenu -> {
            if (sysMenu.getStatus() == 1) {

                DTree dTree = new DTree("" + sysMenu.getId(), "" + sysMenu.getPid(), sysMenu.getTitle(), false);
                CheckArr checkArr = new CheckArr("0", "0");
                if (checkedIds.contains(sysMenu.getId())) {
                    checkArr.setChecked("1");
                }
                dTree.setCheckArr(Collections.singletonList(checkArr));
                dTreeMaps.put("" + sysMenu.getId(), dTree);
            }
        });
        dTreeMaps.forEach((k, v) -> {
            if (v.getParentId().compareTo("0") > 0) {
                if (dTreeMaps.containsKey(v.getParentId())) {
                    dTreeMaps.get(v.getParentId()).getChildren().add(v);
                }
            }
        });
        dTreeMaps.forEach((k, v) -> {
            if (v.getChildren() != null && v.getChildren().size() <= 0) {
                v.setChildren(null);
                v.setIsLast(true);
            }
        });
        DTreeResponse resp = new DTreeResponse();
        resp.setData(dTreeMaps.values().stream()
                .filter(x -> x.getParentId().compareTo("0") <= 0)
                .collect(Collectors.toList()));
        return resp;
    }

}
