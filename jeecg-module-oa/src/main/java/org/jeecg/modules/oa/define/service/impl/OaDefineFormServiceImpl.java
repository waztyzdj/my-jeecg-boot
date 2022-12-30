package org.jeecg.modules.oa.define.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.modules.oa.define.constant.OaDefineConstant;
import org.jeecg.modules.oa.define.entity.OaDefineForm;
import org.jeecg.modules.oa.define.entity.OaDefineFormVersion;
import org.jeecg.modules.oa.define.mapper.OaDefineFormMapper;
import org.jeecg.modules.oa.define.service.IOaDefineFormVersionService;
import org.jeecg.modules.oa.define.service.IOaDefineFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;

/**
 * @Description: 表单定义
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Service
public class OaDefineFormServiceImpl extends ServiceImpl<OaDefineFormMapper, OaDefineForm> implements IOaDefineFormService {
    @Autowired
    private IOaDefineFormVersionService oaDefineFormVersionService;

    @Override
    /**
     * TODO 重写save方法，新增时同时增加版本记录
     */
    public boolean save(OaDefineForm oaDefineForm) {
        oaDefineForm.setId(UUIDGenerator.generate());
        OaDefineFormVersion oaDefineFormVersion = initOaDefineFormVersion(oaDefineForm);
        oaDefineForm.setEditVersionId(oaDefineFormVersion.getId());
        oaDefineForm.setEditVersionNo("1");
        oaDefineForm.setStatus(OaDefineConstant.OA_DEFINE_FORM_STATUS_EDIT);
        return oaDefineFormVersionService.save(oaDefineFormVersion) && super.save(oaDefineForm);
    }

    private OaDefineFormVersion initOaDefineFormVersion(OaDefineForm oaDefineForm) {
        OaDefineFormVersion oaDefineFormVersion = new OaDefineFormVersion();
        oaDefineFormVersion.setId(UUIDGenerator.generate());
        oaDefineFormVersion.setFormName(oaDefineForm.getName());
        oaDefineFormVersion.setFormId(oaDefineForm.getId());
        oaDefineFormVersion.setVersionNo("1");
        oaDefineFormVersion.setStatus(OaDefineConstant.OA_DEFINE_FORM_STATUS_EDIT);
        return oaDefineFormVersion;
    }
    public boolean removeById(Serializable id) {
        OaDefineForm oaDefineForm = getById(id);
        QueryWrapper<OaDefineFormVersion> wrapper = new QueryWrapper();
        wrapper.eq("Form_id", id);
        return oaDefineFormVersionService.remove(wrapper) && super.removeById(id);
    }

    @Override
    /**
     * 发布流程版本
     */
    public void audit(OaDefineForm oaDefineForm) {
        // 流程版本发布
        oaDefineFormVersionService.audit(oaDefineForm);
        // 修改流程定义状态
        oaDefineForm.setPublishVersionId(oaDefineForm.getEditVersionId());
        oaDefineForm.setPublishVersionNo(oaDefineForm.getEditVersionNo());
        oaDefineForm.setEditVersionId("");
        oaDefineForm.setEditVersionNo("");
        oaDefineForm.setStatus(OaDefineConstant.OA_DEFINE_FORM_STATUS_AUDITED);
        updateById(oaDefineForm);
    }

    /**
     * TODO 配置新版本
     * @param id
     * @return
     */
    @Override
    public OaDefineForm addConfig(String id) {
        OaDefineForm oaDefineForm = getById(id);
        OaDefineFormVersion oaDefineFormVersion = oaDefineFormVersionService.getById(oaDefineForm.getPublishVersionId());
        oaDefineFormVersion.setId(UUIDGenerator.generate());
        oaDefineFormVersion.setVersionNo(String.valueOf(Integer.valueOf(oaDefineFormVersion.getVersionNo()) + 1));
        oaDefineFormVersion.setStatus(OaDefineConstant.OA_DEFINE_FORM_STATUS_EDIT);
        oaDefineFormVersionService.save(oaDefineFormVersion);
        oaDefineForm.setEditVersionId(oaDefineFormVersion.getId());
        oaDefineForm.setEditVersionNo(oaDefineFormVersion.getVersionNo());
        oaDefineForm.setStatus(OaDefineConstant.OA_DEFINE_FORM_STATUS_NEW_EDIT);
        updateById(oaDefineForm);
        return oaDefineForm;
    }

}
