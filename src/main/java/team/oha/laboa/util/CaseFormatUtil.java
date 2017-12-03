package team.oha.laboa.util;

import com.google.common.base.CaseFormat;

public class CaseFormatUtil {
    public static String lowerCamelToLowerUnderScore(String source){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, source);
    }
}
