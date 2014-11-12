package com.mycompany.project.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.project.resources.Resources;
import com.mycompany.project.shared.UserInfo;

public class OperationViewImpl extends Composite implements OperationView
{
  private Presenter presenter;
  
  private static OperationViewImpl1UiBinder uiBinder = 
      GWT.create(OperationViewImpl1UiBinder.class);

  interface OperationViewImpl1UiBinder extends
      UiBinder<Widget, OperationViewImpl>
  {
  }

  public OperationViewImpl()
  {
    Resources resources = Resources.INSTANCE;
    resources.style().ensureInjected();
    initWidget(uiBinder.createAndBindUi(this));
    //Set bar panel dimension
    barHPanel.setHeight(Window.getClientHeight() / 10 + "px");

    popup.setVisible(false); //hide it until the label has been clicked.
    
    lbName.addMouseOverHandler(new MouseOverHandler()
    {
      @Override
      public void onMouseOver(MouseOverEvent event)
      {
        lbName.getElement().getStyle().setCursor(Cursor.POINTER);
      }
      
    });
    
    lbName.addClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent event)
      {
        //Make the popup window visible
        popup.setVisible(true);
      }
    });
    
    viewProfileButton.addClickHandler(new ClickHandler()
    {
      @Override
      public void onClick(ClickEvent event)
      {
        //go to "view profile" place where all info regarding this user shall be displayed
        popup.setVisible(false);
        presenter.goTo(new ViewProfilePlace(lbName.getText()));
      }
    });
    
    tbAllStudent.setVisible(false); //Hide it until the user wants to see info of all students, otherwise an ugly square will be default shown.
  }
  
  //UiFields for the frame components
  @UiField
  Label lbName;
  @UiField
  HTMLPanel barHPanel;
  
  //UiFields for "Add A New Student" tab
  @UiField
  TextBox firstName;

  @UiField
  TextBox middleName;
  
  @UiField
  TextBox lastName;
  
  @UiField
  TextBox email;

  @UiField
  TextBox streeName;
  
  @UiField
  TextBox city;
  
  @UiField
  TextBox postCode;
  
  @UiField
  TextBox mobileNumber;
  
  @UiField
  PasswordTextBox tbPassword;
  
  @UiField
  Button btSubmit;
  
  @UiField
  Button btShowAllStudent;
  
  @UiField
  FlexTable tbAllStudent;
  
  @UiField
  HTMLPanel popup;
  
  @UiField
  Label userName;
  
  @UiField
  Button viewProfileButton;
  
  @UiField
  Button btSignoff; //TODO: Add handler
  
  @Override
  public void setPresenter(Presenter presenter)
  {
    this.presenter = presenter;
  }

  @Override
  public void setName(String name)
  {
    lbName.setText(name);  
    userName.setText(name);
  }

  @UiHandler("btSubmit")
  void onSubmitClick(ClickEvent event)
  {
    UserInfo userInfo = new UserInfo();

    //populate the names
    userInfo.setFirstName(firstName.getText());
    userInfo.setMiddleName(middleName.getText());
    userInfo.setLastName(lastName.getText());

    //populate the contact information
    userInfo.setStreet(streeName.getText());
    userInfo.setPostCode(postCode.getText());
    userInfo.setCity(city.getText());
    userInfo.setCountry("Sweden");
    userInfo.setEmail(email.getText());
    userInfo.setMobileNumber(mobileNumber.getText());

    //populate the password
    userInfo.setPassword(tbPassword.getText());
    
    //the id is up to the db to decide, so we don't have to populate it here.
    
    //Ask the presenter to add this user to the db.
    presenter.updateUserInfo(userInfo);
  }
  
  @UiHandler("btShowAllStudent")
  void onShowAllStudentClick(ClickEvent event)
  {
    presenter.getAllStudents();
  }

  @Override
  public void displayAllUsers(UserInfo[] usersInfo)
  {
    final String titleColumn[] = 
    {
      "ID",
      "First Name",
      "Middle Name",
      "Last Name",
      "Mobile Number",
      "Stree Name",
      "Post Code",
      "City",
      "Email",
      "Country"
    };
    
    for (int i = 0; i < titleColumn.length; ++i)
    {
      tbAllStudent.setText(0, i, titleColumn[i]);
    }
    
    for (int i = 0; i < usersInfo.length; ++i)
    {
      int j = 0;
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getId());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getFirstName());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getMiddleName());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getLastName());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getMobileNumber());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getStreet());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getPostCode());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getCity());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getEmail());
      tbAllStudent.setText(i + 1, j++, usersInfo[i].getCountry());
    }
    
    tbAllStudent.setVisible(true);
  }
}
