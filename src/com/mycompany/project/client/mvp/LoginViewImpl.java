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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Sample implementation of {@link LoginView}.
 */
public class LoginViewImpl extends Composite implements LoginView 
{
  private static final Binder binder = GWT.create(Binder.class);
  
 	interface Binder extends UiBinder<Widget, LoginViewImpl> 
 	{
 	}
 	
 	private Presenter presenter;
 	
 	@UiField
 	Button btLogin;
 	@UiField
 	TextBox tbUserName;
 	@UiField
 	PasswordTextBox tbPassword;
// 	@UiField
// 	Image backgroundImage;
// 	@UiField
// 	Resources res;
 	
 	public LoginViewImpl() 
 	{
  		initWidget(binder.createAndBindUi(this));
 	}
 
 	@Override
 	public void setPresenter(Presenter listener) 
 	{
 		 this.presenter = listener;
 	}
 
 	@UiHandler("btLogin")
 	void onBtLoginClick(ClickEvent event) 
 	{
 	  presenter.authenticateUser(tbUserName.getText(), tbPassword.getText());
 	}
 
  @Override
  public void setUserName(String userName) 
  {
    tbUserName.setText(userName);
  }

  @Override
  public void setPassword(String password) 
  {
    tbPassword.setText(password);
  }
}
