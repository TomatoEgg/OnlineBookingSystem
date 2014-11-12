package com.mycompany.project.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserInfo implements IsSerializable
{
  public static class Names implements IsSerializable
  {
    private String firstName;
    private String middleName;
    private String lastName;
    /**
     * @return the firstName
     */
    public String getFirstName()
    {
      return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
      this.firstName = firstName;
    }
    /**
     * @return the middleName
     */
    public String getMiddleName()
    {
      return middleName;
    }
    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName)
    {
      this.middleName = middleName;
    }
    /**
     * @return the lastName
     */
    public String getLastName()
    {
      return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
      this.lastName = lastName;
    }
  };
  
  public static class contactInfo implements IsSerializable
  {
    public static class Address implements IsSerializable
    {
      /**
       * @return the street
       */
      public String getStreet()
      {
        return street;
      }

      /**
       * @param street the street to set
       */
      public void setStreet(String street)
      {
        this.street = street;
      }

      /**
       * @return the postCode
       */
      public String getPostCode()
      {
        return postCode;
      }

      /**
       * @param postCode the postCode to set
       */
      public void setPostCode(String postCode)
      {
        this.postCode = postCode;
      }

      /**
       * @return the country
       */
      public String getCountry()
      {
        return country;
      }

      /**
       * @param country the country to set
       */
      public void setCountry(String country)
      {
        this.country = country;
      }

      /**
       * @return the city
       */
      public String getCity()
      {
        return city;
      }

      /**
       * @param city the city to set
       */
      public void setCity(String city)
      {
        this.city = city;
      }

      private String street;
      private String city;
      private String country;
      private String postCode;
    }

    //Constructor
    public contactInfo()
    {
      address = new Address();
    }
    
    private Address address;
    private String email;
    private String mobileNumber;
    
    /**
     * @return the address
     */
    public Address getAddress()
    {
      return address;
    }
    /**
     * @param address the address to set
     */
    public void setAddress(Address address)
    {
      this.address = address;
    }
    /**
     * @return the email
     */
    public String getEmail()
    {
      return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
      this.email = email;
    }
    /**
     * @return the mobileNumber
     */
    public String getMobileNumber()
    {
      return mobileNumber;
    }
    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber)
    {
      this.mobileNumber = mobileNumber;
    }
  };
  
  private String id;
  private Names names;
  private contactInfo contactInfo;
  private String password;

  //Constructor
  public UserInfo()
  {
    contactInfo = new contactInfo();
    names       = new Names();
  }
  
  /**
   * @return the password
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  /**
   * @return the id
   */
  public String getId()
  {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id)
  {
    this.id = id;
  }

  /**
   * @return the name
   */
  public Names getNames()
  {
    return names;
  }

  /**
   * @param name the name to set
   */
  public void setNames(Names names)
  {
    this.names = names;
  }

  /**
   * @return the contactInfo
   */
  public contactInfo getContactInfo()
  {
    return contactInfo;
  }

  /**
   * @param contactInfo the contactInfo to set
   */
  public void setContactInfo(contactInfo contactInfo)
  {
    this.contactInfo = contactInfo;
  }

  /* To make this class for encapsulated, wrapper functions for
   * retrieving fields from this class are needed.
   */
  
  public String getFirstName()
  {
    return names.getFirstName();
  }

  public void setFirstName(String firstName)
  {
    names.setFirstName(firstName);
  }
  
  public String getMiddleName()
  {
    return names.getMiddleName();
  }
  
  public void setMiddleName(String middleName)
  {
    names.setMiddleName(middleName);
  }

  public String getLastName()
  {
    return names.getLastName();
  }

  public void setLastName(String lastName)
  {
    names.setLastName(lastName);
  }
  
  public String getMobileNumber()
  {
    return contactInfo.getMobileNumber();
  }
  
  public void setMobileNumber(String mobileNumber)
  {
    contactInfo.setMobileNumber(mobileNumber);
  }

  public String getStreet()
  {
    return contactInfo.getAddress().getStreet();
  }
  
  public void setStreet(String street)
  {
    contactInfo.getAddress().setStreet(street);
  }

  public String getPostCode()
  {
    return contactInfo.getAddress().getPostCode();
  }

  public void setPostCode(String postCode)
  {
    contactInfo.getAddress().setPostCode(postCode);
  }
  
  public String getCity()
  {
    return contactInfo.getAddress().getCity();
  }
  
  public void setCity(String city)
  {
    contactInfo.getAddress().setCity(city);
  }

  public String getCountry()
  {
    return contactInfo.getAddress().getCountry();
  }
  
  public void setCountry(String country)
  {
    contactInfo.getAddress().setCountry(country);
  }

  public String getEmail()
  {
    return contactInfo.getEmail();
  }
  
  public void setEmail(String email)
  {
    contactInfo.setEmail(email);
  }
}
