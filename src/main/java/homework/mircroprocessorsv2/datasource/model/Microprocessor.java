package homework.mircroprocessorsv2.datasource.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@NamedQueries({
        @NamedQuery(
                name = "get_all_microprocessors",
                query = "from Microprocessor m"
        ),
        @NamedQuery(
                name = "get_microprocessor_by_id",
                query = "from Microprocessor m where m.id = :id"
        ),
        //HQL - delete и update не получилось реализовать
        @NamedQuery(
                name = "delete_microprocessor_by_id",
                query = "delete from Microprocessor m where m.id = :id"
        )
})
@Entity
public class Microprocessor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Model")
    private String model;
    @Basic
    @Column(name = "DataBitDepth")
    private int dataBitDepth;
    @Basic
    @Column(name = "AddressBitDepth")
    private int addressBitDepth;
    @Basic
    @Column(name = "AddressSpaces")
    private long addressSpaces;
    @Basic
    @Column(name = "NumberOfCommands")
    private Integer numberOfCommands;
    @Basic
    @Column(name = "NumberOfElements")
    private int numberOfElements;
    @Basic
    @Column(name = "ReleaseYear")
    private int releaseYear;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "microprocessorId")
    private List<ClockSpeed> clockSpeedsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDataBitDepth() {
        return dataBitDepth;
    }

    public void setDataBitDepth(int dataBitDepth) {
        this.dataBitDepth = dataBitDepth;
    }

    public int getAddressBitDepth() {
        return addressBitDepth;
    }

    public void setAddressBitDepth(int addressBitDepth) {
        this.addressBitDepth = addressBitDepth;
    }

    public long getAddressSpaces() {
        return addressSpaces;
    }

    public void setAddressSpaces(long addressSpaces) {
        this.addressSpaces = addressSpaces;
    }

    public Integer getNumberOfCommands() {
        return numberOfCommands;
    }

    public void setNumberOfCommands(Integer numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    //преобразовать список частот в строку для вывода в текстовое поле
    public String getClockSpeedsStr() {
        StringBuilder sb = new StringBuilder();
        for (ClockSpeed clockSpeed: clockSpeedsById) {
            sb.append(clockSpeed.toString());
            sb.append(" и ");
        }
        if (!sb.toString().equals(""))
            sb.delete(sb.length() - 3, sb.length());
        return sb.toString();
    }

    //парсить строку из текстового поля в список объектов частот
    public void setClockSpeeds(String clockSpeeds) {
        clockSpeedsById = new ArrayList<>();
        String[] csArr= clockSpeeds.replaceAll(" ", "").split("и");
        for (String cs: csArr) {
            String[] csValArr = cs.split("-");
            BigDecimal max = null;
            if (csValArr.length > 1)
                max = new BigDecimal(csValArr[1]);
            ClockSpeed clockSpeed = new ClockSpeed();
            clockSpeed.setMinValueM(new BigDecimal(csValArr[0]));
            clockSpeed.setMaxValueM(max);
            this.clockSpeedsById.add(clockSpeed);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Microprocessor that = (Microprocessor) o;
        return id == that.id && dataBitDepth == that.dataBitDepth && addressBitDepth == that.addressBitDepth && addressSpaces == that.addressSpaces && numberOfElements == that.numberOfElements && releaseYear == that.releaseYear && Objects.equals(model, that.model) && Objects.equals(numberOfCommands, that.numberOfCommands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, dataBitDepth, addressBitDepth, addressSpaces, numberOfCommands, numberOfElements, releaseYear);
    }

    public List<ClockSpeed> getClockSpeedsById() {
        return clockSpeedsById;
    }

    public void setClockSpeedsById(List<ClockSpeed> clockSpeedsById) {
        this.clockSpeedsById = clockSpeedsById;
    }

    @Override
    public String toString() {
        return "Microprocessor{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", dataBitDepth=" + dataBitDepth +
                ", addressBitDepth=" + addressBitDepth +
                ", addressSpaces=" + addressSpaces +
                ", numberOfCommands=" + numberOfCommands +
                ", numberOfElements=" + numberOfElements +
                ", releaseYear=" + releaseYear +
                ", clockspeedsById=" + clockSpeedsById +
                '}';
    }
}
