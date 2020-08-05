package com.jicg.jman.config.mybaits_plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jicg.jman.utils.Utils;
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
            log.info("insertFill createrId sessionId " + session.getId());
            metaObject.setValue("createrId", Utils.getUser().getId());
        }
        if (metaObject.hasSetter("updaterId")) {
            log.info("insertFill updaterId sessionId " + session.getId());
            metaObject.setValue("updaterId", Utils.getUser().getId());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateAt", LocalDateTime.class, LocalDateTime.now());
        if (metaObject.hasSetter("updaterId")) {
            log.info("updateFill updaterId sessionId " + session.getId());
            metaObject.setValue("updaterId", Utils.getUser().getId());
        }
    }
}
