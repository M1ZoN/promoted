package io.jenkins.plugins.sample;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.model.Action;
import hudson.model.Project;
import jenkins.model.TransientActionFactory;

import java.beans.Transient;
import java.util.Collection;
import java.util.Collections;

public class MyActionFactory extends TransientActionFactory<Project> {


    @Override
    public Class<Project> type() {
        return Project.class;
    }

    @NonNull
    @Override
    public Collection<? extends Action> createFor(@NonNull Project target) {
        return Collections.singleton(new PromotedBuildAction("Promoted"));
    }
}
