package com.mtl.crawler.scheduler.enums;

import com.juqitech.entity.EntityEnum;

/**
 * TODO：仅用于演示Enum定义风格，正式项目请删除
 *
 * @author ZhangFeng
 * @since 2020/01/06
 */
public enum DemoEnum implements EntityEnum {

    DEMO(1, "DEMO", "演示");

    private int code;
    private String name;
    private String desc;

    DemoEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return desc;
    }

    public static DemoEnum getByCode(int code) {
        for (DemoEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }
}
