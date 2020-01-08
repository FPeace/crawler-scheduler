package com.mtl.crawler.scheduler.domain.po;

import com.juqitech.entity.BasePO;
import com.mtl.crawler.scheduler.enums.DemoEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO：仅用于演示PO定义风格，正式项目请删除。持久对象都继承自BasePO，且只有一重继承，只支持基本属性与枚举属性。
 *
 * @author ZhangFeng
 * @since 2020/01/06
 */
@Getter
@Setter
@ToString(callSuper = true)
public class DemoPO extends BasePO {

    /**
     * PO对象的属性使用"_"分隔
     */
    private String field_id;

    /**
     * 枚举属性
     */
    private DemoEnum status;

    /**
     * 布尔属性
     */
    private Boolean bool;
}