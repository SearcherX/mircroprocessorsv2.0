package homework.mircroprocessorsv2.control;

import homework.mircroprocessorsv2.datasource.model.Microprocessor;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "QueryServlet", value = "/QueryServlet")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectMode = request.getParameter("output");

        switch (selectMode) {
            case "query1" -> {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = entityManager.getTransaction();

                try {
                    transaction.begin();
                    // сама операция
                    Query query = entityManager.createQuery("select m from Microprocessor m where m.addressBitDepth >= 16");
                    transaction.commit();
                    request.setAttribute("microprocessors", query.getResultList());
                    request.setAttribute("caption", "HQL-запрос(Запрос 1): выбрать микропроцессоры с разрядностью >= 16");
                    request.setAttribute("selectMode", selectMode);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
            }
            case "query2" -> {
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
                    transaction.commit();
                    request.setAttribute("microprocessors", typedQuery.getResultList());
                    request.setAttribute("caption", "JPQL(Запрос2): выбрать микропроцессоры с тактовой частотой 4.77");
                    request.setAttribute("selectMode", selectMode);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
            }
            case "query3" -> {
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
                    transaction.commit();
                    request.setAttribute("microprocessors", query.getResultList());
                    request.setAttribute("caption", "JPQL(запрос3): выбрать микропроцессоры с тактовой частотой-диапозоном и разрядностью данных 32");
                    request.setAttribute("selectMode", selectMode);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
            }
            case "query4" -> {
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
                    transaction.commit();
                    request.setAttribute("microprocessors", query.getResultList());
                    request.setAttribute("caption", "JPQL(запрос4): выбрать микропроцессоры с моделью, начинающейся на Pe");
                    request.setAttribute("selectMode", selectMode);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
                break;
            }
            case "query5" -> {
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
                    transaction.commit();
                    request.setAttribute("microprocessors", microprocessors);
                    request.setAttribute("caption", "Criteria(запрос5): выбрать микропроцессоры с моделью, начинающейся на 80");
                    request.setAttribute("selectMode", selectMode);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
            }
            default -> {
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }
}
