package homework.mircroprocessorsv2.model;

import java.util.List;

public interface MicroprocessorControl {
    void saveMicroprocessor(Microprocessor microprocessor);
    Microprocessor getMicroprocessorById(int id);
    List<Microprocessor> getAllMicroprocessors();
    void updateMicroprocessor(Microprocessor microprocessor);
    void deleteMicroprocessorById(int id);
}
