package com.restcode.restcode.resource;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveAppointmentResource {
    @NotNull
    private Date scheduledDatetime;
    @NotNull
    private String topic;
    @NotNull
    private String meetingLink;

    public Date getScheduledDatetime() {
        return scheduledDatetime;
    }

    public void setScheduledDatetime(Date scheduledDatetime) {
        this.scheduledDatetime = scheduledDatetime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }
}
