package com.hieu.demo.config;

public class Constants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
    public static final String SIGNING_KEY = "auth123key";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ROLE_ADMIN = "CapBridgeAdmin";
    public static final String ROLE_INVESTOR = "Investor";
    public static final String DEFAULT_USER_ROLE_KEY = "Default_Role_Key";
    public static final String MIN_ALLOWED_UNITS = "MinAllowedUnits_Key";
    public static final String COMMISSION_AMOUNT = "OrderCommission_Key";
    public static final String COMMISSION_PERCENTAGE = "OrderCommissionPercentage_Key";
    public static final String ORDER_EXPIRY_DAYS ="OrderDefaultExpiryDays_Key";
    public static final String TWOFACTORTOKEN_EXPIRY_MINS ="TwoFactorTokenExpiryMins_Key";
    public static final String TWOFACTORAUTH_MODE ="TwoFactorAuthMode_Key";
    public static final String OTPSMSMSG_LOGIN ="OTPSmsMsgLogin_Key";
    public static final String OTPSMSMSG_SIGNUP ="OTPSmsMsgSignup_Key";
    public static final String OTPSMSMSG_SELLORDER ="OTPSmsMsgSellOrder_Key";
    public static final String OTPSMSMSG_TRADE ="OTPSmsMsgTrade_Key";
    public static final String ENABLE_2FA ="Enable2fa_Key";
    public static final String ENABLE_2FA_SU ="Enable2faSignup_Key";
    public static final String ENABLE_2FA_SO ="Enable2faSellorder_Key";
    public static final String ENABLE_2FA_TD ="Enable2faTrade_Key";
}