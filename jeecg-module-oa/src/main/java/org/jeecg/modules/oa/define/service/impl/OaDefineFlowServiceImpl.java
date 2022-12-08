package org.jeecg.modules.oa.define.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.modules.oa.define.constant.OaDefineConstant;
import org.jeecg.modules.oa.define.entity.OaDefineFlow;
import org.jeecg.modules.oa.define.entity.OaDefineFlowVersion;
import org.jeecg.modules.oa.define.mapper.OaDefineFlowMapper;
import org.jeecg.modules.oa.define.service.IOaDefineFlowService;
import org.jeecg.modules.oa.define.service.IOaDefineFlowVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 流程定义
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Service
public class OaDefineFlowServiceImpl extends ServiceImpl<OaDefineFlowMapper, OaDefineFlow> implements IOaDefineFlowService {
    @Autowired
    private IOaDefineFlowVersionService oaDefineFlowVersionService;

    @Override
    /**
     * TODO 重写save方法，新增时同时增加版本记录
     */
    public boolean save(OaDefineFlow oaDefineFlow) {
        oaDefineFlow.setId(UUIDGenerator.generate());
        OaDefineFlowVersion oaDefineFlowVersion = initOaDefineFlowVersion(oaDefineFlow);
        oaDefineFlow.setEditVersionId(oaDefineFlowVersion.getId());
        oaDefineFlow.setEditVersionNo("1");
        oaDefineFlow.setStatus(OaDefineConstant.OA_DEFINE_FLOW_STATUS_EDIT);
        return oaDefineFlowVersionService.save(oaDefineFlowVersion) && super.save(oaDefineFlow);
    }

    private OaDefineFlowVersion initOaDefineFlowVersion(OaDefineFlow oaDefineFlow) {
        OaDefineFlowVersion oaDefineFlowVersion = new OaDefineFlowVersion();
        oaDefineFlowVersion.setId(UUIDGenerator.generate());
        oaDefineFlowVersion.setFlowName(oaDefineFlow.getName());
        oaDefineFlowVersion.setFlowId(oaDefineFlow.getId());
        oaDefineFlowVersion.setVersionNo("1");
        oaDefineFlowVersion.setStatus(OaDefineConstant.OA_DEFINE_FLOW_STATUS_EDIT);
        return oaDefineFlowVersion;
    }
    public boolean removeById(Serializable id) {
        OaDefineFlow oaDefineFlow = getById(id);
        QueryWrapper<OaDefineFlowVersion> wrapper = new QueryWrapper();
        wrapper.eq("flow_id", id);
        return oaDefineFlowVersionService.remove(wrapper) && super.removeById(id);
    }

    @Override
    /**
     * 发布流程版本
     */
    public void audit(OaDefineFlow oaDefineFlow) {
        // 流程版本发布
        oaDefineFlowVersionService.audit(oaDefineFlow);
        // 修改流程定义状态
        oaDefineFlow.setPublishVersionId(oaDefineFlow.getEditVersionId());
        oaDefineFlow.setPublishVersionNo(oaDefineFlow.getEditVersionNo());
        oaDefineFlow.setEditVersionId("");
        oaDefineFlow.setEditVersionNo("");
        oaDefineFlow.setStatus(OaDefineConstant.OA_DEFINE_FLOW_STATUS_AUDITED);
        updateById(oaDefineFlow);
    }

    /**
     * TODO 配置新版本
     * @param id
     * @return
     */
    @Override
    public OaDefineFlow addConfig(String id) {
        OaDefineFlow oaDefineFlow = getById(id);
        OaDefineFlowVersion oaDefineFlowVersion = oaDefineFlowVersionService.getById(oaDefineFlow.getPublishVersionId());
        oaDefineFlowVersion.setId(UUIDGenerator.generate());
        oaDefineFlowVersion.setVersionNo(String.valueOf(Integer.valueOf(oaDefineFlowVersion.getVersionNo()) + 1));
        oaDefineFlowVersion.setStatus(OaDefineConstant.OA_DEFINE_FLOW_STATUS_EDIT);
        oaDefineFlowVersionService.save(oaDefineFlowVersion);
        oaDefineFlow.setEditVersionId(oaDefineFlowVersion.getId());
        oaDefineFlow.setEditVersionNo(oaDefineFlowVersion.getVersionNo());
        oaDefineFlow.setStatus(OaDefineConstant.OA_DEFINE_FLOW_STATUS_NEW_EDIT);
        updateById(oaDefineFlow);
        return oaDefineFlow;
    }
}
