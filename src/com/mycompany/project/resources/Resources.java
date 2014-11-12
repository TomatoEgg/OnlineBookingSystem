package com.mycompany.project.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle
{
  public static final Resources INSTANCE = GWT.create(Resources.class);
  
  @Source("bg1.jpg")
  ImageResource getBackground();
  
  public interface Style extends CssResource
  {
    String popup();
    String upperHalf();
    String upperImage();
    String rightUpper();
    String label();
    String viewProfileButton();
    String bottomHalf();
    String logo();
    String labelName();
    String profileTitle();
    String viewProfileView_Labels();
    String myButton();
    String labelNameInPopup();
  }
  
  @Source("myCSS.css")
  public Style style();
}
