package io.jenkins.plugins.sample;

import hudson.model.Run;
import jenkins.model.RunAction2;

public class PromotedBuildAction implements RunAction2 {
    private String status;
    private transient Run run;

    public PromotedBuildAction(String status) {
        this.status = status;
    }

    public Run getRun() {
        return run;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void onAttached(Run<?, ?> r) {
        this.run = r;
    }

    @Override
    public void onLoad(Run<?, ?> r) {
        this.run = r;
    }

    @Override
    public String getIconFileName() {
        return "star.png";
    }

    @Override
    public String getDisplayName() {
        return "Promotion Status";
    }

    @Override
    public String getUrlName() {
        return "promote";
    }
}
