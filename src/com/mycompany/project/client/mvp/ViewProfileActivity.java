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
import com.mycompany.project.client.mvp.ViewProfilePlace;
import com.mycompany.project.client.mvp.ViewProfileView;
import com.mycompany.project.client.rpc.LoginServiceAsync;
import com.mycompany.project.client.rpc.LoginService.Util;
import com.mycompany.project.shared.UserInfo;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class ViewProfileActivity extends AbstractActivity implements ViewProfileView.Presenter 
{
 	private String name;
 	private UserInfo cachedUserInfo;
 	private static ClientFactory clientFactory;
 	private ViewProfileView view;
 	private LoginServiceAsync LoginService;
 	
 	public ViewProfileActivity(ViewProfilePlace place, ClientFactory clientFactory) 
 	{
 		 this.name = place.getName();
 		 ViewProfileActivity.clientFactory = clientFactory;
 		 LoginService = Util.getInstance();
 	}
 
 	@Override
 	public void start(AcceptsOneWidget containerWidget, EventBus eventBus)
 	{
 	  view = clientFactory.getViewProfileView();
  		view.setPresenter(this);
  		getUserInfo();
  		containerWidget.setWidget(view.asWidget());
 	}
 
 	/**
 	 * @see ViewProfile.Presenter#goTo(Place)
 	 */
 	public void goTo(Place place) 
 	{
 		 clientFactory.getPlaceController().goTo(place);
 	}
 	
 	public void getUserInfo()
  {
    LoginService.getUserInfo(name, new AsyncCallback<UserInfo>()
    {
      @Override
      public void onFailure(Throwable caught)
      {
        System.out.println("RPC failure" + caught.toString());
      }

      @Override
      public void onSuccess(UserInfo result)
      {
        view.setUserInfo(result);
        cachedUserInfo = result; //store it for later usage, e.g. detection of changes in text boxes to toggle "save" button
      }
    });
  }

  @Override
  public boolean hasUnsavedFields(UserInfo currentUserInfo)
  {
    if (!cachedUserInfo.getFirstName().trim().equals(currentUserInfo.getFirstName().trim())
     || (cachedUserInfo.getMiddleName() != null && !cachedUserInfo.getMiddleName().trim().equals(currentUserInfo.getMiddleName().trim())
      || cachedUserInfo.getMiddleName() == null && !currentUserInfo.getMiddleName().trim().equals(""))
     || !cachedUserInfo.getLastName().trim().equals(currentUserInfo.getLastName().trim())
     || !cachedUserInfo.getEmail().trim().equalsIgnoreCase(currentUserInfo.getEmail().trim())
     || !cachedUserInfo.getMobileNumber().trim().equalsIgnoreCase(currentUserInfo.getMobileNumber().trim()))
     {
       return true;
     }
     else
     {
       return false;
     }
  }
}
