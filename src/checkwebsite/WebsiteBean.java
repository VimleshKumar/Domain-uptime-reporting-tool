/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkwebsite;

/**
 *
 * @author Vimlesh
 */
public class WebsiteBean {
    int respCode, respTime, webSize;
    String status, currTime;

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getRespTime() {
        return respTime;
    }

    public void setRespTime(int respTime) {
        this.respTime = respTime;
    }

    public int getWebSize() {
        return webSize;
    }

    public void setWebSize(int webSize) {
        this.webSize = webSize;
    }

    
}
