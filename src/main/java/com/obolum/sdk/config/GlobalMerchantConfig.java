package com.obolum.sdk.config;

import com.obolum.sdk.exceptions.ObolumException;
import com.obolum.sdk.enums.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhu.q
 */
public class GlobalMerchantConfig {

    private static MerchantConfig DEFAULT_MERCHANT_CONFIG = null;

    private static final Map<String, MerchantConfig> MERCHANT_CONFIG_MAP = new ConcurrentHashMap<>();


    public static void setDefaultConfig(MerchantConfig defaultMerchantConfig) {
        DEFAULT_MERCHANT_CONFIG = defaultMerchantConfig;
        MERCHANT_CONFIG_MAP.put(defaultMerchantConfig.getMerchantNo(), defaultMerchantConfig);
    }

    public static void addConfig(MerchantConfig config) {
        if (StringUtils.isEmpty(config.getMerchantNo())) {
            throw new ObolumException(ErrorCodeEnum.CONFIG_INVALID, "merchantNo is empty");
        }
        if (StringUtils.isEmpty(config.getAppId())) {
            throw new ObolumException(ErrorCodeEnum.CONFIG_INVALID, "appId is empty");
        }
        if (StringUtils.isEmpty(config.getMerchantPrivateKey())) {
            throw new ObolumException(ErrorCodeEnum.CONFIG_INVALID, "merchantPrivateKey is empty");
        }
        if (StringUtils.isEmpty(config.getObolumPublicKey())) {
            throw new ObolumException(ErrorCodeEnum.CONFIG_INVALID, "obolumPublicKey is empty");
        }
        MERCHANT_CONFIG_MAP.put(config.getMerchantNo(), config);
    }

    public static MerchantConfig getDefaultConfig() {
        if (DEFAULT_MERCHANT_CONFIG == null) {
            throw new ObolumException(ErrorCodeEnum.CONFIG_INVALID, "default merchant config is null");
        }
        return DEFAULT_MERCHANT_CONFIG;
    }

    public static MerchantConfig getConfig(String merchantNo) {

        if (StringUtils.isEmpty(merchantNo)) {
            throw new ObolumException(ErrorCodeEnum.PARAMS_INVALID, "merchantNo is empty");
        }

        MerchantConfig merchantConfig = MERCHANT_CONFIG_MAP.get(merchantNo);
        if (merchantConfig == null) {
            throw new ObolumException(ErrorCodeEnum.PARAMS_INVALID,
                    "can not find merchant config by merchantNo:" + merchantNo);
        }
        return merchantConfig;
    }

}
