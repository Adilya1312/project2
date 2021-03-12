package org.apache.wicket;


import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {


    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
    @Override
    public void init()
    {
        super.init();
    }


}
