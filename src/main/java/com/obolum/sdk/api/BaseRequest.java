package com.obolum.sdk.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.obolum.sdk.client.DefaultObolumClient;
import com.obolum.sdk.config.MerchantConfig;
import com.obolum.sdk.domain.GatewayResult;

import java.lang.reflect.ParameterizedType;

/**
 * @author zhu.q
 */
public abstract class BaseRequest<RESP> {

    public GatewayResult<RESP> send(String merchantNo) {
        String result = DefaultObolumClient.getInstance().send(getApiName(), this, merchantNo);
        return JSON.parseObject(result, new TypeReference<GatewayResult<RESP>>(
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]) {
        });
    }

    public GatewayResult<RESP> sendByconfig(MerchantConfig config) {
        String result = DefaultObolumClient.getInstance().send(getApiName(), this, config);
        return JSON.parseObject(result, new TypeReference<GatewayResult<RESP>>(
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]) {
        });
    }

    public GatewayResult<RESP> send() {
        return this.send(null);
    }

    /**
     * 接口名称
     *
     * @return api name
     */
    protected abstract String getApiName();
}
