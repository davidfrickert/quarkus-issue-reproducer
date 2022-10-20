package org.acme;

public class GreetingResourceImpl implements GreetingResource {

    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}