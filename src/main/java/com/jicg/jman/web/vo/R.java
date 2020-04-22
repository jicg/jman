package com.jicg.jman.web.vo;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author jicg on 2020/4/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

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


    public static <K> R ok(K data) {
        return new R<K>(0, "ok", data);
    }

    public static <T> R<T> fail(String msg) {
        return new R<T>().setCode(-1).setMsg(msg);
    }


}
