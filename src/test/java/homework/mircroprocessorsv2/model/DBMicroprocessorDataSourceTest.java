package homework.mircroprocessorsv2.model;

import homework.mircroprocessorsv2.datasource.model.ClockSpeed;
import homework.mircroprocessorsv2.datasource.model.Microprocessor;
import homework.mircroprocessorsv2.datasource.DBMicroprocessorDataSource;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class DBMicroprocessorDataSourceTest {

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
        new DBMicroprocessorDataSource().deleteMicroprocessorById(93);
    }

    //HQL-запрос: выбрать всех
    @Test
    void selectQuery1() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // сама операция
            Query query = entityManager.createQuery("from Microprocessor");
            System.out.println(query.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    //JPQL - типизированный запрос, где тактовая частота - 4.77
    @Test
    void selectQuery2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // сама операция
            TypedQuery<Microprocessor> typedQuery = entityManager.createQuery(
                    "select M from Microprocessor M " +
                            "join M.clockSpeedsById cs " +
                            "where cs.minValueM =:clockSpeed",
                    Microprocessor.class);
            typedQuery.setParameter("clockSpeed", new BigDecimal("4.77"));
            System.out.println(typedQuery.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    //JPQL - запрос, где тактовая частота - диапозон и разрядность данных - 32
    @Test
    void selectQuery3() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // сама операция
            Query query = entityManager.createQuery(
                    "select m from Microprocessor m " +
                            "join m.clockSpeedsById cs " +
                            "where cs.minValueM is not null and cs.maxValueM is not null and m.dataBitDepth = :depth"
                    );
            query.setParameter("depth", 32);
            System.out.println(query.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    //JPQL - запрос, где модель начинается на Pe
    @Test
    void selectQuery4() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // сама операция
            Query query = entityManager.createQuery(
                    "select m from Microprocessor m " +
                            "where substring(m.model, 1, 2) = :str"
            );
            query.setParameter("str", "Pe");
            System.out.println(query.getResultList());
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    //Criteria - запрос, получить модели, начинающиеся на 80
    @Test
    void selectQuery5() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // сама операция
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Microprocessor> criteria = builder.createQuery(Microprocessor.class);
            Root<Microprocessor> root = criteria.from(Microprocessor.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("model"), "80%"));
            List<Microprocessor> microprocessors = entityManager.createQuery(criteria).getResultList();
            System.out.println(microprocessors);
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