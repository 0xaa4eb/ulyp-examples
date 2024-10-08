package org.example.hibernate.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonStoreService {

    @Autowired
    private PersonRepository repository;

    public void save(Person person) {
        repository.save(person);
    }

    public Person find(long id) {
        return repository.findOne(id);
    }
}
