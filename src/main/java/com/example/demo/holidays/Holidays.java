package com.example.demo.holidays;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Holidays {

    @Transient
    private Object vcalendar;

    public Object getVcalendar() {
        return vcalendar;
    }

    public void setVcalendar(Object vcalendar) {
        this.vcalendar = vcalendar;
    }

    @Id
    @Column(name = "role_id"
    )
    @SequenceGenerator(
            name = "holiday_sequence",
            sequenceName = "holiday_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "holiday_sequence"
    )
    private Long id;
    private String uid;
    private Date dtstart;
    private Date dtend;
    private String summary;

    public Holidays() {
    }

    public Holidays(String uid, Date dtstart, Date dtend, String summary) {
        this.uid = uid;
        this.dtstart = dtstart;
        this.dtend = dtend;
        this.summary = summary;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDtstart() {
        return dtstart;
    }

    public void setDtstart(Date dtstart) {
        this.dtstart = dtstart;
    }

    public Date getDtend() {
        return dtend;
    }

    public void setDtend(Date dtend) {
        this.dtend = dtend;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Holidays{" +
                "uid='" + uid + '\'' +
                ", dtstart=" + dtstart +
                ", dtend=" + dtend +
                ", summary='" + summary + '\'' +
                '}';
    }
}
