package com.mycompany.project.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.mycompany.project.client.mvp.LoginView;
import com.mycompany.project.client.mvp.LoginViewImpl;
import com.mycompany.project.client.mvp.OperationView;
import com.mycompany.project.client.mvp.OperationViewImpl;
import com.mycompany.project.client.mvp.ViewProfileView;
import com.mycompany.project.client.mvp.ViewProfileViewImpl;

public class ClientFactoryImpl implements ClientFactory 
{
  private static final EventBus eventBus = new SimpleEventBus();
  private static final PlaceController placeController 
                                         = new PlaceController(eventBus);
  private static final LoginView myLoginView = new LoginViewImpl();
  private static final OperationViewImpl myOperationView = new OperationViewImpl();
  private static final ViewProfileViewImpl myViewProfileView = new ViewProfileViewImpl();                                     
  @Override
  public EventBus getEventBus() 
  {
    return eventBus;
  }

  @Override
  public PlaceController getPlaceController() 
  {
    return placeController;
  }

  @Override
  public LoginView getLoginView()
  {
    return myLoginView;
  }

  @Override
  public OperationView getOperationView()
  {
    return myOperationView;
  }

  @Override
  public ViewProfileView getViewProfileView()
  {
    return myViewProfileView;
  }
}
