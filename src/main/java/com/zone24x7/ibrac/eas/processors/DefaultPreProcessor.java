package com.zone24x7.ibrac.eas.processors;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.pojo.EventJson;
import com.zone24x7.ibrac.eas.util.JsonPojoConverter;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

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

        String eventData = eventInputParams.getEventData();

        if(eventInputParams.getContentType().equals(StringConstants.APPLICATION_JSON)){

            EventJson eventJson = JsonPojoConverter.toPojo(eventData, EventJson.class);
            eventJson.setEasTimestamp(new Date().toString());
            eventInputParams.setEventData(JsonPojoConverter.toJson(eventJson).toString());

        } else {
            eventData = StringConstants.SOH + new Date().toString() + StringConstants.SOT + eventData;
            eventInputParams.setEventData(eventData);
        }

        return eventInputParams;
    }
}
