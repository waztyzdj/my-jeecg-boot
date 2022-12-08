package org.jeecg.modules.oa.define.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.modules.oa.define.constant.OaDefineConstant;
import org.jeecg.modules.oa.define.entity.OaDefineFlow;
import org.jeecg.modules.oa.define.entity.OaDefineFlowLink;
import org.jeecg.modules.oa.define.entity.OaDefineFlowNode;
import org.jeecg.modules.oa.define.entity.OaDefineFlowVersion;
import org.jeecg.modules.oa.define.mapper.OaDefineFlowVersionMapper;
import org.jeecg.modules.oa.define.service.IOaDefineFlowLinkService;
import org.jeecg.modules.oa.define.service.IOaDefineFlowNodeService;
import org.jeecg.modules.oa.define.service.IOaDefineFlowVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 流程版本
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Service
public class OaDefineFlowVersionServiceImpl extends ServiceImpl<OaDefineFlowVersionMapper, OaDefineFlowVersion> implements IOaDefineFlowVersionService {

    @Autowired
    private IOaDefineFlowNodeService oaDefineFlowNodeService;

    @Autowired
    private IOaDefineFlowLinkService oaDefineFlowLinkService;

    /**
     * 流程版本发布
     * @param oaDefineFlow
     */
    @Override
    public void audit(OaDefineFlow oaDefineFlow) {
        OaDefineFlowVersion oaDefineFlowVersion = super.getById(oaDefineFlow.getEditVersionId());
        oaDefineFlowVersion.setStatus(OaDefineConstant.OA_DEFINE_FLOW_STATUS_AUDITED);
        JSONObject jsonObj = JSON.parseObject(oaDefineFlowVersion.getConfigJson());
        saveFlowNodeList(oaDefineFlowVersion, jsonObj.getJSONArray("nodeList"));
        saveFlowLinkList(oaDefineFlowVersion, jsonObj.getJSONArray("linkList"));
        super.updateById(oaDefineFlowVersion);
    }

    /**
     * TODO 保存流程节点
     * @param oaDefineFlowVersion
     * @param nodeList
     */
    private void saveFlowNodeList(OaDefineFlowVersion oaDefineFlowVersion, List nodeList) {
        for(Object obj : nodeList) {
            OaDefineFlowNode oaDefineFlowNode = JSON.parseObject(((JSONObject) obj).toJSONString() , OaDefineFlowNode.class);
            oaDefineFlowNode.setFlowId(oaDefineFlowVersion.getFlowId());
            oaDefineFlowNode.setVersionId(oaDefineFlowVersion.getId());
            oaDefineFlowNode.setNodeId(oaDefineFlowNode.getId());
            oaDefineFlowNode.setId(UUIDGenerator.generate());
            oaDefineFlowNodeService.save(oaDefineFlowNode);
        }
    }

    /**
     * TODO 保存流程节点链接
     * @param oaDefineFlowVersion
     * @param linkList
     */
    private void saveFlowLinkList(OaDefineFlowVersion oaDefineFlowVersion, JSONArray linkList) {
        for(Object obj : linkList) {
            OaDefineFlowLink oaDefineFlowLink = JSON.parseObject(((JSONObject) obj).toJSONString() , OaDefineFlowLink.class);
            oaDefineFlowLink.setFlowId(oaDefineFlowVersion.getFlowId());
            oaDefineFlowLink.setVersionId(oaDefineFlowVersion.getId());
            oaDefineFlowLink.setLinkId(oaDefineFlowLink.getId());
            oaDefineFlowLink.setId(UUIDGenerator.generate());
            oaDefineFlowLinkService.save(oaDefineFlowLink);
        }
    }
}
