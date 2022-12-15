package homework.mircroprocessorsv2.datasource;

import homework.mircroprocessorsv2.datasource.model.Microprocessor;

import java.util.List;

public interface MicroprocessorDataSource {
    void saveMicroprocessor(Microprocessor microprocessor);
    Microprocessor getMicroprocessorById(int id);
    List<Microprocessor> getAllMicroprocessors();
    void updateMicroprocessor(Microprocessor microprocessor);
    void deleteMicroprocessorById(int id);
}
