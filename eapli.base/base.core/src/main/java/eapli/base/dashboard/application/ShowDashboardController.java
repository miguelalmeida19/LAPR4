package eapli.base.dashboard.application;

import eapli.base.dashboard.domain.HttpServerAgvStatus;

public class ShowDashboardController {

    public void showDashboard()  {
        HttpServerAgvStatus server = new HttpServerAgvStatus();
        server.changeController(this);
        server.start();
    }
}
