package com.example.hario.networksecurity;

public class SMS_API_POJO {

        private String otp;

        public String getOtp ()
        {
            return otp;
        }

        public void setOtp (String otp)
        {
            this.otp = otp;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [otp = "+otp+"]";
        }

}
