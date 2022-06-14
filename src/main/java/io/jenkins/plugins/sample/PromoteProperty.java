package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.*;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

public class PromoteProperty extends JobProperty<Job<?, ?>>{
    private boolean promote;

    @DataBoundConstructor
    public PromoteProperty(boolean promote) {
        this.promote = promote;
    }

    public boolean getPromote() {
        return promote;
    }

    public void setPromote(boolean promote) {
        this.promote = promote;
    }

    public static PromoteDescriptor get() {
        return (PromoteDescriptor) Jenkins.get().getDescriptor(PromoteProperty.class);
    }


    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {

        if (promote) {
            build.addAction(new PromotedBuildAction(promote ? "Promoted" : "Not Promoted"));
        }
        listener.getLogger().println("This is logged from Promote Property. isPromote checked?: " + promote);
        return super.perform(build, launcher, listener);
    }

    @Override
    public PromoteDescriptor getDescriptor() {
        return (PromoteDescriptor) Jenkins.get().getDescriptor(getClass());
    }

    @Extension
    public static final class PromoteDescriptor extends JobPropertyDescriptor {
//        private String status;

        public PromoteDescriptor() {
            load();
        }

        @Override
        public String getDisplayName() {
            return "Manual build promotion";
        }

//        public String getStatus() {
//            return status;
//        }
    }
}