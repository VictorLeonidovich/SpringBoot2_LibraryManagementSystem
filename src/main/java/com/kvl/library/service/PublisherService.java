package com.kvl.library.service;

import com.kvl.library.entity.Book;
import com.kvl.library.entity.Publisher;
import com.kvl.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(final Long id) {
        return findById(id);
    }

    private Publisher findById(final Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher in not found"));
    }

    public void createPublisher(final Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void updatePublisher(final Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void deletePublisher(final Long id) {
        final Publisher publisher = findById(id);
        publisherRepository.deleteById(publisher.getId());
    }
}
