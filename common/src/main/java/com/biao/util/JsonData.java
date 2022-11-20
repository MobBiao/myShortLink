package com.biao.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.biao.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {

    /**
     * 状态码 0 表示成功
     */

    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;


    /**
     * 获取远程调用数据
     * 注意事项：
     * 支持多单词下划线专驼峰（序列化和反序列化）
     *
     * @param typeReference 某类型的引用
     * @param <T>           泛型
     * @return 反序列化后的对象
     */
    public <T> T getData(TypeReference<T> typeReference) {
        return JSON.parseObject(JSON.toJSONString(data), typeReference);
    }

    /**
     * 成功，不传入数据
     *
     * @return JsonData
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    /**
     * 成功，传入数据
     *
     * @param data 数据
     * @return JsonData
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    /**
     * 失败，传入描述信息
     *
     * @param msg 信息
     * @return JsonData
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }


    /**
     * 自定义状态码和错误信息
     *
     * @param code 状态码
     * @param msg  错误信息
     * @return JsonData
     */
    public static JsonData buildCodeAndMsg(int code, String msg) {
        return new JsonData(code, null, msg);
    }

    /**
     * 传入枚举，返回信息
     *
     * @param codeEnum 枚举
     * @return JsonData
     */
    public static JsonData buildResult(BizCodeEnum codeEnum) {
        return JsonData.buildCodeAndMsg(codeEnum.getCode(), codeEnum.getMessage());
    }
}
