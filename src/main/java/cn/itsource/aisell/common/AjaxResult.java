package cn.itsource.aisell.common;

public class AjaxResult {
    private Boolean success = true;
    private String msg;

    public AjaxResult() {
    }

    public AjaxResult(String msg) {
        this.msg = msg;
    }

    public AjaxResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
