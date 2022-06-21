package io.jenkins.plugins.sample;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.*;
import jenkins.model.RunAction2;
import jenkins.model.TransientActionFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

@Extension
public class MyActionFactory extends TransientActionFactory<Run> {

    @Override
    public Class<Run> type() {
        return Run.class;
    }

    @NonNull
    @Override
    public Collection<? extends Action> createFor(@NonNull Run target) {
        return Collections.singleton(new PromotedBuildAction("Choose promotion", target));
    }
}
