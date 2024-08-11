package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class App {
    public static void main(String[] args) {
        // Configuração do Hibernate
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        // Criando uma fábrica de sessões
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Criando uma sessão
        Session session = sessionFactory.openSession();

        // Iniciando uma transação
        session.beginTransaction();

        // Criando o banco de dados se ele não existir
        String createDatabase = "CREATE DATABASE IF NOT EXISTS testdb";
        session.createSQLQuery(createDatabase).executeUpdate();

        // Selecionando o banco de dados
        String useDatabase = "USE testdb";
        session.createSQLQuery(useDatabase).executeUpdate();

        // Operação no banco de dados: criação da tabela
        String sql = "CREATE TABLE IF NOT EXISTS example (" +
                "id INT AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "PRIMARY KEY (id))";
        session.createSQLQuery(sql).executeUpdate();

        // Commit e fechamento da sessão
        session.getTransaction().commit();
        session.close();

        // Fechando a fábrica de sessões
        sessionFactory.close();
    }
}
