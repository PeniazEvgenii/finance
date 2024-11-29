package by.it_academy.jd2.schedulerservice.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant startTime;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant stopTime;

    private Long interval;

    private ETimeUnit timeUnit;

    public Instant getStartTime() {
        return this.startTime;
    }

    public Instant getStopTime() {
        return this.stopTime;
    }

    public Long getInterval() {
        return this.interval;
    }

    public ETimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    public void setStopTime(Instant stopTime) {
        this.stopTime = stopTime;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public void setTimeUnit(ETimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ScheduleDto)) return false;
        final ScheduleDto other = (ScheduleDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$startTime = this.getStartTime();
        final Object other$startTime = other.getStartTime();
        if (this$startTime == null ? other$startTime != null : !this$startTime.equals(other$startTime)) return false;
        final Object this$stopTime = this.getStopTime();
        final Object other$stopTime = other.getStopTime();
        if (this$stopTime == null ? other$stopTime != null : !this$stopTime.equals(other$stopTime)) return false;
        final Object this$interval = this.getInterval();
        final Object other$interval = other.getInterval();
        if (this$interval == null ? other$interval != null : !this$interval.equals(other$interval)) return false;
        final Object this$timeUnit = this.getTimeUnit();
        final Object other$timeUnit = other.getTimeUnit();
        if (this$timeUnit == null ? other$timeUnit != null : !this$timeUnit.equals(other$timeUnit)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ScheduleDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $startTime = this.getStartTime();
        result = result * PRIME + ($startTime == null ? 43 : $startTime.hashCode());
        final Object $stopTime = this.getStopTime();
        result = result * PRIME + ($stopTime == null ? 43 : $stopTime.hashCode());
        final Object $interval = this.getInterval();
        result = result * PRIME + ($interval == null ? 43 : $interval.hashCode());
        final Object $timeUnit = this.getTimeUnit();
        result = result * PRIME + ($timeUnit == null ? 43 : $timeUnit.hashCode());
        return result;
    }

    public String toString() {
        return "ScheduleDto(startTime=" + this.getStartTime() + ", stopTime=" + this.getStopTime() + ", interval=" + this.getInterval() + ", timeUnit=" + this.getTimeUnit() + ")";
    }
}
