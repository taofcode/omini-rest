package zw.co.sbs.steward.freelancer.service;
import zw.co.sbs.steward.freelancer.domain.Audits;
import zw.co.sbs.steward.freelancer.domain.DeviceLogins;
import zw.co.sbs.steward.freelancer.domain.Devices;
import zw.co.sbs.steward.freelancer.domain.User;

import javax.ws.rs.core.Response;


public interface UserServices  extends GenericDao<User> {


    @Override
    User save(User eontity);

     User findOne(String id);

     Response login(User user);

     Response registerDevice(Devices device);

    void logOutDevice(DeviceLogins logins);

    void saveAudit(Audits audits);
}