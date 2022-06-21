package io.jenkins.plugins.sample;

import hudson.model.Run;
//import jenkins.model.RunAction2;
import hudson.model.Action;

public class PromotedBuildAction implements Action {
    private String status;
    private transient Run run;

    public PromotedBuildAction(String status, Run run) {
        this.run = run;
        this.status = status;
    }

    public Run getRun() {
        return run;
    }

    public String getStatus() {
        return status;
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
