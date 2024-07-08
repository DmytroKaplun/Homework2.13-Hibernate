package org.example.dao;

import org.example.app.dao.SpaceTravelDao;
import org.example.app.dao.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientDaoTest {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Transaction tx;
    @Mock
    private Query<Client> query;
    @InjectMocks
    private SpaceTravelDao<Client> clientDao;

    @BeforeEach
    public void setUp() {
        clientDao = new SpaceTravelDao<>(Client.class, sessionFactory);
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    void save() {
        when(session.beginTransaction()).thenReturn(tx);
        Client client = Client.builder()
                .name("Anna")
                .passport("A1234567")
                .build();
        clientDao.save(client);
        verify(session).persist(client);
        verify(tx).commit();
    }

    @Test
    void findById() {
        clientDao.findById(1L);
        verify(session).get(Client.class, 1L);
    }

    @Test
    void update() {
        when(session.beginTransaction()).thenReturn(tx);
        Client client = Client.builder()
                .name("Anna")
                .passport("A1234567")
                .build();

        clientDao.update(client);

        verify(session).merge(client);
        verify(tx).commit();
    }

    @Test
    void delete() {
        when(session.beginTransaction()).thenReturn(tx);
        Client client = new Client("A1234567","Anna" );
        when(session.get(Client.class, 1L)).thenReturn(client);

        clientDao.delete(1L);
        verify(session).remove(client);
        verify(tx).commit();
    }

    @Test
    void findAll() {
        Client client = Client.builder()
                .name("Anna")
                .passport("A1234567")
                .build();
        Client client2 = Client.builder()
                .name("Bob")
                .passport("A4234500")
                .build();
        List<Client> expectedClients = Arrays.asList(client, client2);

        when(session.createQuery("from Client", Client.class)).thenReturn(query);
        when(query.list()).thenReturn(expectedClients);

        List<Client> foundClients = clientDao.findAll();

        verify(session).createQuery("from Client", Client.class);
        verify(query).list();
        Assertions.assertEquals(expectedClients, foundClients);
    }
}