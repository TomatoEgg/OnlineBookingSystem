package com.mycompany.project.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.mycompany.project.client.mvp.LoginView;
import com.mycompany.project.client.mvp.OperationView;
import com.mycompany.project.client.mvp.ViewProfileView;

public interface ClientFactory 
{
  EventBus getEventBus();
  PlaceController getPlaceController();
  LoginView getLoginView();
  OperationView getOperationView();
  ViewProfileView getViewProfileView();
}
