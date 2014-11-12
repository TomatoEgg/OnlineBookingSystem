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

import com.mycompany.project.client.ClientFactory;
import com.mycompany.project.client.mvp.LoginPlace;
import com.mycompany.project.client.mvp.LoginView;
import com.mycompany.project.client.rpc.LoginService;
import com.mycompany.project.client.rpc.LoginServiceAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class LoginActivity extends AbstractActivity implements LoginView.Presenter 
{
	 private final String userName;
	 private final String password;
  private LoginServiceAsync LoginService;
  private static ClientFactory clientFactory;
  private static PlaceController placeController;
  
 	public LoginActivity(LoginPlace place, ClientFactory clientFactory) 
 	{
  		userName = place.getUserName();
  		password = place.getPassword();
  		LoginActivity.clientFactory = clientFactory;
  		LoginActivity.placeController = clientFactory.getPlaceController();
  		LoginService = (LoginServiceAsync)GWT.create(LoginService.class);
 	}
 
 	@Override
 	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
 	{
  		LoginView view = clientFactory.getLoginView();
  		view.setUserName(userName);
  		view.setPassword(password);
  		view.setPresenter(this);
  		containerWidget.setWidget(view.asWidget());
 	}

//  If login successfully, there is no need to warn the user
// 	@Override
// 	public String mayStop() 
// 	{
// 	}
 
 	/**
 	 * @see LoginView.Presenter#goTo(Place)
 	 */
 	public void goTo(Place place) 
 	{
 	  placeController.goTo(place); 
 	}

  @Override
  public void authenticateUser(final String user, final String password)
  {
    LoginService.authenticateUser(user, password, new AsyncCallback<Boolean>()
    {
      @Override
      public void onFailure(Throwable caught) 
      {
        Window.alert("RPC call fails: " + caught.toString());
      }

      @Override
      public void onSuccess(Boolean result) 
      {
        if (result)
        {
          //The user has been verified and thus we should move to a new place
          goTo(new OperationPlace(user));
        }
        else
        {
          Window.alert("Your user name and/or password is wrong!");
        }
      }
    });
  }
}
