package com.storage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.storage.entity.DemoSerialDeserial;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.*;
import java.util.Date;

@Path("/demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DemoController {

    @GET
    public Response demoSerialDeserial() throws JsonProcessingException {

        DemoSerialDeserial demoSerialDeserial = new DemoSerialDeserial();

//        demoSerialDeserial.setaBoolean(null);
//        demoSerialDeserial.setId(1L);
//        demoSerialDeserial.setName("Name");
//        demoSerialDeserial.setaSamallBolean(true);
//        demoSerialDeserial.setPriceFloat(1F);
//        demoSerialDeserial.setPriceDouble(1.00);
//        demoSerialDeserial.setLocalDate(LocalDate.now());
//        demoSerialDeserial.setOffsetDateTime(OffsetDateTime.now(ZoneId.of("America/Nipigon")));
//        demoSerialDeserial.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Europe/Istanbul")));
//        demoSerialDeserial.setDateTime(LocalDateTime.now());
//        Map<String, String> stringStringMap = new HashMap<>();
//        stringStringMap.put("abc", "abc");
//        stringStringMap.put("bcd", "bcd");
//        stringStringMap.put("cde", "cde");
//
//        demoSerialDeserial.setStringStringMap(stringStringMap);
//
//        Map<LocalDate, String> localDateStringMap = new HashMap<>();
//        localDateStringMap.put(LocalDate.of(2023, 10, 11), "abc");
//        localDateStringMap.put(LocalDate.of(2023, 6, 5), "bcd");
//        localDateStringMap.put(LocalDate.of(2023, 3, 22), "cde");

        //demoSerialDeserial.setLocalDateStringMap(localDateStringMap);


        demoSerialDeserial.setDuration(Duration.ofDays(1));
        demoSerialDeserial.setInstant(Instant.now(Clock.tickMinutes(ZoneId.of("Europe/Berlin"))));
        demoSerialDeserial.setPeriod(Period.of(2, 2, 3));

        demoSerialDeserial.setOffsetDateTime(OffsetDateTime.now(ZoneId.of("Europe/Berlin")));
        demoSerialDeserial.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Europe/Berlin")));
        demoSerialDeserial.setLocalDate(LocalDate.now());


        demoSerialDeserial.setData(new Date());
        demoSerialDeserial.setDateTime(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Berlin"));
        String converted = objectMapper.writeValueAsString(now);

        ZonedDateTime restored = objectMapper.readValue(converted, ZonedDateTime.class);
        System.out.println("serialized: " + now);
        System.out.println("restored: " + restored);

//        int[] intArray = new int[3];
//        intArray[0] = 10;
//        // intArray[1] = null;
//        intArray[2] = 6;
//
//        demoSerialDeserial.setIntArray(intArray);
//
//        demoSerialDeserial.setOperationTypeEnum(OperationTypeEnum.ADD);


        return Response.ok(demoSerialDeserial).build();

    }
}
