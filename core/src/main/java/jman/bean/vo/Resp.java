package com.jicg.jman.bean.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jicg on 2020/4/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Resp<T> implements Serializable {

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
    /**
     * 结果集
     */
    private T data;

    public static <K> Resp<K> ok(String msg) {
        return new Resp<K>(0, "操作成功", null);
    }



    public static <K> Resp<K> ok(String msg, K data) {
        return new Resp<K>(0, "操作成功", data);
    }

    public static <T> Resp<T> fail(String msg) {
        return new Resp<T>().setCode(-1).setMsg(msg);
    }


}
