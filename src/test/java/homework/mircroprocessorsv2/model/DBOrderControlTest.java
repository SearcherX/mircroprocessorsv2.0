package homework.mircroprocessorsv2.model;

import homework.mircroprocessorsv2.datasource.model.ClockSpeed;
import homework.mircroprocessorsv2.datasource.model.Microprocessor;
import homework.mircroprocessorsv2.datasource.DBMicroprocessorDataSource;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class DBOrderControlTest {

    @Test
    void saveMicroprocessor() {
        Microprocessor microprocessor = new Microprocessor();
        microprocessor.setModel("Тест2");
        microprocessor.setDataBitDepth(16);
        microprocessor.setAddressBitDepth(16);
        microprocessor.setAddressSpaces(534536456);
        microprocessor.setNumberOfCommands(500);
        microprocessor.setNumberOfElements(10000);
        microprocessor.setReleaseYear(2015);

        List<ClockSpeed> clockSpeeds = new ArrayList<>();

        ClockSpeed clockSpeed = new ClockSpeed();
        clockSpeed.setMinValueM(new BigDecimal("5"));

        clockSpeed.setMaxValueM(null);
        clockSpeeds.add(clockSpeed);

        ClockSpeed clockSpeed1 = new ClockSpeed();
        clockSpeed1.setMinValueM(new BigDecimal("8"));
        clockSpeed1.setMaxValueM(null);
        clockSpeeds.add(clockSpeed1);

        microprocessor.setClockSpeedsById(clockSpeeds);
        new DBMicroprocessorDataSource().saveMicroprocessor(microprocessor);
    }

    @Test
    void getMicroprocessorById() {
        Microprocessor microprocessor = new DBMicroprocessorDataSource().getMicroprocessorById(83);
        System.out.println(microprocessor);
    }

    @Test
    void getAllMicroprocessors() {
        List<Microprocessor> microprocessors = new DBMicroprocessorDataSource().getAllMicroprocessors();
        System.out.println(microprocessors);
    }

    @Test
    void updateMicroprocessor() {
        Microprocessor microprocessor = new DBMicroprocessorDataSource().getMicroprocessorById(84);
        microprocessor.setModel("Новый тест20");
        microprocessor.getClockSpeedsById().get(0).setMaxValueM(new BigDecimal("200"));
        new DBMicroprocessorDataSource().updateMicroprocessor(microprocessor);
    }

    @Test
    void deleteMicroprocessorById() {
        new DBMicroprocessorDataSource().deleteMicroprocessorById(84);
    }
}