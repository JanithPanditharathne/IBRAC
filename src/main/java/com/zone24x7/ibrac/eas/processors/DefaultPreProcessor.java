package com.zone24x7.ibrac.eas.processors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.pojo.EventJson;
import com.zone24x7.ibrac.eas.util.JsonPojoConverter;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Implementation class of the pre processor to represent the default pre processor.
 */
@Component
public class DefaultPreProcessor implements PreProcessor {

    /**
     * Method to process the request body.
     *
     * @param eventInputParams eventInputParams pojo object.
     * @return processed request body enclosed with eventInputParams object.
     */
    @Override
    public EventInputParams process(EventInputParams eventInputParams) throws IOException {
        // Get the eventData from the eventInputParams and store it in eventData.
        String eventData = eventInputParams.getEventData();

        if (eventInputParams.getContentType().equals(StringConstants.APPLICATION_JSON)) {
            // List to store multiple Jsons if an array of JSON is sent in the POST request
            List<EventJson> eventJsonList = JsonPojoConverter.toPojo(eventData, new TypeReference<List<EventJson>>() {
            });

            //For Loop is used to add the easTimestamp to all the jsons.
            for (EventJson eventJson : eventJsonList) {
                eventJson.setEasTimestamp(new Date().toString());
            }
            // Set the event data in eventInputParams to the new event data (with easTimestamp added)
            eventInputParams.setEventData(JsonPojoConverter.toJson(eventJsonList).toString());
        } else {
            // Add the relevant string constants and the date to the text in the POST request sent
            eventData = StringConstants.SOH + new Date().toString() + StringConstants.SOT + eventData;
            // setEventData with the new eventData (with string constants and date appended to the eventData that was sent)
            eventInputParams.setEventData(eventData);
        }
        // Return the new eventInputParams with the easTimestamp added and relevant string constants (for text/plain)
        return eventInputParams;
    }
}
