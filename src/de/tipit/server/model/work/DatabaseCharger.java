package de.tipit.server.model.work;

import javax.persistence.EntityManager;

public interface DatabaseCharger {
    void add(final EntityManager entityManager);
}
