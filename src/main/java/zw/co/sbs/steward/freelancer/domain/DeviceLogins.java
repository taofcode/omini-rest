package zw.co.sbs.steward.freelancer.domain;

import javax.persistence.*;

@Entity
public class DeviceLogins {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String status;

    private String deviceId;

    private  String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
