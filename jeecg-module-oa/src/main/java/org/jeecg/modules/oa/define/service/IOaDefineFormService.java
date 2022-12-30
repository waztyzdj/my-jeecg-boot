package org.jeecg.modules.oa.define.service;

import org.jeecg.modules.oa.define.entity.OaDefineForm;
import org.jeecg.modules.oa.define.entity.OaDefineForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 表单定义
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
public interface IOaDefineFormService extends IService<OaDefineForm> {
    /**
     * TODO 发布流程
     * @param oaDefineForm
     */
    void audit(OaDefineForm oaDefineForm);

    /**
     * TODO 配置新版本
     * @param id
     * @return
     */
    OaDefineForm addConfig(String id);

}
