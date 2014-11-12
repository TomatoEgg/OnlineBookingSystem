package com.mycompany.project.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mycompany.project.client.ClientFactory;
import com.mycompany.project.client.mvp.OperationView.Presenter;
import com.mycompany.project.client.rpc.LoginService.Util;
import com.mycompany.project.client.rpc.LoginServiceAsync;
import com.mycompany.project.shared.UserInfo;

public class OperationActivity extends AbstractActivity implements Presenter
{
  private static ClientFactory clientFactory;
  private LoginServiceAsync LoginService;
  private OperationView view;
  private String name;
  
  public OperationActivity(OperationPlace operationPlace, ClientFactory clientFactory)
  {
    OperationActivity.clientFactory = clientFactory;
    LoginService = Util.getInstance();
    setName(operationPlace.getUserName());
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus)
  {
    view = clientFactory.getOperationView();
    view.setPresenter(this);
    view.setName(getName());
    panel.setWidget(view.asWidget());
  }

  @Override
  public void goTo(Place place)
  {
    clientFactory.getPlaceController().goTo(place);
  }
  
  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name)
  {
    this.name = name;
  }

  @Override
  public void updateUserInfo(UserInfo userInfo)
  {
    LoginService.updateUserInfo(userInfo, new AsyncCallback<Boolean>()
    {
      @Override
      public void onFailure(Throwable caught)
      {
        System.out.println("RPC failure" + caught.toString());
      }

      @Override
      public void onSuccess(Boolean result)
      {
        //This new record has been successfully stored into the database
        if (result)
        {
          System.out.println("user info successfully updated!");
          Window.alert("Successfully updated!");
        }
        else
        {
          System.out.println("something is wrong on the server side!");
        }
      }
    });
  }

  @Override
  public void getAllStudents()
  {
    LoginService.getAllUsers(new AsyncCallback<UserInfo[]>()
    {
      @Override
      public void onFailure(Throwable caught)
      {
        Window.alert("Fails to show all users");
      }

      @Override
      public void onSuccess(UserInfo[] result)
      {
        view.displayAllUsers(result);
      }
    });
  }
}
