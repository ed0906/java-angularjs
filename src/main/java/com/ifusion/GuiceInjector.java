package com.ifusion;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceInjector {

    private static Injector injector;

    public static Injector getInjector() {
        if(injector == null) {
            injector = Guice.createInjector(new GuiceModule());
        }
        return injector;
    }

    private static class GuiceModule extends AbstractModule {
        @Override
        protected void configure() {
        }
    }
}
