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
package com.mycompany.project.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mycompany.project.shared.UserInfo;

@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService 
{
  /*
   * Authenticate the user with provided password. If they are identical to what are stored
   * in the database, the result is true, otherwise false.
   */
  public boolean authenticateUser(final String user, 
                                  final String pass);
  
  /*
   * Get the user information of the specified user.
   */
  public UserInfo getUserInfo(String user);
  
  /*
   * Write a new account into the database or update existing account with updated information
   * If the operation fails, the result is false, otherwise true.
   */
  public boolean updateUserInfo(UserInfo user);
  
  /*
   * Get all registered users
   */
  public UserInfo[] getAllUsers();
  
	 /**
	 * Utility class for simplifying access to the instance of async service.
	 */
	 public static class Util 
	 {
	 	 private static LoginServiceAsync instance;
		  public static LoginServiceAsync getInstance()
		  {
		   	if (instance == null) 
		  	 {
			 	   instance = GWT.create(LoginService.class);
			   }
			   return instance;
		  }
	 }
}
