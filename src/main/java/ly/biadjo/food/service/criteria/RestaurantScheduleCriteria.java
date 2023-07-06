package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.DayOfWeek;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.RestaurantSchedule} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.RestaurantScheduleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /restaurant-schedules?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantScheduleCriteria implements Serializable, Criteria {

    /**
     * Class for filtering DayOfWeek
     */
    public static class DayOfWeekFilter extends Filter<DayOfWeek> {

        public DayOfWeekFilter() {
        }

        public DayOfWeekFilter(DayOfWeekFilter filter) {
            super(filter);
        }

        @Override
        public DayOfWeekFilter copy() {
            return new DayOfWeekFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DayOfWeekFilter dayOfWeek;

    private StringFilter openingTime;

    private StringFilter closingTime;

    private LongFilter restaurantId;

    private Boolean distinct;

    public RestaurantScheduleCriteria() {
    }

    public RestaurantScheduleCriteria(RestaurantScheduleCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.dayOfWeek = other.dayOfWeek == null ? null : other.dayOfWeek.copy();
        this.openingTime = other.openingTime == null ? null : other.openingTime.copy();
        this.closingTime = other.closingTime == null ? null : other.closingTime.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RestaurantScheduleCriteria copy() {
        return new RestaurantScheduleCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DayOfWeekFilter getDayOfWeek() {
        return dayOfWeek;
    }

    public DayOfWeekFilter dayOfWeek() {
        if (dayOfWeek == null) {
            dayOfWeek = new DayOfWeekFilter();
        }
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeekFilter dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public StringFilter getOpeningTime() {
        return openingTime;
    }

    public StringFilter openingTime() {
        if (openingTime == null) {
            openingTime = new StringFilter();
        }
        return openingTime;
    }

    public void setOpeningTime(StringFilter openingTime) {
        this.openingTime = openingTime;
    }

    public StringFilter getClosingTime() {
        return closingTime;
    }

    public StringFilter closingTime() {
        if (closingTime == null) {
            closingTime = new StringFilter();
        }
        return closingTime;
    }

    public void setClosingTime(StringFilter closingTime) {
        this.closingTime = closingTime;
    }

    public LongFilter getRestaurantId() {
        return restaurantId;
    }

    public LongFilter restaurantId() {
        if (restaurantId == null) {
            restaurantId = new LongFilter();
        }
        return restaurantId;
    }

    public void setRestaurantId(LongFilter restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RestaurantScheduleCriteria that = (RestaurantScheduleCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(dayOfWeek, that.dayOfWeek) &&
                Objects.equals(openingTime, that.openingTime) &&
                Objects.equals(closingTime, that.closingTime) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, openingTime, closingTime, restaurantId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantScheduleCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (dayOfWeek != null ? "dayOfWeek=" + dayOfWeek + ", " : "") +
            (openingTime != null ? "openingTime=" + openingTime + ", " : "") +
            (closingTime != null ? "closingTime=" + closingTime + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
