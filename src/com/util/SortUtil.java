package com.util;

import com.enums.IncidentType;
import com.enums.Status;
import com.model.Incident;

public class SortUtil {
    public static void main(String[] args)
    {
        Incident incident1 = new Incident(1, IncidentType.REPORT_ABUSE, "Case file created.", Status.INITIATED);
        Incident incident2 = new Incident(1, IncidentType.THEFT, "Security footage under review.", Status.ACTIVE);

        System.out.println(incident1 == incident2);
        System.out.println(incident1.equals(incident2));
        /* at first if i didnt generate equals() and hashcode() method in Incident class then the .equals() will not come tru
        bec .equals check == ..if it is false and no equals() method present in that particular class then output will be false
        even though if the content is same

        if i generate the equals() method inside that particular class..i can tell the jvm to come true if these two values are true
        means my references are same like that
        here i put the id same others are false but the .equals() came true bec i created .equals() in incident class

        heap -stores the object creation(Incident)
        stack- stores the refrences (incident 1 and incident 2)

         */
    }
}
