package homework.mircroprocessorsv2.datasource;

import homework.mircroprocessorsv2.datasource.model.Microprocessor;
import jakarta.persistence.*;

import java.util.List;

public class DBMicroprocessorDataSource implements MicroprocessorDataSource {
    @Override
    public void saveMicroprocessor(Microprocessor microprocessor) {
        // 1. создаем фабрику
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        // 2. manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 3. объект транзакции
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            // выполнение самой операции создания записи в БД
            transaction.begin();
            entityManager.persist(microprocessor);   // создание новой записи на основе объекта
            transaction.commit();
        } finally {
            // все закрыть и откатить транзакцию, если нужно
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public Microprocessor getMicroprocessorById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // переменная для хранения объекта-результата поиска
        Microprocessor result = null;
        try {
            transaction.begin();
            // сама операция
            Query query = entityManager.createNamedQuery("get_microprocessor_by_id");
            query.setParameter("id", id);
            result = (Microprocessor) query.getSingleResult();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return result;  // вернуть результат
    }

    //read - операция
    @Override
    public List<Microprocessor> getAllMicroprocessors() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<Microprocessor> microprocessors;

        try {
            transaction.begin();
            // сама операция
            microprocessors = entityManager.createNamedQuery("get_all_microprocessors").getResultList();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return microprocessors;
    }

    @Override
    public void updateMicroprocessor(Microprocessor microprocessor) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(microprocessor);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public void deleteMicroprocessorById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // сама операция
            // 1. получить удаляемый объект
            Microprocessor deleted = entityManager.find(Microprocessor.class, id);
            // 2. удалить
            entityManager.remove(deleted);
            //entityManager.createNamedQuery("delete_microprocessor_by_id").setParameter("id", id).executeUpdate();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
