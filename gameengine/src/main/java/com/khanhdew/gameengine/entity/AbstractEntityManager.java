package com.khanhdew.gameengine.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

@Getter
public class AbstractEntityManager<T> implements EntityManager {
    protected final ObjectPool<T> objectPool = new ObjectPool<>() {
        @Override
        protected T create() {
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };
    private final Class<T> type;
    protected final List<T> entities;

    public AbstractEntityManager(Class<T> type) {
        this.type = type;
        this.entities = Collections.synchronizedList(new ArrayList<>());
    }
    @Override
    public void add(double x, double y, double w, double h) {
        Optional<T> inactiveEntity = entities.stream()
                .filter(e -> !((BaseEntity) e).isActive())
                .findFirst();

        if (inactiveEntity.isPresent()) {
            T e = inactiveEntity.get();
            ((BaseEntity) e).setActive(true);
            ((BaseEntity) e).reset(x, y, w, h);
        } else {
            T entity = objectPool.borrow();
            ((BaseEntity) entity).reset(x, y, w, h);
            entities.add(entity);
        }

        System.out.println("Add entity: " + entities.size());

    }

    @Override
    public void remove() {
        Optional<T> inactiveEntity = entities.stream()
                .filter(e -> !((BaseEntity) e).isActive())
                .findFirst();
        if (inactiveEntity.isPresent()) {
            T e = inactiveEntity.get();
            objectPool.returnToPool(e);
            entities.remove(e);
        }
    }

    @Override
    public void update() {
        entities.forEach(e -> {
            ((BaseEntity) e).update();
        });
        remove();
    }
}
