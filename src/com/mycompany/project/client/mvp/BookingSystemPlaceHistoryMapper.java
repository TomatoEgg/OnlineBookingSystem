package com.mycompany.project.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( { LoginPlace.Tokenizer.class, 
                   OperationPlace.Tokenizer.class,
                   ViewProfilePlace.Tokenizer.class})
public interface BookingSystemPlaceHistoryMapper extends PlaceHistoryMapper {
}
