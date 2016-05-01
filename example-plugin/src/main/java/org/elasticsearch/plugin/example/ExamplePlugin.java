package org.elasticsearch.plugin.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.inject.multibindings.Multibinder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.repositories.RepositoriesModule;
public class ExamplePlugin extends Plugin { 
@Override 
public String name() { 
return "example-plugin"; 
}
@Override 
public String description() { 
return "Example Plugin Description"; 
} 

@Override
public Collection<Module> nodeModules() {
        return Collections.<Module>singletonList(new ExampleRestModule());
    }
}
