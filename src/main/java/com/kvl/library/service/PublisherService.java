package com.kvl.library.service;

import com.kvl.library.entity.Publisher;
import com.kvl.library.exception.EntityNotFoundException;
import com.kvl.library.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAllPublishers() {
        log.info("Fetching all publishers from the database");
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(final Long id) {
        Publisher publisher = findById(id);
        log.info("Fetched publisher '{}' by id '{}' from the database", publisher, id);
        return publisher;
    }

    private Publisher findById(final Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher with ID " + id + " was not found"));
    }

    public void createPublisher(final Publisher publisher) {
        log.info("Saving publisher '{}' to the database", publisher);
        publisherRepository.save(publisher);
    }

    public void updatePublisher(final Publisher publisher) {
        log.info("Updating publisher '{}' in the database", publisher);
        publisherRepository.save(publisher);
    }

    public void deletePublisher(final Long id) {
        final Publisher publisher = findById(id);
        log.info("Deleting publisher '{}' by id '{}' from the database", publisher, id);
        publisherRepository.deleteById(publisher.getId());
    }
}
