package homework.mircroprocessorsv2.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ClockSpeed {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "MinValueM")
    private BigDecimal minValueM;
    @Basic
    @Column(name = "MaxValueM")
    private BigDecimal maxValueM;
//    @Basic
//    @Column(name = "microprocessorId")
//    private int microprocessorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMinValueM() {
        return minValueM;
    }

    public void setMinValueM(BigDecimal minValueM) {
        this.minValueM = minValueM;
    }

    public BigDecimal getMaxValueM() {
        return maxValueM;
    }

    public void setMaxValueM(BigDecimal maxValueM) {
        this.maxValueM = maxValueM;
    }

//    public int getMicroprocessorId() {
//        return microprocessorId;
//    }
//
//    public void setMicroprocessorId(int microprocessorId) {
//        this.microprocessorId = microprocessorId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Clockspeed that = (Clockspeed) o;
//        return id == that.id && microprocessorId == that.microprocessorId && Objects.equals(minValueM, that.minValueM) && Objects.equals(maxValueM, that.maxValueM);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, minValueM, maxValueM, microprocessorId);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClockSpeed that = (ClockSpeed) o;
        return id == that.id && minValueM.equals(that.minValueM) && Objects.equals(maxValueM, that.maxValueM);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minValueM, maxValueM);
    }
}
