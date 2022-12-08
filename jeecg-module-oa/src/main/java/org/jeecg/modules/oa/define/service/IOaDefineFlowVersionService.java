package org.jeecg.modules.oa.define.service;

import org.jeecg.modules.oa.define.entity.OaDefineFlow;
import org.jeecg.modules.oa.define.entity.OaDefineFlowVersion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 流程版本
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
public interface IOaDefineFlowVersionService extends IService<OaDefineFlowVersion> {

    /**
     * TODO 流程版本发布
     * @param oaDefineFlow
     */
    void audit(OaDefineFlow oaDefineFlow);
}
