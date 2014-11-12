package com.mycompany.project.client.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class OperationPlace extends Place
{
  //TODO: Add members
  private String m_userName;
  
  public OperationPlace(String userName)
  {
    m_userName = userName;
  }
  
  public String getUserName()
  {
    return m_userName;
  }
  
  public void setUserName(String m_userName)
  {
    this.m_userName = m_userName;
  }
  /**
   * PlaceTokenizer knows how to serialize the Place's state to a URL token.
   */
  public static class Tokenizer implements PlaceTokenizer<OperationPlace>
  {
    @Override
    public OperationPlace getPlace(String token)
    {
      return new OperationPlace(token);
    }

    @Override
    public String getToken(OperationPlace place)
    {
      return place.getUserName();
    } 
  }
}
