package org.jeecg.modules.oa.define.service;

import org.jeecg.modules.oa.define.entity.OaDefineForm;
import org.jeecg.modules.oa.define.entity.OaDefineFormVersion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 表单版本
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
public interface IOaDefineFormVersionService extends IService<OaDefineFormVersion> {

    /**
     * TODO 表单版本发布
     * @param oaDefineForm
     */
    void audit(OaDefineForm oaDefineForm);

}
