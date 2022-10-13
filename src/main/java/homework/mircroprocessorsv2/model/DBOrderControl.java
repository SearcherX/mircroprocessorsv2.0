package homework.mircroprocessorsv2.model;

import jakarta.persistence.*;

import java.util.List;

public class DBOrderControl implements MicroprocessorControl {
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
            result = entityManager.find(Microprocessor.class, id);
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

    @Override
    public List<Microprocessor> getAllMicroprocessors() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        // список результатов
        List<Microprocessor> microprocessors = null;

        try {
            transaction.begin();
            // сама операция
            microprocessors = entityManager.createQuery("SELECT m FROM Microprocessor m").getResultList();
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
            // 1. получаю обновляемый объект по Id
            Microprocessor updated = entityManager.find(Microprocessor.class, microprocessor.getId());
            // 2. обновляю поля обновляемого объекта
           // updated.updateFields(microprocessor); !!!!
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
