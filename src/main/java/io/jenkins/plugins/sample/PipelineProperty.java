package io.jenkins.plugins.sample;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.console.ConsoleLogFilter;
import hudson.model.*;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;
import jenkins.model.Jenkins;
import jenkins.tasks.SimpleBuildWrapper;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

public class PipelineProperty extends SimpleBuildWrapper {
    private boolean promote;

    @DataBoundConstructor
    public PipelineProperty(boolean promote) {
        this.promote = promote;
    }

    public boolean getPromote() {
        return promote;
    }

    public void setPromote(boolean promote) {
        this.promote = promote;
    }

    public static PipelineDescriptor get() {
        return (PipelineDescriptor) Jenkins.get().getDescriptor(PromoteProperty.class);
    }

    @Override
    public PipelineDescriptor getDescriptor() {
        return (PipelineDescriptor) Jenkins.get().getDescriptor(getClass());
    }

    @Override
    public void setUp(Context context, Run<?, ?> build, FilePath workspace, Launcher launcher, TaskListener listener, EnvVars initialEnvironment) throws IOException, InterruptedException {
        listener.getLogger().println("TESTING THE LOGGING INSIDE PROMOTE PROPERTY");
        if (promote) {
            build.addAction(new PromotedBuildAction("Promoted"));
        }
        super.setUp(context, build, workspace, launcher, listener, initialEnvironment);
    }

    @Extension
    public static final class PipelineDescriptor extends BuildWrapperDescriptor {

        private String status;

        public PipelineDescriptor() {
            load();
        }

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Manual build promotion";
        }

        public String getStatus() {
            return status;
        }
    }
}
