package org.jeecg.modules.oa.define.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.oa.define.constant.OaDefineConstant;
import org.jeecg.modules.oa.define.entity.OaDefineFormVersion;
import org.jeecg.modules.oa.define.entity.OaDefineForm;
import org.jeecg.modules.oa.define.entity.OaDefineFormVersion;
import org.jeecg.modules.oa.define.mapper.OaDefineFormVersionMapper;
import org.jeecg.modules.oa.define.service.IOaDefineFormVersionService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 表单版本
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Service
public class OaDefineFormVersionServiceImpl extends ServiceImpl<OaDefineFormVersionMapper, OaDefineFormVersion> implements IOaDefineFormVersionService {

    @Override
    public void audit(OaDefineForm oaDefineForm) {
        OaDefineFormVersion oaDefineFormVersion = super.getById(oaDefineForm.getEditVersionId());
        oaDefineFormVersion.setStatus(OaDefineConstant.OA_DEFINE_FLOW_STATUS_AUDITED);
        super.updateById(oaDefineFormVersion);
    }
}
