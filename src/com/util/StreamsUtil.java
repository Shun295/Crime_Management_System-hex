package com.util;

import com.enums.IncidentType;
import com.model.Incident;
import com.service.StreamsService;

import java.util.List;

public class StreamsUtil {
    public static void main(String[] args)
    {
        StreamsService streamsService=new StreamsService();
        List<Incident> list=streamsService.sampleData();

        List<Incident> filteredListByType=streamsService.filterIncidentByType(list, IncidentType.REPORT_ABUSE);
        filteredListByType.forEach(System.out :: println);

        List<IncidentType> incType=streamsService.getAllIncidentType(list);
        incType.forEach(System.out :: println);
    }
}
