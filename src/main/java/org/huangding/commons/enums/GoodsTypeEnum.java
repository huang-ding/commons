package org.huangding.commons.enums;

/**
 * @author huangding
 * @description
 * @date 2018/11/13 9:46
 */
public enum GoodsTypeEnum {

    /**
     * 1=食品
     */
    FOOD(1, "食品"),
    /**
     * 饮料
     */
    DRINK(2, "饮料"),
    /**
     * 日用品
     */
    NECESSARY(3, "日用品"),
    /**
     * 奶粉
     */
    DRIED_MILK(4, "奶粉"),
    /**
     * 婴儿用品
     */
    BABY_PRODUCT(5, "婴儿用品"),
    /**
     * 保健品
     */
    HEALTH_CARE_PRODUCTS(6, "保健品"),
    /**
     * 化妆
     */
    MAKEUP(7, "化妆"),
    /**
     * 尿不湿
     */
    DIAPERS(8, "尿不湿"),
    /**
     * 牛奶
     */
    MIKE(9, "牛奶"),
    /**
     * 红酒
     */
    RED_WINE(10, "红酒"),
    /**
     * 洋酒
     */
    WINE(11, "洋酒"),
    /**
     * 花王卫生巾
     */
    HW_SANITARY_NAPKIN(12, "花王卫生巾"),
    /**
     * 贵爱娘卫生巾
     */
    GAN_SANITARY_NAPKIN(13, "贵爱娘卫生巾"),
    /**
     * 油粮
     */
    OIL_FOOD(14, "油粮"),
    /**
     * 杯
     */
    CUP(15, "杯"),
    /**
     * 锅
     */
    POT(16, "锅"),
    /**
     * 牙膏
     */
    TOOTHPASTE(17, "牙膏"),
    /**
     * 牙刷
     */
    TOOTHBRUSH(18, "牙刷"),
    /**
     * 双肩包
     */
    BACKPACK(19, "双肩包"),
    /**
     * 洗发水
     */
    SHAMPOO(20, "洗发水"),
    /**
     * 护发素
     */
    CONDITIONER(21, "护发素"),
    /**
     * 沐浴露
     */
    SHOWER_GEL(22, "沐浴露"),
    /**
     * 面膜
     */
    MASK(23, "面膜"),
    /**
     * 刀具
     */
    CUTLERY(24, "刀具"),
    ;

    private final int code;

    private final String remarks;

    GoodsTypeEnum(int code, String remarks) {
        this.code = code;
        this.remarks = remarks;
    }

    public static GoodsTypeEnum forInt(int code) {
        GoodsTypeEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            GoodsTypeEnum type = arr$[i$];
            if (type.code == code) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid GoodsType code: " + code);
    }

    public int getCode() {
        return this.code;
    }

    public String getRemarks() {
        return remarks;
    }
}
