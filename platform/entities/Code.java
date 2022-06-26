package platform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "code_snippets")
public class Code {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Lob
    private String code;
    private LocalDateTime date;
    private long time; // time in seconds in which the snippet will be available
    private long views; // views left for the snippet
    @JsonIgnore @Column(name = "viewed_times")
    private long viewedTimes = 0;
    @JsonIgnore
    private boolean restricted = false;

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonProperty("date")
    public String getDateAsString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @JsonProperty("time")
    public long getRemainingSeconds() {
        if (time == 0) {
            return time;
        }
        LocalDateTime expirationDate = date.plusSeconds(time);
        return Math.max(0, Duration.between(LocalDateTime.now(), expirationDate).toSeconds());
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @JsonProperty("views")
    public long getRemainingViews() {
        if (views == 0) {
            return views;
        }
        return Math.max(0, views - viewedTimes);
    }

    public long getViewedTimes() {
        return viewedTimes;
    }

    public void setViewedTimes(long viewedTimes) {
        this.viewedTimes = viewedTimes;
    }

    public void registerView() {
        viewedTimes++;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    @JsonIgnore
    public boolean isAvailable() {
        if (!restricted) {
            return true;
        }
        if (time > 0 && LocalDateTime.now().isAfter(date.plusSeconds(time))) {
            return false;
        }
        return views <= 0 || viewedTimes < views;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", views=" + views +
                ", viewedTimes=" + viewedTimes +
                ", restricted=" + restricted +
                '}';
    }
}
