package com.mycompany.project.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mycompany.project.client.ClientFactory;
import com.mycompany.project.client.mvp.LoginActivity;

public class BookingSystemActivityMapper implements ActivityMapper
{
  private static ClientFactory clientFactory; 
  
  public BookingSystemActivityMapper(ClientFactory clientFactory)
  {
    BookingSystemActivityMapper.clientFactory = clientFactory;
  }
  
  @Override
  public Activity getActivity(Place place)
  {
    if (place instanceof LoginPlace)
    {
      return new LoginActivity((LoginPlace) place, clientFactory);
    }
    else if (place instanceof OperationPlace)
    {
      return new OperationActivity((OperationPlace) place, clientFactory);
    }
    else if (place instanceof ViewProfilePlace)
    {
      return new ViewProfileActivity((ViewProfilePlace)place, clientFactory);
    }
    else
    {
      return null;
    }
  }
}
