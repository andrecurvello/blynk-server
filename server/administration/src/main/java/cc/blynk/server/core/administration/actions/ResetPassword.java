package cc.blynk.server.core.administration.actions;

import cc.blynk.server.core.administration.Executable;
import cc.blynk.server.dao.SessionsHolder;
import cc.blynk.server.dao.UserRegistry;
import cc.blynk.server.model.auth.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for auto user pass changing. Expected hashed password
 *
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 21.04.15.
 */
public class ResetPassword implements Executable {

    private static final Logger log = LogManager.getLogger(ResetPassword.class);

    @Override
    public List<String> execute(UserRegistry userRegistry, SessionsHolder sessionsHolder, String... params) {
        List<String> result = new ArrayList<>();
        if (params != null && params.length == 2) {
            String userName = params[0];
            String newPass = params[1];

            User user = userRegistry.getByName(userName);
            if (user == null) {
                log.error("User '{}' not exists.", userName);
            } else {
                user.setPass(newPass);
                user.setLastModifiedTs(System.currentTimeMillis());
                log.info("Password updated");
                result.add("Password updated.\n");
            }
        }
        log.info("ok");
        result.add("ok\n");
        return result;
    }

}
