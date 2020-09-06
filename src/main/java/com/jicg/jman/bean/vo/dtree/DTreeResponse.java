package com.jicg.jman.bean.vo.dtree;

import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysRole;

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


    public static <T> DTreeResponse toTreeChecked(List<T> sysMenuList, List<T> checkMenus, LoadItemAction<T> action) {
        List<Long> checkedIds = checkMenus.stream().map(r -> action.getTreeItem(r).id).collect(Collectors.toList());
        Map<String, DTree> dTreeMaps = new LinkedHashMap<>();
        sysMenuList.forEach(sysMenu -> {
            TreeItem<T> treeItem = action.getTreeItem(sysMenu);
            DTree dTree = new DTree("" + treeItem.id, "" + treeItem.pid,
                    treeItem.title, false);
            CheckArr checkArr = new CheckArr("0", "0");
            if (checkedIds.contains(treeItem.id)) {
                checkArr.setChecked("1");
            }
            dTree.setCheckArr(Collections.singletonList(checkArr));
            dTreeMaps.put("" + treeItem.id, dTree);
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

    public static DTreeResponse toTreeRoleChecked(List<SysRole> roleList, List<SysRole> checkRoleList) {
        List<Long> checkedIds = checkRoleList.stream().map(SysRole::getId).collect(Collectors.toList());
        DTreeResponse resp = new DTreeResponse();
        resp.setData(roleList.stream().map(role -> {
            DTree dTree = new DTree("" + role.getId(), "0", role.getName(), true);
            CheckArr checkArr = new CheckArr("0", "0");
            if (checkedIds.contains(role.getId())) {
                checkArr.setChecked("1");
            }
            dTree.setCheckArr(Collections.singletonList(checkArr));
            return dTree;
        }).collect(Collectors.toList()));
        return resp;
    }

    public static class TreeItem<T> {
        private long id = 0;
        long pid = 0;
        String title = "";

        public TreeItem(long id, long pid, String title) {
            this.id = id;
            this.pid = pid;
            this.title = title;
        }
    }

    public interface LoadItemAction<T> {
        TreeItem<T> getTreeItem(T t);
    }

}
