@lombok.NoArgsConstructor
@lombok.Data
public class Data {

    @com.fasterxml.jackson.annotation.JsonProperty("err_code")
    private Integer errCode;
    @com.fasterxml.jackson.annotation.JsonProperty("err_msg")
    private String errMsg;
    @com.fasterxml.jackson.annotation.JsonProperty("data")
    private DataDTO data;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class DataDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("id")
        private String id;
        @com.fasterxml.jackson.annotation.JsonProperty("created_at")
        private String createdAt;
        @com.fasterxml.jackson.annotation.JsonProperty("updated_at")
        private String updatedAt;
        @com.fasterxml.jackson.annotation.JsonProperty("role")
        private Integer role;
        @com.fasterxml.jackson.annotation.JsonProperty("uid")
        private Integer uid;
        @com.fasterxml.jackson.annotation.JsonProperty("gender")
        private Integer gender;
        @com.fasterxml.jackson.annotation.JsonProperty("nick_name")
        private String nickName;
        @com.fasterxml.jackson.annotation.JsonProperty("avatar")
        private String avatar;
        @com.fasterxml.jackson.annotation.JsonProperty("language")
        private String language;
        @com.fasterxml.jackson.annotation.JsonProperty("wx_open_id")
        private String wxOpenId;
        @com.fasterxml.jackson.annotation.JsonProperty("wx_union_id")
        private String wxUnionId;
        @com.fasterxml.jackson.annotation.JsonProperty("last_login_time")
        private Object lastLoginTime;
        @com.fasterxml.jackson.annotation.JsonProperty("last_logout_time")
        private Object lastLogoutTime;
        @com.fasterxml.jackson.annotation.JsonProperty("charge_first_time")
        private Integer chargeFirstTime;
        @com.fasterxml.jackson.annotation.JsonProperty("charge_last_time")
        private Integer chargeLastTime;
        @com.fasterxml.jackson.annotation.JsonProperty("charge_total")
        private Integer chargeTotal;
        @com.fasterxml.jackson.annotation.JsonProperty("charge_times")
        private Integer chargeTimes;
    }
}
