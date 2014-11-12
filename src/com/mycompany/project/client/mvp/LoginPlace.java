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

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * A place object representing a particular state of the UI. A Place can be converted to and from a
 * URL history token by defining a {@link PlaceTokenizer} for each {@link Place}, and the 
 * {@link PlaceHistoryHandler} automatically updates the browser URL corresponding to each 
 * {@link Place} in your app.
 */
public class LoginPlace extends Place 
{
 	/**
 	 * Sample property (stores token). 
 	 */
 	private String m_userName;
 	private String m_password;
 
 	public LoginPlace(String token) 
 	{
	    String stringsFromToken[] = token.split(":");
	    if (null != stringsFromToken && stringsFromToken.length == 2)
	    {
	      m_userName = stringsFromToken[0];
	      m_password = stringsFromToken[1];
	    }
	    else
	    {
	      m_userName = m_password = "";
	    }
 	}
 
 	public String getUserName() 
 	{
 		 return m_userName;
 	}
 
 	public String getPassword() 
 	{
    return m_password;
  }
 
   /**
 	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
 	 */
 	public static class Tokenizer implements PlaceTokenizer<LoginPlace> 
 	{
  		@Override
  		public String getToken(LoginPlace place) 
  		{
  		 	return place.getUserName() + "|" + place.getPassword();
  		}
 
  		@Override
  		public LoginPlace getPlace(String token) 
  		{
  		 	return new LoginPlace(token);
  		}
 	}
}
