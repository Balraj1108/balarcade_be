package com.example.balarcade.service;

import com.example.balarcade.repository.PostazioneRepository;
import com.example.balarcade.service.provider.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Service
@RequiredArgsConstructor
public class PostazioneService {

    private final PostazioneRepository repository;
    private ServiceProvider sp;


    @Transactional
    public void prendiPostazioni() {

    }

}
