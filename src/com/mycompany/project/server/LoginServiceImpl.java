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
package com.mycompany.project.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.project.client.rpc.LoginService;
import com.mycompany.project.shared.UserInfo;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService 
{
  private static final long serialVersionUID = 1L;
  private Connection conn = null;
  private String url = null;
  private String user = null;
  private String pass = null;
  private boolean isDB_Local = false;
  
  public LoginServiceImpl() 
  {
    if (isDB_Local)
    {
      url  = "jdbc:mysql://127.0.0.1:3306/bookingsystem";
      user = "root";
      pass = "1111"; //TODO: those strings should be configured in .xml and read it here
    }
    else
    {
      url  = "jdbc:mysql://instance30844.db.xeround.com:19824/bookingsystem";
      user = "admin";
      pass = "admin";
    }
    
    try 
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(url, user, pass);
    } 
    catch (Exception e) 
    {
      //NEVER catch exceptions like this
      System.out.println("connection fails to be setup" + e.getMessage());
    }
  }
  
  @Override
  public boolean authenticateUser(String user, String pass) 
  {
    boolean found = false;
    try 
    {
      PreparedStatement ps = conn.prepareStatement(
      "select * from students where FirstName = \"" + user + "\" AND " +
        "Password = \"" + pass + "\"");
      
      ResultSet result = ps.executeQuery();
      while (result.next()) 
      {
        found = true;
        System.out.println("User successfully authenticated!");
      }
      
      result.close();
      ps.close();
    } 
    catch (SQLException sqle) 
    {
      System.out.println("SQL exception!");
    }
    
    return found;
  }

  @Override
  public UserInfo getUserInfo(String user)
  {
    UserInfo userInfo = new UserInfo();
    try 
    {
      PreparedStatement ps = conn.prepareStatement(
      "select * from students where FirstName = \"" + user + "\" ");
      
      ResultSet result = ps.executeQuery();
      while (result.next()) 
      {
        userInfo.setFirstName(result.getString("FirstName"));
        userInfo.setMiddleName(result.getString("MiddleName"));
        userInfo.setLastName(result.getString("LastName"));
        userInfo.setMobileNumber(result.getString("MobilePhone"));
        userInfo.setStreet(result.getString("StreeName"));
        userInfo.setCity(result.getString("City"));
        userInfo.setPostCode(result.getString("PostCode"));
        userInfo.setEmail(result.getString("Email"));
        userInfo.setCountry("Sweden");
      }
      
      result.close();
      ps.close();
    } 
    catch (SQLException sqle) 
    {
      System.out.println("SQL exception!\n" + sqle.toString());
    }
    return userInfo;
  }

  @Override
  public boolean updateUserInfo(UserInfo user)
  {
    try
    {
      PreparedStatement ps = conn.prepareStatement("insert into students VALUES"      + 
                                                   "(" + "'" + (int)Math.random() + "'" + ","     +
                                                         "'" + user.getFirstName() + "'" + ","    +
                                                         "'" + user.getMiddleName() + "'" + ","   +
                                                         "'" + user.getLastName() + "'" + ","     +
                                                         "'" + user.getMobileNumber() + "'" + "," +
                                                         "'" + user.getStreet() + "'" + ","       +
                                                         "'" + user.getPostCode() + "'" + ","     +
                                                         "'" + user.getCity() + "'" + ","         +
                                                         "'" + user.getPassword() + "'" + ","     +
                                                         "'" + user.getEmail() + "'" + ","        + 
                                                         "'" + user.getCountry() + "'" + ")");
      
      ps.executeUpdate();
      System.out.println("User info successfully updated");
      ps.close();
      return true;
    } 
    catch (SQLException e)
    {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public UserInfo[] getAllUsers()
  {
    UserInfo[] allUserInfo = null;
    try 
    {
      PreparedStatement ps = conn.prepareStatement("select * from students order by ID");
      ResultSet result = ps.executeQuery();
      if (result.last())
      {
        int count = result.getRow(); //get the number of rows so that the size of the array below can be determined
        allUserInfo = new UserInfo[count];

        result.beforeFirst(); //move back the iterator back to the first element of the result set.
        count = 0;
        while (result.next()) 
        {
          allUserInfo[count] = new UserInfo(); 
          allUserInfo[count].setId(result.getString("ID"));
          allUserInfo[count].setFirstName(result.getString("FirstName"));
          allUserInfo[count].setMiddleName(result.getString("MiddleName"));
          allUserInfo[count].setLastName(result.getString("LastName"));
          allUserInfo[count].setMobileNumber(result.getString("MobilePhone"));
          allUserInfo[count].setStreet(result.getString("StreeName"));
          allUserInfo[count].setCity(result.getString("City"));
          allUserInfo[count].setPostCode(result.getString("PostCode"));
          allUserInfo[count].setEmail(result.getString("Email"));
          allUserInfo[count].setCountry(result.getString("Country"));
          count++;
        }
      }
      
      result.close();
      ps.close();
      return allUserInfo;
    } 
    catch (SQLException sqle) 
    {
      System.out.println("SQL exception!\n" + sqle.toString());
      return null;
    }
  }
}
