package eapli.base.app.backoffice.console.presentation.dashboard;

import eapli.base.dashboard.application.ShowDashboardController;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ShowDashboardUI extends AbstractUI {
    private final ShowDashboardController theController = new ShowDashboardController();

    @Override
    protected boolean doShow() {
        theController.showDashboard();

        URI uri;
        try {
            uri = new URI("https://localhost:55034/");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Dashboard";
    }
}
