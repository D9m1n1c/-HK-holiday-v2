package com.example.demo.holidays;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.demo.holidays.HolidayRepository;

@Component
public class HolidayFetch {

    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidayFetch(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public void holidayData() throws Exception{

        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://www.1823.gov.hk/common/ical/en.json"))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Holidays holidayGson = gson.fromJson(response.body(), Holidays.class);

        JSONObject jsonObject = new JSONObject(holidayGson);

        JSONArray jsonArray = jsonObject.getJSONArray("vcalendar");
        ArrayList<Object> listdata = new ArrayList<Object>();
        listdata.add(jsonArray.get(0));

        JSONObject newJsonObject = new JSONObject(listdata.get(0).toString());
        JSONArray newJsonArray = newJsonObject.getJSONArray("vevent");
        ArrayList<Object> newlistdata = new ArrayList<Object>();

        if(newJsonArray != null) {
            for (int i = 0; i < newJsonArray.length(); i++) {
                newlistdata.add(newJsonArray.get(i));
            }
        }

        List<Holidays> HolidayList = new ArrayList<>();



        for(int i=0 ; i<newlistdata.size(); i++){

            JSONObject summaryObject = new JSONObject(newlistdata.get(i).toString());

            Holidays holidays = new Holidays();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);

            holidays.setSummary(summaryObject.get("summary").toString());

            holidays.setUid(summaryObject.get("uid").toString());

            JSONArray dtendArray = summaryObject.getJSONArray("dtend");
            JSONArray dtstartArray = summaryObject.getJSONArray("dtstart");

            String dtstartString = dtstartArray.get(0).toString();
            String dtendString = dtendArray.get(0).toString();
            Date dtstartDate = dateFormat.parse(dtstartString);
            Date dtendDate = dateFormat.parse(dtendString);

            holidays.setDtstart(dtstartDate);

            holidays.setDtend(dtendDate);

            HolidayList.add(holidays);

        }

        holidayRepository.saveAll(HolidayList);
    }
}
