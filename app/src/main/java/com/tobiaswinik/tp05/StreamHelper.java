package com.tobiaswinik.tp05;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamHelper {
    public static String GetFullStringFromInputReader(InputStream streamDeEntrada){
        BufferedReader responseReader;
        String responseLine;
        String strResultado="";
        StringBuilder sbResponse;
        try {
            responseReader= new BufferedReader(new InputStreamReader(streamDeEntrada));
            sbResponse=new StringBuilder();
            while ((responseLine = responseReader.readLine()) != null){
                sbResponse.append(responseLine);
            }
            responseReader.close();
            strResultado=sbResponse.toString();
        }catch (Exception e){

        }
        return strResultado;

    }
}