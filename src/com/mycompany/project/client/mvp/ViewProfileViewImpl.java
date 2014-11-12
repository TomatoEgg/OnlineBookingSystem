/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.mycompany.project.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.project.resources.Resources;
import com.mycompany.project.shared.UserInfo;

/**
 * Sample implementation of {@link ViewProfileView}.
 */
public class ViewProfileViewImpl extends Composite implements ViewProfileView, 
                                                              ClickHandler,
                                                              ChangeHandler, 
                                                              KeyUpHandler,
                                                              ProvidesResize,
                                                              RequiresResize
{
 	interface Binder extends UiBinder<Widget, ViewProfileViewImpl> 
 	{
 	}
 	
 	private static final Binder binder = GWT.create(Binder.class);
 
 	private Presenter presenter;
 	
 	@UiField (provided=true) FlexTable table;
 	@UiField Button btSave;
  @UiField FlowPanel container;
  
 	public ViewProfileViewImpl() 
 	{
 	  setupTable();
 		 initWidget(binder.createAndBindUi(this));
 		 container.addAttachHandler(new AttachEvent.Handler()
    {
 		   @Override
      public void onAttachOrDetach(AttachEvent event)
      {
        int width = Window.getClientWidth();
        int height = Window.getClientHeight();

        //Add left&top padding to the container panel so that it will appear at the center of the browser.
        String paddingLeft = Integer.toString(width/3) + "px";
        String paddingTop  = Integer.toString(height/3)+ "px";
        container.getElement().setAttribute("style", "padding-left:" + paddingLeft + ";"
                                                   + "padding-top:" + paddingTop + ";");
      }
    });
 	}
 
  private void setupTable()
  {
 	  table = new FlexTable();
 	  table.setCellSpacing(10);
 	  table.setText(0, 0, "Your Profile");
    table.getFlexCellFormatter().setAlignment(0, 0, 
                                              HasHorizontalAlignment.ALIGN_CENTER, 
                                              HasVerticalAlignment.ALIGN_MIDDLE);
    table.getFlexCellFormatter().setColSpan(0, 0, 2); //The first row should take two columns

    //Set static data for first name text box
    TextBox tbFirstName = new TextBox();
    tbFirstName.setReadOnly(true); //init to read only
    tbFirstName.getElement().setAttribute("placeHolder", "Type your first name"); //Set the hint text shown while the text box is empty
    tbFirstName.addClickHandler(this);
    tbFirstName.addChangeHandler(this);
    
    int row = 1;
    table.setText(row, 0, "First Name");
    table.setWidget(row, 1, tbFirstName.asWidget());
    row++;
    
    //Set static data for last name text box
    TextBox tbMiddleName = new TextBox();
    tbMiddleName.setReadOnly(true); //init to read only
    tbMiddleName.getElement().setAttribute("placeHolder", "Type your middle name"); //Set the hint text shown while the text box is empty
    tbMiddleName.addClickHandler(this);
    tbMiddleName.addChangeHandler(this);
    
    table.setText(row, 0, "Middle Name");
    table.setWidget(row, 1, tbMiddleName.asWidget());
    row++;
    
    //Set static data for last name text box
    TextBox tbLastName = new TextBox();
    tbLastName.setReadOnly(true); //init to read only
    tbLastName.getElement().setAttribute("placeHolder", "Type your last name"); //Set the hint text shown while the text box is empty
    tbLastName.addClickHandler(this);
    tbLastName.addChangeHandler(this);
    
    table.setText(row, 0, "Last Name");
    table.setWidget(row, 1, tbLastName.asWidget());
    row++;
    
    //Set static data for last name text box
    TextBox tbEmail = new TextBox();
    tbEmail.setReadOnly(true); //init to read only
    tbEmail.getElement().setAttribute("placeHolder", "Type your Email address"); //Set the hint text shown while the text box is empty
    tbEmail.addClickHandler(this);
    tbEmail.addChangeHandler(this);
    
    table.setText(row, 0, "Email");
    table.setWidget(row, 1, tbEmail.asWidget());
    row++; 
    
    //Set static data for mobile number text box
    TextBox tbMobileNumber= new TextBox();
    tbMobileNumber.setReadOnly(true); //init to read only
    tbMobileNumber.getElement().setAttribute("placeHolder", "Type your mobile number"); //Set the hint text shown while the text box is empty
    tbMobileNumber.addClickHandler(this);
    tbMobileNumber.addChangeHandler(this);
    tbMobileNumber.addKeyUpHandler(this);
    tbMobileNumber.setName("tbMobileNumber");
    
    table.setText(row, 0, "Mobile Number");
    table.setWidget(row, 1, tbMobileNumber.asWidget());
    row++;
    
    //set the style for each labels
    for (int i = 0; i < row; ++i)
    {
      if (i == 0)
      {
        table.getFlexCellFormatter().addStyleName(i, 0, Resources.INSTANCE.style().profileTitle());
      }
      else
      {
        table.getFlexCellFormatter().addStyleName(i, 0, Resources.INSTANCE.style().viewProfileView_Labels());
      }
    }
  }

  @Override
 	public void setPresenter(Presenter listener) 
 	{
 	 	this.presenter = listener;
 	}

  @Override
  public void setUserInfo(UserInfo profile)
  {
    int row = 1;
    TextBox tb = (TextBox)(table.getWidget(row++, 1));
    tb.setText(profile.getFirstName());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    tb.setText(profile.getMiddleName());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    tb.setText(profile.getLastName());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    tb.setText(profile.getEmail());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    tb.setText(profile.getMobileNumber());
    
    btSave.setEnabled(false); //default disabled.
  }
  
  /*
   * return a current snapshot of the user profile
   * which may have been updated by the user
   */
  private UserInfo getCurrentUserInfo()
  {
    UserInfo currentUserInfo = new UserInfo();
    
    int row = 1;
    
    TextBox tb = (TextBox)(table.getWidget(row++, 1));
    currentUserInfo.setFirstName(tb.getText());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    currentUserInfo.setMiddleName(tb.getText());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    currentUserInfo.setLastName(tb.getText());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    currentUserInfo.setEmail(tb.getText());
    
    tb = (TextBox)(table.getWidget(row++, 1));
    currentUserInfo.setMobileNumber(tb.getText());
    return currentUserInfo;
  }

  @Override
  public void onClick(ClickEvent event)
  {
    ((TextBox)event.getSource()).setReadOnly(false); //when it is clicked, make textbox editable
  }

  @Override
  public void onChange(ChangeEvent event)
  {
    UserInfo currentUserInfo = getCurrentUserInfo();
    if (presenter.hasUnsavedFields(currentUserInfo))
    {
      btSave.setEnabled(true);
    }
    else
    {
      btSave.setEnabled(false);
    }
  }

  @Override
  public void onKeyUp(KeyUpEvent event)
  {
    TextBox tb = (TextBox)event.getSource(); 
    if (tb.getName().equals("tbMobileNumber"))
    {
      String input = tb.getText();
      if (!input.matches("[0-9]*")) 
      {
        tb.setText(input.substring(0, input.length() - 1));
        Window.alert("Only digits (0-9) are allowed");
      }
    }
  }

  @Override
  public void onResize()
  {
    int width = Window.getClientWidth();
    int height = Window.getClientHeight();

    String paddingLeft = Integer.toString(width/2) + "px";
    String paddingTop  = Integer.toString(height/2)+ "px";
    container.getElement().setAttribute("style", "padding-left:" + paddingLeft + ";"
                                                 + "padding-top:" + paddingTop + ";");
  }
}
