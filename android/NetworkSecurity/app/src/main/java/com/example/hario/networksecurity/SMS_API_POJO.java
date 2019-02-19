package com.example.hario.networksecurity;

public class SMS_API_POJO {

        private String otp;
        private String sms;
        public String getOtp ()
        {
            return otp;
        }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "SMS_API_POJO{" +
                "otp='" + otp + '\'' +
                ", sms='" + sms + '\'' +
                '}';
    }

    public void setOtp (String otp)
        {
            this.otp = otp;
        }

}
