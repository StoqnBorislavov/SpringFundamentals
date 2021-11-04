package softuni.mobile.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import softuni.mobile.model.view.StatsView;
import softuni.mobile.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {


    private int anonymousRequest;
    private int authRequest;

    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            authRequest++;
        } else {
            anonymousRequest++;
        }
    }

    @Override
    public StatsView getStats() {
        return new StatsView(authRequest, anonymousRequest);
    }
}
