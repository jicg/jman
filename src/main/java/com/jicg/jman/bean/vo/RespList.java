package com.jicg.jman.bean.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/4/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RespList<T> extends Resp<List<T>> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码
     */
    private long code = 0;
    /**
     * 描述
     */
    private String msg = "操作成功";

    private long count;
    /**
     * 结果集
     */
    private List<T> data = new ArrayList<>();

    public static <K> RespList<K> ok(IPage<K> page) {
        return new RespList<K>(0, "操作成功", page.getTotal(), page.getRecords());
    }

//    public static <K> RespList<K> ok(String msg) {
//        return new RespList<K>(0, "操作成功", null);
//    }
//
//    public static <K> RespList<K> ok(String msg, K data) {
//        return new RespList<K>(0, "操作成功", data);
//    }
//
//    public static <T> RespList<T> fail(String msg) {
//        return new RespList<T>().setCode(-1).setMsg(msg);
//    }


}
