package com.jicg.jman.config.mybaits_plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @author jicg on 2020/4/16
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private HttpSession session;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateAt", LocalDateTime.class, LocalDateTime.now());
        if (metaObject.hasSetter("createrId")) {
            log.info("sessionId " + session.getId());
            // todo 新增 更新createrId
        }
        if (metaObject.hasSetter("updaterId")) {
            log.info("sessionId " + session.getId());
            // todo 修改 更新updaterId
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateAt", LocalDateTime.class, LocalDateTime.now());
        if (metaObject.hasSetter("updaterId")) {
            log.info("sessionId " + session.getId());
            // todo 修改 更新updaterId
        }
    }
}
