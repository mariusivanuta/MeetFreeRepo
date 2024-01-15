package global.mappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.inject.Singleton;
import java.text.DateFormat;
import java.util.Locale;

@Singleton
public class ObjectMapperCustomization implements ObjectMapperCustomizer {
    public void customize(ObjectMapper mapper) {


        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);

        mapper.enable(SerializationFeature.WRITE_DATES_WITH_CONTEXT_TIME_ZONE);

        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);

        mapper.setDateFormat(DateFormat.getDateInstance(2, Locale.GERMAN));

        mapper.registerModule(new JavaTimeModule());

        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

    }
}