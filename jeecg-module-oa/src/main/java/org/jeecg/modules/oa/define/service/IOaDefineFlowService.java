package org.jeecg.modules.oa.define.service;

import org.apache.poi.ss.formula.functions.T;
import org.jeecg.modules.oa.define.entity.OaDefineFlow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 流程定义
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
public interface IOaDefineFlowService extends IService<OaDefineFlow> {
    /**
     * TODO 发布流程
     * @param oaDefineFlow
     */
    void audit(OaDefineFlow oaDefineFlow);

    /**
     * TODO 配置新版本
     * @param id
     * @return
     */
    OaDefineFlow addConfig(String id);
}
