package main.com.movieticketingsystem.java.common.constant;

/**
 * @className: UserConstant
 * @program: MovieTicketingSystem
 * @description: // 用户常量
 * @author: GirtSeanking
 * @create: 2021-06-28 07:27
 **/

public class Constant {

    /** 用户名和密码空格限制 */
    public static final String SPACE_REG = "^[^\\s]*$";

    /** 手机号码格式限制 */
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

    /** 用户角色判断 */
    public static final String COMMON_ROLE = "user";
    public static final String STAFF_ROLE = "staff";

    /* 列表类型 */
    public static final String LIKE_TYPE = "like";
    public static final String FAVORITES_TYPE = "favorites";

}