package softuni.mobile.service;

import softuni.mobile.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
