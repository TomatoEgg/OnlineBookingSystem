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
package com.mycompany.project.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.mycompany.project.client.ClientFactory;
import com.mycompany.project.client.mvp.BookingSystemActivityMapper;
import com.mycompany.project.client.mvp.BookingSystemPlaceHistoryMapper;
import com.mycompany.project.client.mvp.LoginPlace;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BookingSystem implements EntryPoint 
{
  private Place defaultPlace = new LoginPlace(":"); //default place: empty name and empty pw
  private final SimplePanel appWidget = new SimplePanel();

  @Override
  public void onModuleLoad()
  {
    ClientFactory clientFactory = GWT.create(ClientFactory.class);
    
    EventBus eventBus = clientFactory.getEventBus();
    ActivityMapper activityMapper = new BookingSystemActivityMapper(clientFactory);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    
    activityManager.setDisplay(appWidget); //set the simple panel as the "drawing background"
    
    // Start PlaceHistoryHandler with our PlaceHistoryMapper
    BookingSystemPlaceHistoryMapper historyMapper = GWT.create(BookingSystemPlaceHistoryMapper.class);
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(clientFactory.getPlaceController(), eventBus, defaultPlace);
    
    RootPanel.get().add(appWidget);
    // Goes to place represented on URL or default place
    historyHandler.handleCurrentHistory();
  }
}
