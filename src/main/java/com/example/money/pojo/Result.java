package com.example.money.pojo;

import lombok.Data;

/**
 * @author lzx
 * @date 2023-03-23 17:03
 * @description
 */
@Data
public class Result {
        private String Code;
        private String Message;
        private String Status;

        public String getCode() {
            return Code;
        }

        public void setCode(String code) {
            Code = code;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }



}
