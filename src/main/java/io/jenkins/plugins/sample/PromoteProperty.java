package io.jenkins.plugins.sample;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.*;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

@SuppressWarnings("unused")
public class PromoteProperty extends JobProperty<Job<?, ?>> {
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

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        if (promote) {
            build.addAction(new PromotedBuildAction("Promoted"));
        }
        return super.perform(build, launcher, listener);
    }

    public static PromoteDescriptor get() {
        return (PromoteDescriptor) Jenkins.get().getDescriptor(PromoteProperty.class);
    }

    @Override
    public PromoteDescriptor getDescriptor() {
        return (PromoteDescriptor) Jenkins.get().getDescriptor(getClass());
    }

    @Extension
    public static final class PromoteDescriptor extends JobPropertyDescriptor {

        private String status;

        public PromoteDescriptor() {
            load();
        }

        @Override
        public String getDisplayName() {
            return "Cucumber Slack Notifier";
        }

        public String getStatus() {
            return status;
        }
    }
}