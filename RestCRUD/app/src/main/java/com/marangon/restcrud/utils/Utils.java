package com.marangon.restcrud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String URL_API = "https://api-rest-spring-fetch.herokuapp.com/api/alumno/";

    public static String dateToString(Date fecha){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return formatter.format(fecha);
    }

    public static Date stringToDate(String fecha){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date fechaDate = new Date();
        try {
            fechaDate = formatter.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fechaDate;
    }

}
