package com.mycompany.project.client.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.mycompany.project.shared.UserInfo;

public interface OperationView extends IsWidget
{
  void setPresenter(Presenter presenter);
  void setName(String name);
  void displayAllUsers(UserInfo[] usersInfo);
  
  public interface Presenter 
  {
    /**
     * Navigate to a new Place in the browser.
     */
    void goTo(Place place);
    void updateUserInfo(UserInfo userInfo);
    void getAllStudents();
  }
}
