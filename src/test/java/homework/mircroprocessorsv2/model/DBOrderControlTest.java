package homework.mircroprocessorsv2.model;

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

        microprocessor.setClockspeedsById(clockSpeeds);
        new DBOrderControl().saveMicroprocessor(microprocessor);
    }

    @Test
    void getMicroprocessorById() {
    }

    @Test
    void getAllMicroprocessors() {
    }

    @Test
    void updateMicroprocessor() {
    }

    @Test
    void deleteMicroprocessorById() {
    }
}